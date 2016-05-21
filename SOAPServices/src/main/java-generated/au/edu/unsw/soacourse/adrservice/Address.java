package au.edu.unsw.soacourse.adrservice;

public class Address {
	private String fullName;
	private String address;
	
	public Address() {
		
	}

	public Address(String fullName, String address) {
		this.setFullName(fullName);
		this.setAddress(address);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
