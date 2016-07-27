package au.edu.unsw.soacourse.model.jobposting;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType (XmlAccessType.FIELD)
public class Posting {
	
	@XmlElement(name="_jobId")
	private int jobid;
	
	@XmlElement(name="CompanyId")
	private int companyid;
	
	@XmlElement(name="PositionTitle")	
	private String positiontitle;
	
	@XmlElement(name="SalaryRate")
	private String salaryrate;
	
	@XmlElement(name="Location")
	private String location;
	
	@XmlElement(name="Details")
	private String details;
	
	@XmlElement(name="Status")
	private String status;
	
	public Posting() {
		System.out.println("inside posting constructor");
	}
	
	public Posting(int entryNum) {
		Date date = new Date();
		String toHash = date.toString() + String.valueOf(entryNum);
		this.jobid = Math.abs(toHash.hashCode());
		this.status = "open";
	}
	
	public int getJobId() {
		return jobid;
	}

	public void setJobId(int jobid) {
		this.jobid = jobid;
	}
	
	public int getCompanyId() {
		return companyid;
	}

	public void setCompanyId(int companyid) {
		this.companyid = companyid;
	}

	public String getPositionTitle() {
		return positiontitle;
	}

	public void setPositionTitle(String positiontitle) {
		this.positiontitle = positiontitle;
	}
	
	public String getSalaryRate() {
		return salaryrate;
	}

	public void setSalaryRate(String salaryrate) {
		this.salaryrate = salaryrate;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void updatePosting(Posting posting) {
		this.companyid = posting.getCompanyId();
		this.details = posting.getDetails();
		this.location = posting.getLocation();
		this.salaryrate = posting.getSalaryRate();
		this.status = posting.getStatus();
	}
}
