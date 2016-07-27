package au.edu.unsw.soacourse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import au.edu.unsw.soacourse.backgroundcheck.BackgroundCheckRequest;
import au.edu.unsw.soacourse.backgroundcheck.BackgroundCheckResponse;
import au.edu.unsw.soacourse.backgroundcheck.BackgroundCheckServicePortType;
import au.edu.unsw.soacourse.dao.RegisteredUsersDao;
import au.edu.unsw.soacourse.model.Application;
import au.edu.unsw.soacourse.model.Applications;
import au.edu.unsw.soacourse.model.Company;
import au.edu.unsw.soacourse.model.Jobs;
import au.edu.unsw.soacourse.model.Posting;
import au.edu.unsw.soacourse.model.RegisteredUser;
import au.edu.unsw.soacourse.model.RegisteredUsers;
import au.edu.unsw.soacourse.model.User;

@Controller
public class CompanyController {
	private static final String REST_URI = "http://localhost:8080/RestfulFoundITService";
	private static final String SECURITY_KEY = "i-am-foundit";

	@Autowired
	private BackgroundCheckServicePortType autocheck;
	
	@RequestMapping(value = "/companyRegisterForm")
	public String registerCompanyForm() throws Exception {
		return "companyRegisterForm";
	}
	
	@RequestMapping(value = "/companyLogin")
	public String companyLogin() throws Exception {
		return "companyLogin";
	}
	
	@RequestMapping(value = "/companyHome")
	public String companyHome() throws Exception {
		return "companyHome";
	}
	
	@RequestMapping(value = "/newJobForm")
	public String newJobForm() throws Exception {
		return "newJobForm";
	}
	
	@RequestMapping(value = "/companyUpdate")
	public String companyUpdate(ModelMap model, HttpServletRequest req) throws Exception {
		//String profileid = req.getParameter("id");
		//String employees = req.getParameter("employees");
		//String location = req.getParameter("location");
		//TODO
		return "companyHome";
	}
	
	@RequestMapping(value = "/processCompanyLogin")
	public String processCompanyLogin(ModelMap model, HttpServletRequest req) throws Exception {
		//login details
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		RegisteredUsersDao dao = new RegisteredUsersDao();
		dao.getRegisteredUsers();
		RegisteredUser user = dao.getUser(email, password);
		
		if (user != null && !user.getRole().equalsIgnoreCase("app-candidate")) {
			req.getSession().setAttribute("user", user);
			String role = user.getRole();
			req.getSession().setAttribute("companyid", Math.abs(email.hashCode()));
			req.getSession().setAttribute("role", role);
			
			String queryString = "/company/" + Math.abs(email.hashCode());
			WebClient client = WebClient.create(REST_URI + queryString);
			System.out.println(REST_URI + queryString);
			
			client.header("SecurityKey", SECURITY_KEY);
			client.header("ShortKey", role);
			client.accept(MediaType.APPLICATION_JSON);
			
			Response response = client.get();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response.readEntity(String.class));
			
			Company c = new Company();
			
			c.setEmail(node.get("email").getTextValue());
			c.setEmployees(node.get("employees").getTextValue());
			c.setIndustry(node.get("industry").getTextValue());
			c.setLocation(node.get("location").getTextValue());
			c.setName(node.get("name").getTextValue());
			c.setPassword(node.get("password").getTextValue());
			c.setProfileId(node.get("profileId").getIntValue());
			
			req.getSession().setAttribute("company", c);
			
			return "companyHome";
			
		} else {
			model.addAttribute("loginfail", 1);
		}
		
