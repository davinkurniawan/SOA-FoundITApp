package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.RegisteredUser;
import au.edu.unsw.soacourse.model.RegisteredUsers;

public class RegisteredUsersDao {
	
	private static final String XML_PATH = "xml/RegisteredUsers.xml";
	
	private RegisteredUsers users;
	
	public RegisteredUsers getRegisteredUsers()
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(RegisteredUsers.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			users = (RegisteredUsers) unmarshaller.unmarshal(xmlFile);
		}
		catch(JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void setRegisteredUsers(RegisteredUsers newUsers)
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(RegisteredUsers.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(newUsers, xmlFile);
		}
		catch (JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RegisteredUser getUser(String email, String password) {
		for (RegisteredUser u : users.getUsers()) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}
	
	public boolean isUserRegistered(String email) {
		if (this.users == null)
			this.getRegisteredUsers();
		for (RegisteredUser u : users.getUsers()) {
			if (u.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

}
