
package au.edu.unsw.soacourse.adrservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.edu.unsw.soacourse.adrservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ADRValidateFault_QNAME = new QName("http://adrservice.soacourse.unsw.edu.au", "ADRValidateFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.edu.unsw.soacourse.adrservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ADRValidateRequest }
     * 
     */
    public ADRValidateRequest createADRValidateRequest() {
        return new ADRValidateRequest();
    }

    /**
     * Create an instance of {@link ADRValidateResponse }
     * 
     */
    public ADRValidateResponse createADRValidateResponse() {
        return new ADRValidateResponse();
    }

    /**
     * Create an instance of {@link ServiceFaultType }
     * 
     */
    public ServiceFaultType createServiceFaultType() {
        return new ServiceFaultType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adrservice.soacourse.unsw.edu.au", name = "ADRValidateFault")
    public JAXBElement<ServiceFaultType> createADRValidateFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_ADRValidateFault_QNAME, ServiceFaultType.class, null, value);
    }

}
