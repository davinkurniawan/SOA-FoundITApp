package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.company.CompanyProfiles;
import au.edu.unsw.soacourse.model.user.UserProfiles;

public class UserDao {
	private static final String XML_PATH = "xml/User_Profile.xml";
	
	private UserProfiles users;
	public UserProfiles getUserProfiles()
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(UserProfiles.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			users = (UserProfiles) unmarshaller.unmarshal(xmlFile);
			System.out.println(users.getUsers().size());
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
	
	public void setUserProfiles(UserProfiles userprofiles)
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(UserProfiles.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(userprofiles, xmlFile);
		}
		catch (JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
