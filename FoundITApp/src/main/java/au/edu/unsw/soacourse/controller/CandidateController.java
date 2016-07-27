package au.edu.unsw.soacourse.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import au.edu.unsw.soacourse.model.*;

@Controller
public class CandidateController {
	private static final String REST_URI = "http://localhost:8080/RestfulFoundITService";
	private static final String SECURITY_KEY = "i-am-foundit";
	
	public Posting getPosting(String jobid, HttpServletRequest req) throws JsonProcessingException, IOException {
		String queryString = "/jobs/" + jobid;
		WebClient client = WebClient.create(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_JSON);
		Response response = client.get();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.readEntity(String.class));
		Posting posting = new Posting();
		posting.setJobId(node.get("jobId").getIntValue());
		posting.setCompanyId(node.get("companyId").getIntValue());
		posting.setPositionTitle(node.get("positionTitle").getTextValue());
		posting.setSalaryRate(node.get("salaryRate").getTextValue());
		posting.setLocation(node.get("location").getTextValue());
		posting.setDetails(node.get("details").getTextValue());
		posting.setStatus(node.get("status").getTextValue());
		
		client = WebClient.create(REST_URI + "/company/" + posting.getCompanyId());
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_JSON);
		response = client.get();
		mapper = new ObjectMapper();
		node = mapper.readTree(response.readEntity(String.class));
		posting.setCompanyname(node.get("name").getTextValue());
	
		return posting;
	}
	
	@RequestMapping(value = "/searchJobs")
	public String searchJobs(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException{
		String location = req.getParameter("location");
		String company = req.getParameter("company");
		String positiontitle = req.getParameter("positiontitle");
		String salaryrate = req.getParameter("salaryrate");
		String details = req.getParameter("details");
		String status = req.getParameter("status");
		
		String queryString = "/jobs/search?" + 
				"location=" + location + "&" +
	 			"company=" + company + "&" +
	 			"positiontitle=" + positiontitle + "&" +
	 			"salaryrate=" + salaryrate + "&" +
	 			"details=" + details + "&" +
	 			"status=" + status;
	 	queryString = queryString.replace(" ", "%20");
	 	queryString = queryString.replace("null", "");
	 	System.out.println(REST_URI + queryString);
		WebClient client = WebClient.create(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_XML);
		Jobs jobs = client.get(Jobs.class);
		System.out.println("foundit app " + jobs.getAllPostings().size());
		for (Posting p : jobs.getAllPostings()) {
			client = WebClient.create(REST_URI + "/company/" + p.getCompanyId());
			client.header("SecurityKey", SECURITY_KEY);
			client.header("ShortKey", req.getSession().getAttribute("role"));
			client.accept(MediaType.APPLICATION_JSON);
			Response response = client.get();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response.readEntity(String.class));
			p.setCompanyname(node.get("name").getTextValue());
		}
		model.addAttribute("jobs", jobs.getAllPostings());
		return "jobPosting";
	}
	
	@RequestMapping(value = "/jobdetail")
	public String viewPosting(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException{
		String jobid = req.getParameter("id");
		String company = req.getParameter("company");
		String queryString = "/jobs/" + jobid;

		WebClient client = WebClient.create(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_JSON);
		Response response = client.get();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.readEntity(String.class));
		Posting posting = new Posting();
		posting.setJobId(node.get("jobId").getIntValue());
		posting.setCompanyId(node.get("companyId").getIntValue());
		posting.setPositionTitle(node.get("positionTitle").getTextValue());
		posting.setSalaryRate(node.get("salaryRate").getTextValue());
		posting.setLocation(node.get("location").getTextValue());
		posting.setDetails(node.get("details").getTextValue());
		posting.setStatus(node.get("status").getTextValue());
		posting.setCompanyname(company);
		model.addAttribute("posting", posting);
		
		return "jobDetail";
	}
	
	@RequestMapping(value = "/apply")
	public String showApplicationForm(ModelMap model, HttpServletRequest req){
		model.addAttribute("jobid", req.getParameter("jobid"));
		return "applicationForm";
	}
	
	@RequestMapping(value = "/createApplication")
	public String apply(ModelMap model, HttpServletRequest req) {
		WebClient client = WebClient.create(REST_URI + "/application");
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		System.out.println(REST_URI + "/application");
		Form form = new Form();
		form.param("jobid", req.getParameter("jobid"));
		form.param("profileid", req.getSession().getAttribute("profileId").toString());
		form.param("coverletter", req.getParameter("coverletter"));
		Response response = client.post(form);
		if (response.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
			model.addAttribute("msg", response.readEntity(String.class));
		}
		return "applicationResponse";
	}
	
	@RequestMapping(value = "/viewApplications")
	public String showApplications(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException {
		WebClient client = WebClient.create(REST_URI + "/application/user/" + req.getSession().getAttribute("profileId"));
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_XML);
		Applications aps = client.get(Applications.class);
		for (Application app : aps.getApplications()) {
			System.out.println(app.getJobId());
			Posting p = getPosting(String.valueOf(app.getJobId()), req);
			app.setPosting(p);
		}
		model.addAttribute("applications", aps.getApplications());
		return "showApplications";
	}
	
	@RequestMapping(value = "/applicationdetail")
	public String showApplicationsDetails(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException {
		String appid = req.getParameter("appid");
		String queryString = "/application/" + appid;
		WebClient client = WebClient.create(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.accept(MediaType.APPLICATION_XML);
		Application app = client.get(Application.class);
		Posting p = getPosting(String.valueOf(app.getJobId()), req);
		app.setPosting(p);
		model.addAttribute("application", app);
		
		return "applicationDetail";
	}
	
	@RequestMapping(value = "/updateApplication")
	public String updateApplication(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException {
		String status = req.getParameter("status");
		if (!status.equalsIgnoreCase("submitted")) {
			model.addAttribute("updatemsg", "You are not allowed to modify your application anymore.");
			model.addAttribute("error",1);
		} else {
			String coverletter = req.getParameter("coverletter");
			model.addAttribute("modify", "yes");
			model.addAttribute("length", 1000 - coverletter.length());
		}
		return(showApplicationsDetails(model, req));
	}
	
	@RequestMapping(value = "/update")
	public String modifyApplication(ModelMap model, HttpServletRequest req) throws JsonProcessingException, IOException {
		String coverletter = req.getParameter("coverletter");
		String appid = req.getParameter("appid");
		String queryString = "/application/" + req.getSession().getAttribute("profileId") + "/" + appid;
		System.out.println(appid);
		WebClient client = WebClient.create(REST_URI + queryString);
		System.out.println(REST_URI + queryString);
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		Form form = new Form();
		form.param("coverletter", coverletter);
		client.put(form);
		model.addAttribute("updatemsg", "Successfully updated application.");
		return (showApplicationsDetails(model, req));
	}
	
	@RequestMapping(value = "/advancedSearch")
	public String advancedSearch() throws Exception {
		return "advancedSearch";
	}
	
	@RequestMapping(value = "/updateProfileForm")
	public String updateProfileForm() {
		return "updateProfileForm";
	}
	
	@RequestMapping(value = "/updateProfile")
	public String updateProfile(ModelMap model, HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		String curjob = req.getParameter("curjob");
		String skills = req.getParameter("skills");
		String experience = req.getParameter("experience");
		String education = req.getParameter("education");
		String address = req.getParameter("address");
		String dlno = req.getParameter("dlno");
		u.setCurrentPosition(curjob);
		u.setSkills(skills);
		u.setExperience(experience);
		u.setEducation(education);
		u.getPersonalDetails().setAddress(address);
		u.getPersonalDetails().setDriverLicenseNo(dlno);
				
		WebClient client = WebClient.create(REST_URI + "/user");
		client.header("SecurityKey", SECURITY_KEY);
		client.header("ShortKey", req.getSession().getAttribute("role"));
		client.type(MediaType.APPLICATION_XML);
		client.accept(MediaType.APPLICATION_XML);
		client.put(u, User.class);
		
		req.getSession().setAttribute("user", u);
		return "candidateHome";
	}
}
