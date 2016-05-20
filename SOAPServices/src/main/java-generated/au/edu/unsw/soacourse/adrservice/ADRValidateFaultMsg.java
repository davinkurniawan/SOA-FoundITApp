
package au.edu.unsw.soacourse.adrservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-05-19T17:33:03.005+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "ADRValidateFault", targetNamespace = "http://adrservice.soacourse.unsw.edu.au")
public class ADRValidateFaultMsg extends Exception {
    
    private au.edu.unsw.soacourse.adrservice.ServiceFaultType adrValidateFault;

    public ADRValidateFaultMsg() {
        super();
    }
    
    public ADRValidateFaultMsg(String message) {
        super(message);
    }
    
    public ADRValidateFaultMsg(String message, Throwable cause) {
        super(message, cause);
    }

    public ADRValidateFaultMsg(String message, au.edu.unsw.soacourse.adrservice.ServiceFaultType adrValidateFault) {
        super(message);
        this.adrValidateFault = adrValidateFault;
    }

    public ADRValidateFaultMsg(String message, au.edu.unsw.soacourse.adrservice.ServiceFaultType adrValidateFault, Throwable cause) {
        super(message, cause);
        this.adrValidateFault = adrValidateFault;
    }

    public au.edu.unsw.soacourse.adrservice.ServiceFaultType getFaultInfo() {
        return this.adrValidateFault;
    }
}
