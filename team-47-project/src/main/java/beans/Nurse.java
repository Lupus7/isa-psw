package beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nurse")
public class Nurse extends MedicalStaff {

	public Nurse(String firstName, String lastName, String password, String email, String adress, String city,
			String state, String telephone, String uniqueNum, Boolean onVacation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
		this.onVacation = onVacation;
	}
}
