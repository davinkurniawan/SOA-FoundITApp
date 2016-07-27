package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Application")
@XmlAccessorType (XmlAccessType.FIELD)
public class Application {

	@XmlElement(name="_appId")
	private long appid;

	@XmlElement(name="_jobId")
	private int jobid;

	@XmlElement(name="_profileId")
	private int profileid;

	@XmlElement(name="CoverLetter")
	private String coverletter;

	@XmlElement(name="Status")
	private String status;
	
	private Posting posting;
	
	private User user;
	
	private String autoCheckResponse;
	
	private String autoCheckDetails;
	
	public Application() {
		
	}
	
	public Application(int jobid, int profileid, String coverletter) {
		String appIdString = String.valueOf(jobid) + String.valueOf(profileid);
		appid = Long.parseLong(appIdString);
		this.jobid = jobid;
		this.profileid = profileid;
		this.coverletter = coverletter;
		this.status = "open";
	}
	
	public long getAppId() {
		return appid;
	}
	
	public boolean equals(int jobid, int profileid) {
		String appIdString = String.valueOf(jobid) + String.valueOf(profileid);
		System.out.println(appIdString);
		long appid = Long.parseLong(appIdString);
		
		if (this.appid == appid)
			return true;
		return false;
	}

	public int getJobId() {
		return jobid;
	}

	public void setJobId(int jobid) {
		this.jobid = jobid;
	}

	public int getProfileId() {
		return profileid;
	}

	public void setProfileId(int profileid) {
		this.profileid = profileid;
	}
	
	public String getCoverLetter() {
		return coverletter;
	}

	public void setCoverLetter(String coverletter) {
		this.coverletter = coverletter;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Posting getPosting() {
		return posting;
	}

	public void setPosting(Posting posting) {
		this.posting = posting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the autoCheckResponse
	 */
	public String getAutoCheckResponse() {
		return autoCheckResponse;
	}

	/**
	 * @param autoCheckResponse the autoCheckResponse to set
	 */
	public void setAutoCheckResponse(String autoCheckResponse) {
		this.autoCheckResponse = autoCheckResponse;
	}

	/**
	 * @return the autoCheckDetails
	 */
	public String getAutoCheckDetails() {
		return autoCheckDetails;
	}

	/**
	 * @param autoCheckDetails the autoCheckDetails to set
	 */
	public void setAutoCheckDetails(String autoCheckDetails) {
		this.autoCheckDetails = autoCheckDetails;
	}

}
