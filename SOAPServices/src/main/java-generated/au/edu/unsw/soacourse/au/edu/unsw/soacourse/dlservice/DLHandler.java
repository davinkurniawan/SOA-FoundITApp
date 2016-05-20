package au.edu.unsw.soacourse.dlservice;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DLHandler {
	private ArrayList<DriversLicense> dl_list;

	public DLHandler() {
		dl_list = new ArrayList<DriversLicense>();
	}

	public void parseDocument(Document doc) {
		NodeList dl_nodes = doc.getElementsByTagName("driver");
		for (int i = 0; i < dl_nodes.getLength(); i++) {
			DriversLicense dl = new DriversLicense();
			Node n = dl_nodes.item(i);
			if (n.getNodeName().equalsIgnoreCase("fullname"))
				dl.setFullName(n.getTextContent());
			else if (n.getNodeName().equalsIgnoreCase("dlno"))
				dl.setDLNumber(n.getTextContent());
			dl_list.add(dl);
		}
	}

	public boolean isFound(DriversLicense dl) {
		if (dl_list.contains(dl))
			return true;
		return false;
	}
}
