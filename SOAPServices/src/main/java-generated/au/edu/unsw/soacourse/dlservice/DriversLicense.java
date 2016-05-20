package au.edu.unsw.soacourse.dlservice;

public class DriversLicense {
	private String fullName;
	private String DLNumber;
	public DriversLicense() {}
	public DriversLicense(String fullName, String DLNumber) {
		this.fullName = fullName;
		this.DLNumber = DLNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDLNumber() {
		return DLNumber;
	}
	public void setDLNumber(String dLNumber) {
		DLNumber = dLNumber;
	}
}
