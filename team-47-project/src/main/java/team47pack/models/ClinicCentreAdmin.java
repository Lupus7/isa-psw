package team47pack.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clinicCentreAdmin")
public class ClinicCentreAdmin extends User {
	
	public ClinicCentreAdmin(String firstName, String lastName, String password, String email, String adress, String city,
			String state, String telephone, String uniqueNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.address = adress;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
	}
	
	public ClinicCentreAdmin() {
		
	}

	public ClinicCentreAdmin(User u) {
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.password = u.getPassword();
		this.email = u.getEmail();
		this.address = u.getAddress();
		this.city = u.getCity();
		this.state = u.getState();
		this.telephone = u.getTelephone();
		this.uniqueNum = u.getUniqueNum();
	}
}
