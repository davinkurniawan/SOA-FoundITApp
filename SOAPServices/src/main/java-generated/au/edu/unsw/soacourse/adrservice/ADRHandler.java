package au.edu.unsw.soacourse.adrservice;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.edu.unsw.soacourse.dlservice.DriversLicense;

public class ADRHandler {
	private ArrayList<Address> adr_list;
	
	public ADRHandler() {
		this.adr_list = new ArrayList<Address>();
	}
	
	public void parseDocument(Document doc) {
		NodeList person_nodes = doc.getElementsByTagName("person");
		for (int j = 0; j < person_nodes.getLength(); j++) {
			Node pn = person_nodes.item(j);
			NodeList adr_nodes = pn.getChildNodes();
			Address adr = new Address();
			
			for (int i = 0; i < adr_nodes.getLength(); i++) {
				Node n = adr_nodes.item(i);
				
				if (n.getNodeName().equalsIgnoreCase("fullname")) {
					adr.setFullName(n.getTextContent());
					System.out.println("fullname: " + n.getTextContent());
				}
				if (n.getNodeName().equalsIgnoreCase("address")) {
					adr.setAddress(n.getTextContent());
					System.out.println("address: " + n.getTextContent());
				}
				
			}
			System.out.println();
			adr_list.add(adr);
		}
	}

	public boolean isFound(Address adr) {
		for(Address a : adr_list) {
			if (a.getFullName().equals(adr.getFullName())
					&& adr.getAddress().equals(adr.getAddress())) {
				return true;
			}
		}
		return false;
	}
}
