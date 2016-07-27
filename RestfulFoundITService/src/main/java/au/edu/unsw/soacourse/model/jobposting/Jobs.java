package au.edu.unsw.soacourse.model.jobposting;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import au.edu.unsw.soacourse.dao.CompanyDao;

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

	public Posting getPosting(int jobid) {
		for (Posting posting : postings) {
			if(posting.getJobId() == jobid)
				return posting;
		}
		
		return null;
	}

	public void setPosting(ArrayList<Posting> posting) {
		this.postings = posting;
	}
	
	public void addPosting(Posting newPosting) {
		this.postings.add(newPosting);
	}
	
	public Jobs getCompanyPosting(int companyid) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getCompanyId() == companyid) 
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public void removePosting(int jobid) {
		int i = 0;
		for (i = 0; i <= this.postings.size(); i++) {
			if (i == this.postings.size())
				break;
			
			if (this.postings.get(i).getJobId() == jobid)
				break;
		}
		
		if (i == this.postings.size())
			return;
		
		this.postings.remove(i);
	}
	
	public int size() {
		return this.postings.size();
	}
	
	public boolean contains(int jobid) {
		for (Posting p: this.postings) {
			if (p.getJobId() == jobid)
				return true;
		}
		return false;
	}
	
	public Jobs searchJobPositionTitle(String title) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getPositionTitle().toLowerCase().contains(title.toLowerCase())) 
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public Jobs searchJobLocation(String location) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getLocation().toLowerCase().contains(location.toLowerCase())) 
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public Jobs searchJobSalaryRate(String salaryrate) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getSalaryRate().equalsIgnoreCase(salaryrate))
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public Jobs searchJobDetails(String details) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getDetails().toLowerCase().contains(details.toLowerCase())) 
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public Jobs searchJobStatus(String status) {
		Jobs newJobs = new Jobs();
		
		for (Posting p: this.postings) {
			if (p.getStatus().equalsIgnoreCase(status))
				newJobs.addPosting(p);
		}
		
		return newJobs;
	}
	
	public Jobs searchJobCompany(String company) {
		CompanyDao companydao = new CompanyDao();
		if (companydao.getCompanyProfiles().containsCompanyName(company)) {
			int id = companydao.getCompanyProfiles().getCompanyId(company);
			
			Jobs newJobs = new Jobs();
			for (Posting p: this.postings) {
				if (p.getCompanyId() == id) 
					newJobs.addPosting(p);
			}
			
			return newJobs;
			
		} else {
			return new Jobs();
		}
	}

}
