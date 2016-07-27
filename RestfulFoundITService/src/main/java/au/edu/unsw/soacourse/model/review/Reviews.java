package au.edu.unsw.soacourse.model.review;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Reviews")
@XmlAccessorType (XmlAccessType.FIELD)
public class Reviews {

	@XmlElement(name="Review")
	private ArrayList<Review> reviews;
	
	public Reviews() {
		this.setReviews(new ArrayList<Review>());
	}

	public Review getReview(String reviewId) {
		for (Review review : reviews) {
			if(review.getReviewId().equalsIgnoreCase(reviewId))
				return review;
		}
		
		return null;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public boolean contains(String reviewid) {
		for (Review review: this.reviews) {
			if (review.getReviewId().equals(reviewid)) {
				return true;
			}
		}
		return false;
	}
	
	public Reviews getReviewers(int reviewerid) {
		Reviews newReviews = new Reviews();
		
		for (Review review: this.reviews) {
			if (review.getReviewerId() == reviewerid) {
				newReviews.addReview(review);
			}
		}
		
		return newReviews;
	}
	
	public Reviews getApplications(int appid) {
		Reviews newReviews = new Reviews();
		
		for (Review review: this.reviews) {
			if (review.getAppId() == appid) {
				newReviews.addReview(review);
			}
		}
		
		return newReviews;
	}

}
