package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Company {
	
	@XmlElement(name="Name")
	private String name;
	
	@XmlElement(name="Industry")
	private String industry;
	
	@XmlElement(name="Employees")
	private String employees;
	
	@XmlElement(name="Location")
	private String location;
	
	@XmlElement(name="_profileId")
	private int profileId;
	
	private String email;
	private String password;
	
	public Company() {
		
	}
	/*public Company(String email, String password) {
		this.email = email;
		this.password = password;
		this.profileId = email.hashCode();
	}*/
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void updateCompany(Company company) {
		this.name = company.name;
		this.industry = company.industry;
		this.employees = company.employees;
		this.location = company.location;
	}
}

