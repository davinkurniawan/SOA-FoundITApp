package au.edu.unsw.soacourse.dlservice;

import java.io.IOException;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebService(endpointInterface = "au.edu.unsw.soacourse.dlservice.DLService")
public class DLServiceImpl implements DLService {
	private ObjectFactory factory = new ObjectFactory();
	private DLHandler handler = new DLHandler();

	@Override
	public DLValidateResponse dlValidate(DLValidateRequest parameters)
			throws DLValidateFaultMsg {
		String fullName = parameters.getFullName();
		String DLNumber = parameters.getDLNumber();
		if (fullName == null || fullName.isEmpty()) {
			String code = "ERR_EMPTY_FIELD";
			String msg = "Inputs cannot be empty";
			ServiceFaultType fault = factory.createServiceFaultType();
			fault.setErrcode(code);
			fault.setErrtext(msg);
			throw new DLValidateFaultMsg(msg, fault);
		}
		DriversLicense dl = new DriversLicense(fullName, DLNumber);
		InputSource xmlFile = new InputSource(this.getClass().getClassLoader()
				.getResourceAsStream("xml/DL_database"));
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
		DLValidateResponse response = factory.createDLValidateResponse();
		if (handler.isFound(dl))
			response.setValidationMsg("Driver license is valid.");
		else
			response.setValidationMsg("Driver license does not exist.");

		return response;
	}

}
