package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.hiringteam.HiringTeams;
import au.edu.unsw.soacourse.model.jobposting.Jobs;

public class HiringTeamDao {
	private static final String XML_PATH = "xml/Hiring_Team.xml";
	private HiringTeams hiringTeams;
	
	public HiringTeams getHiringTeams()
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(HiringTeams.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			hiringTeams = (HiringTeams) unmarshaller.unmarshal(xmlFile);
		}
		catch(JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hiringTeams;
	}
	
	public void setHiringTeams(HiringTeams entry)
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(HiringTeams.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(hiringTeams, xmlFile);
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