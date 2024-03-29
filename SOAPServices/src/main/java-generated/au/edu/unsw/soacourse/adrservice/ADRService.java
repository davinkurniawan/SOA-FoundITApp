package au.edu.unsw.soacourse.adrservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-05-31T22:56:00.108+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://adrservice.soacourse.unsw.edu.au", name = "ADRService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ADRService {

    @WebResult(name = "ADRValidateResponse", targetNamespace = "http://adrservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(operationName = "ADRValidate", action = "http://dlservice.soacourse.unsw.edu.au/ADRValidate")
    public ADRValidateResponse adrValidate(
        @WebParam(partName = "parameters", name = "ADRValidateRequest", targetNamespace = "http://adrservice.soacourse.unsw.edu.au")
        ADRValidateRequest parameters
    ) throws ADRValidateFaultMsg;
}
