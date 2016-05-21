package au.edu.unsw.soacourse.adrservice;

import java.io.IOException;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebService(endpointInterface = "au.edu.unsw.soacourse.adrservice.ADRService")
public class ADRServiceImpl implements ADRService {
	private ObjectFactory factory = new ObjectFactory();
	private ADRHandler handler = new ADRHandler();

	@Override
	public ADRValidateResponse adrValidate(ADRValidateRequest parameters)
			throws ADRValidateFaultMsg {
		String fullname = parameters.getFullName();
		String address = parameters.getAddress();
		
		if (fullname == null || fullname.isEmpty()) {
			String code = "ERR_EMPTY_FIELD";
			String msg = "Inputs cannot be empty";
			
			ServiceFaultType fault = factory.createServiceFaultType();
			fault.setErrcode(code);
			fault.setErrtext(msg);
			
			throw new ADRValidateFaultMsg(msg, fault);
		}
		
		Address adr = new Address(fullname, address);
		InputSource xmlFile = new InputSource(this.getClass().getClassLoader()
				.getResourceAsStream("xml/ADR_database.xml"));
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			handler.parseDocument(doc);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ADRValidateResponse response = factory.createADRValidateResponse();
		if (handler.isFound(adr)) 
			response.setValidationMsg("Address is valid");
		else 
			response.setValidationMsg("Address does not exist");
		
		return response;
	}

}
