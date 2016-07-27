package au.edu.unsw.soacourse.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import au.edu.unsw.soacourse.dao.CompanyDao;
import au.edu.unsw.soacourse.dao.JobsDao;
import au.edu.unsw.soacourse.model.jobposting.*;

@Path("/jobs")
public class JobPostingResource {
	private JobsDao dao = new JobsDao();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAllJobPostings(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Jobs jobs = dao.getJobs();
			response = Response.status(Status.OK).entity(jobs).build();
			
		}
		
		return response;
	}
	

	@GET
	@Path("{jobid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getJobPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("jobid") int jobid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {

			Posting post = dao.getJobs().getPosting(jobid);
			if (post != null) {
				System.out.println(post.getDetails());
				response = Response.ok(Status.OK).entity(post).build();
			} else
				response = Response.status(Status.NOT_FOUND).build();	
		}
		
		return response;
	}
	
	@GET
	@Path("/companyjobs/{companyid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getCompanyPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("companyid") int companyid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Jobs postings = dao.getJobs().getCompanyPosting(companyid);
			response = Response.ok(Status.OK).entity(postings).build();
		}
		
		return response;
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_XML)
	public Response searchJobPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@Context UriInfo info) {
		Response response;
		System.out.println("test");
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			System.out.println(securityKey);
			System.out.println(shortKey);
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Jobs result = dao.getJobs();
			String location = info.getQueryParameters().getFirst("location");
			String company = info.getQueryParameters().getFirst("company");
			String positiontitle = info.getQueryParameters().getFirst("positiontitle");
			String salaryrate = info.getQueryParameters().getFirst("salaryrate");
			String details = info.getQueryParameters().getFirst("details");
			String status = info.getQueryParameters().getFirst("status");
			
			System.out.println("query parameters parsed");
			
			if (location != null && !location.trim().isEmpty()) {
				result = result.searchJobLocation(location);
				System.out.println("location njobs = " + result.size());
			}
			if (company != null && !company.trim().isEmpty()) {
				result = result.searchJobCompany(company);
				System.out.println("company njobs = " + result.size());
			}
			if (positiontitle != null && !positiontitle.trim().isEmpty()) {
				result = result.searchJobPositionTitle(positiontitle);
				System.out.println("positiontitle njobs = " + result.size());
			}
			if (salaryrate != null && !salaryrate.trim().isEmpty()) {
				result = result.searchJobSalaryRate(salaryrate);
				System.out.println("salaryrate njobs = " + result.size());
			}
			if (details != null && !details.trim().isEmpty()) {
				result = result.searchJobDetails(details);
				System.out.println("details njobs = " + result.size());
			}
			if (status != null && !status.trim().isEmpty()) {
				result = result.searchJobStatus(status);
				System.out.println("status njobs = " + result.size());
			}
			
			System.out.println("njobs = " + result.size());
			response = Response.status(Status.OK).entity(result).build();
		}
		
		return response;
	}
	

	@POST
	public Response createJobPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("companyid") int companyid,
			@FormParam("salaryrate") String salaryrate,
			@FormParam("location") String location,
			@FormParam("details") String details,
			@FormParam("positiontitle") String positiontitle) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			Posting newPosting = new Posting(dao.getJobs().size());
			newPosting.setCompanyId(companyid);
			newPosting.setDetails(details);
			newPosting.setLocation(location);
			newPosting.setSalaryRate(salaryrate);
			newPosting.setPositionTitle(positiontitle);
			
			//check if company exists
			CompanyDao companydao = new CompanyDao();
			if (!companydao.getCompanyProfiles().contains(companyid))
				return Response.status(Status.BAD_REQUEST).entity("company does not exist").build();
			
			//check if job posting already exists
			if (dao.getJobs().contains(newPosting.getJobId())) {
				response = Response.status(Status.BAD_REQUEST).entity("Job posting already exists").build();
			} else {
				Jobs jobpostings = dao.getJobs();
				jobpostings.addPosting(newPosting);
				dao.setJob(jobpostings);
				
				// TODO change client name
				response = Response.status(Status.CREATED).build();
			}
		}
		
		return response;
	}
	
	@PUT
	@Path("{jobid}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateJobPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("jobid") int jobid,
			Posting update) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			if (dao.getJobs().contains(jobid)) {
				dao.getJobs().getPosting(jobid).updatePosting(update);
				response = Response.status(Status.OK).build();
				
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
			
		}
		
		return response;
	}
	
	@DELETE
	@Path("{jobid}")
	public Response deleteJobPosting(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("jobid") int jobid) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			if (dao.getJobs().contains(jobid)) {
				Jobs jobs = dao.getJobs();
				jobs.removePosting(jobid);
				dao.setJob(jobs);;
				response = Response.status(Status.OK).build();
				
			} else {
				response = Response.status(Status.NOT_FOUND).build();
				
			}
			
		}
		
		return response;
	}
}
