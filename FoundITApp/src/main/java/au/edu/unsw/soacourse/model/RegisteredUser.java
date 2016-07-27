package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class RegisteredUser {
	@XmlElement(name="Email")
	private String email;

	@XmlElement(name="Password")
	private String password;
	
	@XmlElement(name="Role")
	private String role;
	
	public RegisteredUser() {
		
	}

	public RegisteredUser(String email, String password, String role) {
		this.email = email;
		this.password = password;
		if (role.equals("manager")) {
			this.role = "app-manager";
		} else if (role.equals("reviewer")) {
			this.role = "app-reviewer";
		} else {
			this.role = "app-candidate";
		}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
