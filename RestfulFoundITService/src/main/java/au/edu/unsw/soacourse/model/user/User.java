package au.edu.unsw.soacourse.model.user;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="User")
@XmlAccessorType (XmlAccessType.FIELD)
public class User {

	@XmlElement(name="PersonalDetails")
	private PersonalDetails personaldetails;

	@XmlElement(name="CurrentPosition")
	private String currentposition;

	@XmlElement(name="Skills")
	private String skills;

	@XmlElement(name="Experience")
	private String experience;

	@XmlElement(name="Education")
	private String education;

	@XmlElement(name="_profileId")
	private int profileid;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.personaldetails = new PersonalDetails();
		this.profileid = Math.abs(email.hashCode());
	}

	public PersonalDetails getPersonalDetails() {
		return personaldetails;
	}

	public void setPersonalDetails(PersonalDetails personaldetails) {
		this.personaldetails = personaldetails;
	}

	public String getCurrentPosition() {
		return currentposition;
	}

	public void setCurrentPosition(String currentposition) {
		this.currentposition = currentposition;
	}
	
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getProfileId() {
		return profileid;
	}

	public void setProfileId(int profileid) {
		this.profileid = profileid;
	}
	
	public void updateUser(User user) {
		this.personaldetails.updatePD(user.personaldetails);
		this.currentposition = user.currentposition;
		this.education = user.education;
		this.experience = user.experience;
		this.skills = user.skills;
	}

}
