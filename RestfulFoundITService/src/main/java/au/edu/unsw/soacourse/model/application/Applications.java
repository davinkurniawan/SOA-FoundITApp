package au.edu.unsw.soacourse.model.application;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Applications")
@XmlAccessorType (XmlAccessType.FIELD)
public class Applications {

	@XmlElement(name="Application")
	private ArrayList<Application> applications;
	
	public Applications() {
		this.setApplications(new ArrayList<Application>());
	}
	
	public int size() {
		return this.applications.size();
	}

	public ArrayList<Application> getApplications() {
		return applications;
	}
	
	public void setApplications(ArrayList<Application> applications) {
		this.applications = applications;
	}
	
	public Application getApplication(long appid) {
		for (Application app : applications) {
			if(app.getAppId() == appid)
				return app;
		}
		
		return null;
	}
	
	public void setApplicationReviewed(long appid) {
		for (Application app: this.applications) {
			if (app.getAppId() == appid) 
				app.setStatus("reviewed");
		}
	}
	
	public void addApplication(Application app) {
		this.applications.add(app);
	}
	
	public void removeApplication(long appid) {
		int i = 0;
		for (i = 0; i <= this.applications.size(); i++) {
			if (i == this.applications.size())
				return;
			if (this.applications.get(i).getAppId() == appid)
				break;
		}
		this.applications.remove(i);
	}
	
	public boolean contains(long appid) {
		for (Application app: this.applications) {
			if (app.getAppId() == appid)
				return true;
		}
		return false;
	}
	
	public boolean containsJob(int jobid) {
		for (Application app: this.applications) {
			if (app.getJobId() == jobid)
				return true;
		}
		return false;
	}
	
	public boolean containsUserProfile(int profileid) {
		for (Application app: this.applications) {
			if (app.getProfileId() == profileid)
				return true;
		}
		return false;
	}
	
	public Applications getJobApplications(int jobid) {
		Applications newApplications = new Applications();
		
		for (Application app: this.applications) {
			if (app.getJobId() == jobid) 
				newApplications.addApplication(app);
		}
		
		return newApplications;
	}
	
	public Applications getUserApplications(int profileid) {
		Applications newApplications = new Applications();
		
		for (Application app: this.applications) {
			if (app.getProfileId() == profileid) 
				newApplications.addApplication(app);
		}
		
		return newApplications;
	}
	
	public boolean hasUserAppliedToJob(int jobid, int profileid) {
		for (Application app: this.applications) {
			if (app.equals(jobid, profileid))
				return true;
		}
		return false;
	}
	
}

