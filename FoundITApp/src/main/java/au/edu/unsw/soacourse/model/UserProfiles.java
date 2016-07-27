package au.edu.unsw.soacourse.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Profiles")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserProfiles {

	@XmlElement(name="User")
	private ArrayList<User> users;

	public UserProfiles() {
		this.setUsers(new ArrayList<User>());
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public boolean contains(int profileid) {
		for (User user: users) {
			if (user.getProfileId() == profileid)
				return true;
		}
		return false;
	}

	public User getUser(int profileid) {
		for (User user: users) {
			if(user.getProfileId() == profileid)
				return user;
		}
		return null;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(int profileid) {
		int i = 0;
		for (i = 0; i <= users.size(); i++) {
			if (i == users.size())
				break;
			
			if (users.get(i).getProfileId() == profileid)
				break;
		}
		
		if (i == users.size())
			return;
		
		users.remove(i);
	}
}
