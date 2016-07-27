package au.edu.unsw.soacourse.model.review;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import au.edu.unsw.soacourse.dao.ApplicationDao;

@XmlAccessorType (XmlAccessType.FIELD)
public class Review {
	@XmlElement(name="_reviewId")
	private String reviewid;
	
	@XmlElement(name="_appId")
	private int appid;
	
	@XmlElement(name="_reviewerId")
	private int reviewerid;
	
	@XmlElement(name="Comments")
	private String comments;
	
	@XmlElement(name="Decisions")
	private String decision;
	
	public Review(int appid, int reviewerid, String comments, String decision) {
		this.reviewid = String.valueOf(appid) + String.valueOf(reviewerid);
		
		this.appid = appid;
		ApplicationDao appdao = new ApplicationDao();
		appdao.getApplications().setApplicationReviewed(appid);
		
		this.reviewerid = reviewerid;
		this.comments = comments;
		this.decision = decision;
	}
	
	public String getReviewId() {
		return reviewid;
	}

	public void setReviewId(String reviewid) {
		this.reviewid = reviewid;
	}
	
	public int getAppId() {
		return appid;
	}

	public void setAppId(int appid) {
		this.appid = appid;
	}

	public int getReviewerId() {
		return reviewerid;
	}

	public void setReviewerId(int reviewerid) {
		this.reviewerid = reviewerid;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getDecisions() {
		return decision;
	}

	public void setDecisions(String decision) {
		this.decision = decision;
	}
}
