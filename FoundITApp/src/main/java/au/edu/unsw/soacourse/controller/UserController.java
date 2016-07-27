package au.edu.unsw.soacourse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import au.edu.unsw.soacourse.dao.RegisteredUsersDao;
import au.edu.unsw.soacourse.model.PersonalDetails;
import au.edu.unsw.soacourse.model.RegisteredUser;
import au.edu.unsw.soacourse.model.RegisteredUsers;
import au.edu.unsw.soacourse.model.User;

@Controller
public class UserController {
	private static final String REST_URI = "http://localhost:8080/RestfulFoundITService";
	private static final String SECURITY_KEY = "i-am-foundit";
	
	@RequestMapping(value = {"/", "", "/home"})
	public String home(ModelMap model, HttpServletRequest req) throws Exception {
		String role = (String) req.getSession().getAttribute("role");
		if (role == null) {
			return "login";
		}
		
		if (role.equals("app-manager")) {
			return "companyHome";
		} else if (role.equals("app-reviewer")) {
			return "hiringTeamHome";
		} else {
			return "candidateHome";
		}
	}
	
	@RequestMapping(value = "/login")
	public String login() throws Exception {
		return "login";
	}
	
	@RequestMapping(value = "/processLogin")
	public String processLogin(ModelMap model, HttpServletRequest req) throws Exception {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		RegisteredUsersDao dao = new RegisteredUsersDao();
		dao.getRegisteredUsers();
		RegisteredUser user = dao.getUser(email, password);
		if (user != null && !user.getRole().equalsIgnoreCase("app-manager") && !user.getRole().equals("app-reviewer")) {
			String role = user.getRole();
			req.getSession().setAttribute("profileId", Math.abs(email.hashCode()));
			req.getSession().setAttribute("role", role);
			String queryString = "/user/" + Math.abs(email.hashCode());
			WebClient client = WebClient.create(REST_URI + queryString);
			System.out.println(REST_URI + queryString);
			client.header("SecurityKey", SECURITY_KEY);
			client.header("ShortKey", role);
			client.accept(MediaType.APPLICATION_JSON);
			Response response = client.get();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response.readEntity(String.class));
			JsonNode personalDetailsNode = node.get("personalDetails");
			User u = new User();
			PersonalDetails pd = new PersonalDetails();
			
			pd.setAddress(personalDetailsNode.get("address").getTextValue());
			pd.setEmail(personalDetailsNode.get("email").getTextValue());
			pd.setAddress(personalDetailsNode.get("address").getTextValue());
			pd.setFirstName(personalDetailsNode.get("firstName").getTextValue());
			pd.setLastName(personalDetailsNode.get("lastName").getTextValue());
			pd.setDriverLicenseNo(personalDetailsNode.get("driverLicenseNo").getTextValue());
			u.setCurrentPosition(node.get("currentPosition").getTextValue());
			u.setSkills(node.get("skills").getTextValue());
			u.setExperience(node.get("experience").getTextValue());
			u.setEducation(node.get("education").getTextValue());
			u.setProfileId(node.get("profileId").getIntValue());
			u.setPersonalDetails(pd);
			
			req.getSession().setAttribute("user", u);
			if (role.equalsIgnoreCase("app-candidate")) {
				return "candidateHome";
			} else if (role.equalsIgnoreCase("app-manager")) {
				return "managerHome";
			} else if (role.equalsIgnoreCase("app-reviewer")) {
				return "hiringTeamHome";
			}
		} else {
			model.addAttribute("loginfail", 1);
		}
		return "login";
	}
	
	@RequestMapping(value = "/registerForm")
	public String registerForm() throws Exception {
		return "registerForm";
	}
	
	@RequestMapping(value = "/register")
	public String register(ModelMap model, HttpServletRequest req) throws Exception {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String dlno = req.getParameter("dlno");
		String addr = req.getParameter("adr");
		
		if (email == null || email.isEmpty() ||
				password == null || password.isEmpty() ||
				firstname == null || firstname.isEmpty() ||
				lastname == null || lastname.isEmpty() ) {
			model.addAttribute("emptyparam", 1);
		}
		
		if ((dlno == null || dlno.isEmpty()) && ((addr == null || addr.isEmpty()))) {
			model.addAttribute("emptypersonal", 1);
		}
			
		RegisteredUsersDao dao = new RegisteredUsersDao();
		RegisteredUsers users = dao.getRegisteredUsers();
		dao.getRegisteredUsers();
		
		// before creating new user
		if (!dao.isUserRegistered(email)) {
			//update registereduser xml
			RegisteredUser newUser = new RegisteredUser(email, password, role);
			users.addUser(newUser);
			dao.setRegisteredUsers(users);
			
			//add to restful server xml
			WebClient userclient = WebClient.create(REST_URI + "/user");
			System.out.println("create user: " + REST_URI + "/user");
			
			userclient.header("SecurityKey", SECURITY_KEY);
			userclient.header("ShortKey", newUser.getRole());
		
			Form userform = new Form();
			userform.param("email", email);
			userform.param("password", password);
			userform.param("firstname", firstname);
			userform.param("lastname", lastname);
			userform.param("dlno", dlno);
			userform.param("address", addr);
			userclient.post(userform);
			
			return "registerResponse";
		} else {
			model.addAttribute("userexist", 1);
			return "registerForm";
		}
		
	}

	@RequestMapping(value = "/logout")
	public String logout(ModelMap model, HttpServletRequest req) throws Exception {
		req.getSession(false).invalidate();
		req.getSession(true);
		
		return "login";
	}
	
}
