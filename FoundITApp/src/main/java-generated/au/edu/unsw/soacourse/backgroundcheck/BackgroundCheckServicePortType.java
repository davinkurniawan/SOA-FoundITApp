package au.edu.unsw.soacourse.backgroundcheck;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-05-31T22:54:34.875+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://soacourse.unsw.edu.au/backgroundcheck", name = "BackgroundCheckServicePortType")
@XmlSeeAlso({ObjectFactory.class, au.edu.unsw.soacourse.adrservice.ObjectFactory.class, au.edu.unsw.soacourse.dlservice.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BackgroundCheckServicePortType {

    @WebResult(name = "BackgroundCheckResponse", targetNamespace = "http://soacourse.unsw.edu.au/backgroundcheck", partName = "payload")
    @Action(input = "http://soacourse.unsw.edu.au/backgroundcheck/checkApplicantBackground", output = "http://soacourse.unsw.edu.au/backgroundcheck/BackgroundCheck/checkApplicantBackgroundResponse")
    @WebMethod(action = "http://soacourse.unsw.edu.au/backgroundcheck/checkApplicantBackground")
    public BackgroundCheckResponse checkApplicantBackground(
        @WebParam(partName = "payload", name = "BackgroundCheckRequest", targetNamespace = "http://soacourse.unsw.edu.au/backgroundcheck")
        BackgroundCheckRequest payload
    );
}
