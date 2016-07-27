package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.edu.unsw.soacourse.model.company.CompanyProfiles;
import au.edu.unsw.soacourse.model.jobposting.Jobs;

public class CompanyDao {
	private static final String XML_PATH = "xml/Company_Profile.xml";
	
	private CompanyProfiles companies;
	public CompanyProfiles getCompanyProfiles()
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			System.out.println(xmlFile.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(CompanyProfiles.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			companies = (CompanyProfiles) unmarshaller.unmarshal(xmlFile);
			System.out.println(companies.getCompanies().size());
		}
		catch(JAXBException e)
		{
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}
	
	public void setCompanyProfiles(CompanyProfiles companyprofile)
	{
		try
		{
			File xmlFile = new File(this.getClass().getClassLoader().getResource(XML_PATH).toURI());
			JAXBContext context = JAXBContext.newInstance(CompanyProfiles.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(companyprofile, xmlFile);
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