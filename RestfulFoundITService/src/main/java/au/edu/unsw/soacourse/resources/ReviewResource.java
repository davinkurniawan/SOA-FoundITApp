package au.edu.unsw.soacourse.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import au.edu.unsw.soacourse.dao.ApplicationDao;
import au.edu.unsw.soacourse.dao.ReviewDao;
import au.edu.unsw.soacourse.dao.UserDao;
import au.edu.unsw.soacourse.model.review.Review;
import au.edu.unsw.soacourse.model.review.Reviews;

@Path("/review")
public class ReviewResource {
	private ReviewDao dao = new ReviewDao();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAllReviews(
			@HeaderParam("Key") String headerKey) {
		
		if (headerKey == null /* TODO: specify which header key types can access it */) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		Reviews review = dao.getReviews();
		
		return Response.status(Status.OK).entity(review).build();
	}
	
	@GET
	@Path("{reviewid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getReview(
			@HeaderParam("Key") String headerKey,
			@PathParam("reviewid") String reviewid) {
		
		if (headerKey == null /* TODO: specify which header key types can access it */) 
			return Response.status(Status.UNAUTHORIZED).build();
		
		Review review = dao.getReviews().getReview(reviewid);
		
		if (review == null) 
			return Response.status(Status.NOT_FOUND).entity("Review with specified id does not exist").build();
		
		return Response.status(Status.OK).entity(review).build();	
	}
	
	@GET
	@Path("reviewer/{reviewerid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getReviewersReviews(
			@HeaderParam("Key") String headerKey,
			@PathParam("reviewerid") int reviewerid) {
		
		if (headerKey == null /* TODO: specify which header key types can access it */) 
			return Response.status(Status.UNAUTHORIZED).build();
		
		Reviews reviewerReviews = dao.getReviews().getReviewers(reviewerid);
		return Response.status(Status.OK).entity(reviewerReviews).build();
	}
	
	@GET
	@Path("application/{appid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getApplicationReviews(
			@HeaderParam("Key") String headerKey,
			@PathParam("appid") int appid) {
		
		if (headerKey == null /* TODO: specify which header key types can access it */) 
			return Response.status(Status.UNAUTHORIZED).build();
		
		Reviews reviewerReviews = dao.getReviews().getApplications(appid);
		return Response.status(Status.OK).entity(reviewerReviews).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createReview(
			@HeaderParam("Key") String headerKey,
			@FormParam("reviewerid") int reviewerid,
			@FormParam("appid") int appid,
			@FormParam("comments") String comments,
			@FormParam("decision") String decision) {

		if (headerKey == null /* TODO: specify which header key types can access it */) 
			return Response.status(Status.UNAUTHORIZED).build();
		
		//check if reviewid is a valid user profile id
		UserDao userdao = new UserDao();
		if (!userdao.getUserProfiles().contains(reviewerid))
			return Response.status(Status.BAD_REQUEST).entity("Reviewer is not a valid user").build();
		
		//check if appis is a valid application id
		ApplicationDao appdao = new ApplicationDao();
		if (!appdao.getApplications().contains(appid))
			return Response.status(Status.BAD_REQUEST).entity("Application does not exist").build();
		
		Reviews reviews = dao.getReviews();
		Review newReview = new Review(appid, reviewerid, comments, decision);
		reviews.addReview(newReview);
		dao.setReviews(reviews);

		// TODO change client name
		String responseUri = "http://localhost:8080/clientName/review/" + newReview.getReviewId();
		return Response.status(Status.CREATED).entity(responseUri).build();
		
	}
	

}
