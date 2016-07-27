package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.jobposting.Jobs;

public class JobsDao {
	private static final String XML_PATH = "xml/Job_Posting.xml";
	
	private Jobs jobs;
	public Jobs getJobs()
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(Jobs.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			jobs = (Jobs) unmarshaller.unmarshal(xmlFile);
		}
		catch(JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobs;
	}
	
	public void setJob(Jobs job)
	{
		
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(Jobs.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(job, xmlFile);
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