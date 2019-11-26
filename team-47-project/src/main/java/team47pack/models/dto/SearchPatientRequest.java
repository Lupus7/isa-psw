package team47pack.models.dto;

public class SearchPatientRequest {

	private String firstName;
	private String lastName;
	private String id;

	public SearchPatientRequest() {
		
	}

	public SearchPatientRequest(String firstName, String lastName, String id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
