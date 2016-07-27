package au.edu.unsw.soacourse.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name="Jobs")
@XmlAccessorType (XmlAccessType.FIELD)
public class Jobs {
	
	@XmlElement(name="Posting")
	private ArrayList<Posting> postings;
	
	public Jobs() {
		this.setPosting(new ArrayList<Posting>());
	}
	
	public ArrayList<Posting> getAllPostings() {
		return this.postings;
	}

	public void setPosting(ArrayList<Posting> posting) {
		this.postings = posting;
	}

}
