package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.jobposting.Jobs;
import au.edu.unsw.soacourse.model.review.Reviews;

public class ReviewDao {
	private static final String XML_PATH = "xml/Reviews.xml";
	private Reviews reviews;
	
	public Reviews getReviews()
	{	
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(Reviews.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			reviews = (Reviews) unmarshaller.unmarshal(xmlFile);
		}
		catch(JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reviews;
	}
	
	public void setReviews(Reviews review)
	{	
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(Reviews.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(review, xmlFile);
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