package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor extends MedicalStaff {
	
	@Column(name="specialization", unique=false, nullable=false)
	private String specialization;
	
	
	public Doctor(String firstName, String lastName, String password, String email, String adress, String city,
			String state, String telephone, String uniqueNum, Boolean onVacation,String specialization) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.address = adress;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
		this.onVacation = onVacation;
		this.specialization = specialization;
	}

}
