package au.edu.unsw.soacourse.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import au.edu.unsw.soacourse.dao.HiringTeamDao;
import au.edu.unsw.soacourse.model.hiringteam.HiringTeams;
import au.edu.unsw.soacourse.model.hiringteam.Team;

public class HiringTeamResource {
	private HiringTeamDao dao = new HiringTeamDao();
	

	@GET
	@Path("{htid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeam(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("htid") String htid) {

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		Team team = dao.getHiringTeams().getTeam(htid);
		
		if (team != null) 
			return Response.ok(team).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	public Response createHiringTeam(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@FormParam("id") String id,
			@FormParam("reviewer1") String reviewer1,
			@FormParam("reviewer2") String reviewer2,
			@FormParam("reviewer3") String reviewer3,
			@FormParam("reviewer4") String reviewer4,
			@FormParam("reviewer5") String reviewer5) {

		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if (dao.getHiringTeams().contains(id)) 
			return Response.status(Status.BAD_REQUEST).entity("Hiring Team already exists").build();
		
		Team team = new Team();
		team.setHtid(id);
		
		if (reviewer1 != null && !reviewer1.isEmpty())
			team.setReviewer1(reviewer1);

		if (reviewer2 != null && !reviewer2.isEmpty())
			team.setReviewer2(reviewer2);

		if (reviewer3 != null && !reviewer3.isEmpty())
			team.setReviewer3(reviewer3);

		if (reviewer4 != null && !reviewer4.isEmpty())
			team.setReviewer4(reviewer4);

		if (reviewer5 != null && !reviewer5.isEmpty())
			team.setReviewer5(reviewer5);
		
		HiringTeams ht = dao.getHiringTeams();
		ht.addTeam(team);
		dao.setHiringTeams(ht);
		
		return Response.status(Status.CREATED).build();
	}
	

	@PUT
	@Path("/add/{htid}/{reviewer}")
	public Response addReviewer(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("htid") String htid,
			@PathParam("reviewer") String reviewer) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		if (!dao.getHiringTeams().contains(htid)) 
			return Response.status(Status.BAD_REQUEST).entity("Hiring Team does not exist").build();
		
		Team team = dao.getHiringTeams().getTeam(htid);
		if (team.setNewReviewer(reviewer))
			return Response.status(Status.OK).build();

		return Response.status(Status.BAD_REQUEST).entity("Team is full").build();
	}
	
	@PUT
	@Path("/remove/{htid}/{reviewer}")
	public Response removeReviewer(
			@HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey,
			@PathParam("htid") String htid,
			@PathParam("reviewer") String reviewer) {
		
		if (securityKey == null || !securityKey.equals("i-am-foundit") || 
				shortKey == null || !shortKey.matches("app-manager")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		if (!dao.getHiringTeams().contains(htid)) 
			return Response.status(Status.BAD_REQUEST).entity("Hiring Team does not exist").build();
		
		Team team = dao.getHiringTeams().getTeam(htid);
		if (team.removeReviewer(reviewer))
			return Response.status(Status.OK).build();

		return Response.status(Status.BAD_REQUEST).entity("Reviewer is not in team").build();
	}
	

}
