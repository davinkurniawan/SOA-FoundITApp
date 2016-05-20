
package au.edu.unsw.soacourse.dlservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-05-19T17:33:02.380+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "DLValidateFault", targetNamespace = "http://dlservice.soacourse.unsw.edu.au")
public class DLValidateFaultMsg extends Exception {
    
    private au.edu.unsw.soacourse.dlservice.ServiceFaultType dlValidateFault;

    public DLValidateFaultMsg() {
        super();
    }
    
    public DLValidateFaultMsg(String message) {
        super(message);
    }
    
    public DLValidateFaultMsg(String message, Throwable cause) {
        super(message, cause);
    }

    public DLValidateFaultMsg(String message, au.edu.unsw.soacourse.dlservice.ServiceFaultType dlValidateFault) {
        super(message);
        this.dlValidateFault = dlValidateFault;
    }

    public DLValidateFaultMsg(String message, au.edu.unsw.soacourse.dlservice.ServiceFaultType dlValidateFault, Throwable cause) {
        super(message, cause);
        this.dlValidateFault = dlValidateFault;
    }

    public au.edu.unsw.soacourse.dlservice.ServiceFaultType getFaultInfo() {
        return this.dlValidateFault;
    }
}