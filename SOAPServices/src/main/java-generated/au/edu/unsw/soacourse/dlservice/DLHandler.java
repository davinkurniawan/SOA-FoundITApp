package au.edu.unsw.soacourse.dlservice;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DLHandler {
	private ArrayList<DriversLicense> dl_list;

	public DLHandler() {
		dl_list = new ArrayList<DriversLicense>();
	}

	public void parseDocument(Document doc) {
		NodeList drive_nodes = doc.getElementsByTagName("driver");
		for (int j = 0; j < drive_nodes.getLength(); j++) {
			Node dn = drive_nodes.item(j);
			NodeList dl_nodes = dn.getChildNodes();
			DriversLicense dl = new DriversLicense();
			
			for (int i = 0; i < dl_nodes.getLength(); i++) {
				Node n = dl_nodes.item(i);
				
				if (n.getNodeName().equalsIgnoreCase("fullname")) {
					dl.setFullName(n.getTextContent());
				}
				if (n.getNodeName().equalsIgnoreCase("dlno")) {
					dl.setDLNumber(n.getTextContent());
				}
				
			}

			dl_list.add(dl);
		}
	}

	public boolean isFound(DriversLicense dl) {
		for(DriversLicense d : dl_list) {
			if (d.getFullName().equals(dl.getFullName())
					&& d.getDLNumber().equals(dl.getDLNumber())) {
				return true;
			}
		}
		return false;
	}
}
