package au.edu.unsw.soacourse.model.company;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Profiles")
@XmlAccessorType (XmlAccessType.FIELD)
public class CompanyProfiles {
	
	@XmlElement(name="Company")
	private ArrayList<Company> companies;
	
	public boolean contains(int profileid) {
		for (Company company: companies) {
			if (company.getProfileId() == profileid)
				return true;
		}
		return false;
	}
	
	public ArrayList<Company> getCompanies() {
		return companies;
	}
	
	public void setCompanies(ArrayList<Company> companies) {
		this.companies = companies;
	}
	
	public int getCompanyId(String name) {
		for (Company company: this.companies) {
			if (company.getName().toLowerCase().contains(name.toLowerCase()))
				return company.getProfileId();
		}
		return -1;
	}
	
	public Company getCompany(int profileid) {
		for (Company company : this.companies) {
			if(company.getProfileId() == profileid)
				return company;
		}
		
		return null;
	}
	
	public void addCompany(Company company) {
		this.companies.add(company);
	}

	public void removeCompany(int profileid) {
		int i = 0;
		for (i = 0; i < companies.size(); i++) {
			if (companies.get(i).getProfileId() == profileid)
				break;
		}
		
		if (i == companies.size())
			return;
		
		companies.remove(i);
	}
	
	public boolean containsCompanyName(String name) {
		for (Company company: this.companies) {
			if (company.getName().toLowerCase().contains(name.toLowerCase()))
				return true;
		}
		return false;
	}

	
}