package au.edu.unsw.soacourse.model.hiringteam;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Team {
	@XmlElement(name="CompanyProfile")
	private String htid;

	@XmlElement(name="Reviewer1")
	private String reviewer1;
	@XmlElement(name="Reviewers")
	private String reviewer2;
	@XmlElement(name="Reviewers")
	private String reviewer3;
	@XmlElement(name="Reviewers")
	private String reviewer4;
	@XmlElement(name="Reviewers")
	private String reviewer5;
	
	public Team() {
		
	}
	
	public String getHtid() {
		return this.htid;
	}
	
	public void setHtid(String htid) {
		this.htid = htid;
	}
	
	public String getReviewer1() {
		return this.reviewer1;
	}
	
	public void setReviewer1(String reviewer1) {
		this.reviewer1 = reviewer1;
	}
	
	public String getReviewer2() {
		return this.reviewer2;
	}
	
	public void setReviewer2(String reviewer2) {
		this.reviewer2 = reviewer2;
	}
	
	public String getReviewer3() {
		return this.reviewer3;
	}
	
	public void setReviewer3(String reviewer3) {
		this.reviewer3 = reviewer3;
	}
	
	public String getReviewer4() {
		return this.reviewer4;
	}
	
	public void setReviewer4(String reviewer4) {
		this.reviewer4 = reviewer4;
	}

	public String getReviewer5() {
		return this.reviewer5;
	}
	
	public void setReviewer5(String reviewer5) {
		this.reviewer5 = reviewer5;
	}
	
	public ArrayList<String> getAllReviewers() {
		ArrayList<String> reviewers = new ArrayList<String>();
		
		if (reviewer1 != null && !reviewer1.isEmpty())
			reviewers.add(this.reviewer1);

		if (reviewer2 != null && !reviewer2.isEmpty())
			reviewers.add(this.reviewer2);

		if (reviewer3 != null && !reviewer3.isEmpty())
			reviewers.add(this.reviewer3);

		if (reviewer4 != null && !reviewer4.isEmpty())
			reviewers.add(this.reviewer4);

		if (reviewer5 != null && !reviewer5.isEmpty())
			reviewers.add(this.reviewer5);
		
		return reviewers;
	}
	
	public boolean setNewReviewer(String reviewer) {

		if (reviewer1 == null || reviewer1.isEmpty()) {
			reviewer1 = reviewer;
		} else if (reviewer2 == null || reviewer2.isEmpty()) {
			reviewer2 = reviewer;
		} else if (reviewer3 == null || reviewer3.isEmpty()) {
			reviewer3 = reviewer;
		} else if (reviewer4 == null || reviewer4.isEmpty()) {
			reviewer4 = reviewer;
		} else if (reviewer5 == null || reviewer5.isEmpty()) {
			reviewer5 = reviewer;
		} else {
			return false;
		}
		
		return true;
	}

	public boolean removeReviewer(String reviewer) {

		if (reviewer1 == null || reviewer1.equals(reviewer)) {
			reviewer1 = "";
		} else if (reviewer2 == null || reviewer2.equals(reviewer)) {
			reviewer2 = "";
		} else if (reviewer3 == null || reviewer3.equals(reviewer)) {
			reviewer3 = "";
		} else if (reviewer4 == null || reviewer4.equals(reviewer)) {
			reviewer4 = "";
		} else if (reviewer5 == null || reviewer5.equals(reviewer)) {
			reviewer5 = "";
		} else {
			return false;
		}
		
		return true;
	}
}
