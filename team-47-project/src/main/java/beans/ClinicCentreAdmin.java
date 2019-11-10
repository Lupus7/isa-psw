package beans;

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
		this.adress = adress;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
	}
}
