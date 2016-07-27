package au.edu.unsw.soacourse.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import au.edu.unsw.soacourse.dao.ApplicationDao;
import au.edu.unsw.soacourse.dao.JobsDao;
import au.edu.unsw.soacourse.dao.UserDao;
import au.edu.unsw.soacourse.model.application.Application;
import au.edu.unsw.soacourse.model.application.Applications;
@Path("/application")
public class ApplicationResource {
	private ApplicationDao dao = new ApplicationDao();
	
	@GET
	@Path("{appid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getApplication(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("appid") long appid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
		} else {
			
			if (dao.getApplications().contains(appid)) {
				Application app = dao.getApplications().getApplication(appid);
				response = Response.status(Status.OK).entity(app).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		}
		
		return response;
	}
	
	@GET
	@Path("/job/{jobid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getJobApplications(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("jobid") int jobid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Applications newApplications = dao.getApplications().getJobApplications(jobid);
			
			if (newApplications.size() == 0) {
				response = Response.status(Status.NOT_FOUND).entity("There are no applications for that job id").build();
				
			} else {
				response = Response.status(Status.OK).entity(newApplications).build();
				
			}
			 
		}
		
		return response;
	}
	
	@GET
	@Path("/user/{profileid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getUserApplications(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("profileid") int profileid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Applications newApplications = dao.getApplications().getUserApplications(profileid);
			
			if (newApplications.size() == 0) {
				response = Response.status(Status.NOT_FOUND).entity("There are no applications for that profile id").build();
				
			} else {
				response = Response.status(Status.OK).entity(newApplications).build();
				
			}
			 
		}
		
		return response;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getJobApplications(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			if (dao.getApplications().size() == 0) {
				response = Response.status(Status.NOT_FOUND).entity("There are currently no applications").build();
				
			} else {
				response = Response.status(Status.OK).entity(dao.getApplications()).build();
				
			}
		}
		
		
		return response;
	}
	
	@POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createApplication(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("jobid") int jobid,
			@FormParam("profileid") int profileid,
			@FormParam("coverletter") String coverletter) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-candidate"))
			return Response.status(Status.UNAUTHORIZED).build();	
	
		//check whether job exists
		JobsDao jobsdao = new JobsDao();
		if (!jobsdao.getJobs().contains(jobid))
			return Response.status(Status.BAD_REQUEST).entity("job does not exist").build();
		
		//check whether user profile exists
		UserDao userdao = new UserDao();
		if (!userdao.getUserProfiles().contains(profileid))
			return Response.status(Status.BAD_REQUEST).entity("user does not exist").build();
		
		//checks whether user has applied for that job before
		Applications apps = dao.getApplications();
		if (apps.hasUserAppliedToJob(jobid, profileid))
			return Response.status(Status.BAD_REQUEST).entity("Cannot apply to the same job twice").build();
		
		Application newApp = new Application(jobid, profileid, coverletter);
		newApp.setStatus("Submitted");
		apps.addApplication(newApp);
		dao.setApplication(apps);
		
		Response response = Response.status(Status.OK).build();//.entity(newApp).build();
		return response;
	}
	
	
	//TODO: Put only for applicants to change cover letter
	// Reviewers should also be able to change(?) status
	@PUT
	@Path("{profileid}/{appid}")
	public Response updateApplication(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("profileid") int profileid,
			@PathParam("appid") long appid,
			@FormParam("coverletter") String coverletter) {
		
		System.out.println("updating application" + profileid);
		System.out.println("updating application" + appid);
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer"))
			return Response.status(Status.UNAUTHORIZED).build();	
		
		//check if that user has submitted an application
		if (!dao.getApplications().contains(appid)) {
			System.out.println("application does not exist");
			return Response.status(Status.BAD_REQUEST).entity("Application does not exist").build();
		}
		Applications applications = dao.getApplications();
		Application app = applications.getApplication(appid);
		
		//check whether that application was made by the user
		if (app.getProfileId() != profileid) {
			System.out.println("Application was not made by user");
			return Response.status(Status.BAD_REQUEST).entity("Application was not made by user").build();
		}
		
		//check application's status
		if (!app.getStatus().equalsIgnoreCase("submitted")) {
			System.out.println("Application is no longer open and cannot be modified");
			return Response.status(Status.BAD_REQUEST).entity("Application is no longer open and cannot be modified").build();
		}
		applications.getApplication(appid).setCoverLetter(coverletter);
		System.out.println(applications.getApplication(appid).getCoverLetter());
		dao.setApplication(applications);
		
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("{profileid}/{appid}")
	public Response deleteApplication(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("profileid") int profileid,
			@PathParam("appid") long appid) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") ||
				shortKey == null || !shortKey.matches("app-candidate"))
			return Response.status(Status.UNAUTHORIZED).build();	
		
		Applications applications = dao.getApplications();
		Application app = applications.getApplication(appid);
		
		//check whether that application was made by the user
		if (app.getProfileId() != profileid) 
			return Response.status(Status.BAD_REQUEST).entity("Application was not made by user").build();
		
		applications.removeApplication(appid);
		
		return Response.status(Status.OK).build();
		
		
		
	}
}