		return "companyLogin";
	}
	

	@RequestMapping(value = "/companyRegister")
	public String registerCompany(ModelMap model, HttpServletRequest req) throws Exception {
		// company details //TODO
		String name = req.getParameter("name");
		String industry = req.getParameter("industry");
		String employees = req.getParameter("employees");
		String location = req.getParameter("location");
		
		//manager details
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String manageremail = req.getParameter("email");
		String password = req.getParameter("password");
		String role = "app-manager";
		
		if (manageremail == null || manageremail.isEmpty() ||
				password == null || password.isEmpty() ||
				firstname == null || firstname.isEmpty() ||
				lastname == null || lastname.isEmpty() ||
				name == null || name.isEmpty() ||
				industry == null || industry.isEmpty() ||
				employees == null || employees.isEmpty() ||
				location == null || location.isEmpty()) {
			model.addAttribute("emptyparam", 1);
			return "companyRegisterForm";
		}
		
		RegisteredUsersDao dao = new RegisteredUsersDao();
		RegisteredUsers users = dao.getRegisteredUsers();
		dao.getRegisteredUsers();
		
		if (!dao.isUserRegistered(manageremail)) {
			RegisteredUser newUser = new RegisteredUser(manageremail, password, "manager");
			users.addUser(newUser);
			dao.setRegisteredUsers(users);

			req.getSession().setAttribute("companyid", Math.abs(manageremail.hashCode()));
			req.getSession().setAttribute("role", role);
			
			String queryString = "/company";
			WebClient client = WebClient.create(REST_URI + queryString);
			System.out.println("create company: " + REST_URI + queryString);
			
			client.header("SecurityKey", SECURITY_KEY);
			client.header("ShortKey", role);
			
			Form form = new Form();
			form.param("email", manageremail);
			form.param("name", name);
			form.param("industry", industry);
			form.param("employees", employees);
			form.param("location", location);
			client.post(form);
			
			WebClient userclient = WebClient.create(REST_URI + "/user");
			System.out.println("create user: " + REST_URI + "/user");
			
			userclient.header("SecurityKey", SECURITY_KEY);
			userclient.header("ShortKey", role);
		
			Form userform = new Form();
			userform.param("email", manageremail);
			userform.param("password", password);
			userform.param("firstname", firstname);
			userform.param("lastname", lastname);
			userclient.post(userform);
			
			return processCompanyLogin(model, req);
		} else {
			model.addAttribute("userexist", 1);
			return "registerForm";
		}	
	}
	
	@RequestMapping("/companyViewApplications")
	public String viewCompanyApplications(ModelMap model, HttpServletRequest req) {
		String queryString = "/jobs/companyjobs/" + req.getSession().getAttribute("companyid");
		WebClient client = WebClient.create(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_XML);
		Jobs jobs = client.get(Jobs.class);
		Applications aps = new Applications();
		for (Posting p : jobs.getAllPostings()) {
			client = WebClient.create(REST_URI + "/application/job/" + p.getJobId());
			System.out.println(REST_URI + "/application/job/" + p.getJobId());
			client.header("SecurityKey", SECURITY_KEY);
			client.header("ShortKey", req.getSession().getAttribute("role"));
			client.accept(MediaType.APPLICATION_XML);
			Response response = client.get();
			if (response.getStatus() != Status.NOT_FOUND.getStatusCode()) {
				Applications app = client.get(Applications.class);
				for(Application a : app.getApplications()) {
					a.setPosting(p);
					client = WebClient.create(REST_URI + "/user/" + a.getProfileId());
					client.header("SecurityKey", SECURITY_KEY);
					client.header("ShortKey", req.getSession().getAttribute("role"));
					client.accept(MediaType.APPLICATION_XML);
					User u = client.get(User.class);
					a.setUser(u);
				}
				aps.getApplications().addAll(app.getApplications());
			}
		}
		model.addAttribute("applications",aps.getApplications());
		req.getSession().setAttribute("apps", aps);
		return "companyApplications";
	}
	
	@RequestMapping("/autocheck")
	public String autoCheck(ModelMap model, HttpServletRequest req) {
		BackgroundCheckRequest autocheckreq = new BackgroundCheckRequest();
		Applications apps = (Applications) req.getSession().getAttribute("apps");
		for(Application a : apps.getApplications()) {
			autocheckreq.setAddress(a.getUser().getPersonalDetails().getAddress());
			System.out.println(autocheckreq.getAddress());
			autocheckreq.setDLNumber(a.getUser().getPersonalDetails().getDriverLicenseNo());
			System.out.println(autocheckreq.getDLNumber());
			autocheckreq.setFullName(a.getUser().getPersonalDetails().getFirstName() + " " + 
					a.getUser().getPersonalDetails().getLastName());
			System.out.println(autocheckreq.getFullName());
			BackgroundCheckResponse response = autocheck.checkApplicantBackground(autocheckreq);
			a.setAutoCheckResponse(response.getResponse());
			a.setAutoCheckDetails(response.getValidationMsg());
		}
		model.addAttribute("applications", apps.getApplications());
		return "companyApplications";
	}
	
	@RequestMapping("/postNewJob")
	public String newPosting(ModelMap model, HttpServletRequest req) {
		WebClient client = WebClient.create(REST_URI + "/jobs");
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		System.out.println(REST_URI + "/application");
		String salaryrate = req.getParameter("salaryrate");
		String location = req.getParameter("location");
		String details = req.getParameter("details");
		String postitle = req.getParameter("positiontitle");
		if (salaryrate == null || salaryrate.isEmpty() ||
				location == null || location.isEmpty() ||
				details == null || details.isEmpty()) {
			model.addAttribute("emptyparam", 1);
			return "newJobForm";
		}
		Form form = new Form();
		form.param("companyid", req.getSession().getAttribute("companyid").toString());
		form.param("salaryrate", salaryrate);
		form.param("location", location);
		form.param("details", details);
		form.param("positiontitle", postitle);
		client.post(form);
		return "postingResponse";
	}
}
