package au.edu.unsw.soacourse.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import au.edu.unsw.soacourse.dao.*;
import au.edu.unsw.soacourse.model.user.PersonalDetails;
import au.edu.unsw.soacourse.model.user.User;
import au.edu.unsw.soacourse.model.user.UserProfiles;

@Path("/user")
public class UserResource {
	private UserDao dao = new UserDao();

	@GET
	@Path("{profileid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getUserDetails(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("profileid") int profileid) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		Response response;
		User user = dao.getUserProfiles().getUser(profileid);
		
		if (user != null) 
			response = Response.ok(Status.OK).entity(user).build();
		else
			response = Response.status(Status.NOT_FOUND).build();
		
		return response;
	}
	
	@POST
	public Response createUser(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("dlno") String dlno,
			@FormParam("address") String address) {
		Response response;
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			response = Response.status(Status.UNAUTHORIZED).build();
		} else {
			
			User newUser = new User(email, password);
			PersonalDetails pd = new PersonalDetails();
			pd.setFirstName(firstname);
			pd.setLastName(lastname);
			pd.setAddress(address);
			pd.setDriverLicenseNo(dlno);
			newUser.setPersonalDetails(pd);
			
			if (dao.getUserProfiles().contains(newUser.getProfileId())) {
				response = Response.status(Status.BAD_REQUEST).entity("User already exists").build();
			} else {
				UserProfiles userprofile = dao.getUserProfiles();
				userprofile.addUser(newUser);
				dao.setUserProfiles(userprofile);
				response = Response.status(Status.CREATED).build();
			}
		}
		
		return response;	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateUser(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			User user) {
		Response response;

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate")) {
			
			return Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			if (dao.getUserProfiles().contains(user.getProfileId())) {
				UserProfiles up = dao.getUserProfiles();
				up.getUser(user.getProfileId()).updateUser(user);
				dao.setUserProfiles(up);
				response = Response.status(Status.OK).entity(user).build();
				System.out.println("user updated");
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		}
		
		return response;
	}
	
	@DELETE
	@Path("{profileid}")
	public Response deleteUser(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("profileid") int profileid) {
		Response response;

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-candidate|app-reviewer|app-manager")) {
			
			return Response.status(Status.UNAUTHORIZED).build();
			
		} else {
			
			if (dao.getUserProfiles().contains(profileid)) {
				UserProfiles up = dao.getUserProfiles();
				up.removeUser(profileid);
				dao.setUserProfiles(up);
				response = Response.status(Status.OK).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
			
		}
		
		return response;
	}
	
}
