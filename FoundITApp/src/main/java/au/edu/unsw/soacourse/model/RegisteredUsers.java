package au.edu.unsw.soacourse.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import au.edu.unsw.soacourse.model.RegisteredUser;

@XmlRootElement(name = "RegisteredUsers")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegisteredUsers {
	@XmlElement(name = "Entry")
	private ArrayList<RegisteredUser> users;

	public ArrayList<RegisteredUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<RegisteredUser> users) {
		this.users = users;
	}
	
	public void addUser (RegisteredUser newUser) {
		this.users.add(newUser);
	}

}
