package team47pack.models.dto;

public class DoctorInfoRequest {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String telephone;
	private String uniqueNum;
	private String specialization;
	private String email;

	public DoctorInfoRequest() {

	}

	public DoctorInfoRequest(String firstName, String lastName, String address, String city, String state,
			String telephone, String uniqueNum, String specialization, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
		this.specialization = specialization;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUniqueNum() {
		return uniqueNum;
	}

	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
