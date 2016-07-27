package au.edu.unsw.soacourse.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import au.edu.unsw.soacourse.dao.*;
import au.edu.unsw.soacourse.model.company.Company;
import au.edu.unsw.soacourse.model.company.CompanyProfiles;

@Path("/company")
public class CompanyResource {
	private CompanyDao dao = new CompanyDao();

	@GET
	@Path("{profileid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanyDetails(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("profileid") int profileid) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		Response response;
		Company company = dao.getCompanyProfiles().getCompany(profileid);
		
		if (company != null) 
			response = Response.ok(company).build();
		else
			response = Response.status(Status.NOT_FOUND).build();
		
		return response;
	}
	
	@POST
	public Response createCompany(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("name") String name,
			@FormParam("location") String location,
			@FormParam("industry") String industry,
			@FormParam("employees") String employees,
			@FormParam("email") String email) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
		} else {
			
			Company newCompany = new Company();
			newCompany.setName(name);
			newCompany.setIndustry(industry);
			newCompany.setEmployees(employees);
			newCompany.setLocation(location);
			newCompany.setProfileId(Math.abs(email.hashCode()));
			if (dao.getCompanyProfiles().contains(newCompany.getProfileId())) {
				response = Response.status(Status.BAD_REQUEST).entity("Company already exists").build();
			} else {
				CompanyProfiles companyprofile = dao.getCompanyProfiles();
				companyprofile.addCompany(newCompany);
				dao.setCompanyProfiles(companyprofile);
				
				response = Response.status(Status.CREATED).build();
			}
		}
		
		return response;
	}
	
	@PUT
	@Path("{profileid}")
	public Response updateCompany(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("profileid") int profileid,
			Company company) {
		Response response;

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			
			return Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			if (dao.getCompanyProfiles().contains(profileid)) {
				dao.getCompanyProfiles().getCompany(profileid).updateCompany(company);
				response = Response.status(Status.OK).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		}
		
		return response;
	}
	
	@DELETE
	@Path("{profileid}")
	public Response deleteCompany(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("profileid") int profileid) {
		Response response;

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			
			return Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			CompanyProfiles companyprofiles = dao.getCompanyProfiles();
			if (companyprofiles.contains(profileid)) {
				companyprofiles.removeCompany(profileid);
				dao.setCompanyProfiles(companyprofiles);
				response = Response.status(Status.OK).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
			
		}
		
		return response;
	}
	
}