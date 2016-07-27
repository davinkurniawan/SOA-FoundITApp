package au.edu.unsw.soacourse.model.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class PersonalDetails {

	@XmlElement(name="FirstName")
	private String firstname;

	@XmlElement(name="LastName")
	private String lastname;

	@XmlElement(name="Email")
	private String email;

	@XmlElement(name="Password")
	private String password;
	
	@XmlElement(name="DriverLicenseNo")
	private String driverlicenseno;
	
	@XmlElement(name="Address")
	private String address;
	
	public PersonalDetails() {
		
	}
	
	public PersonalDetails(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDriverLicenseNo() {
		return driverlicenseno;
	}

	public void setDriverLicenseNo(String driverlicenseno) {
		this.driverlicenseno = driverlicenseno;
	}
	
	public void updatePD(PersonalDetails pd) {
		this.address = pd.address;
		this.driverlicenseno = pd.driverlicenseno;
		this.email = pd.email;
		this.firstname = pd.firstname;
		this.lastname = pd.lastname;
		this.password = pd.password;
	}

}
