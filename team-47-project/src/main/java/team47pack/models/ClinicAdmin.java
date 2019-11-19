package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "clinicAdmin")
public class ClinicAdmin extends User{
	
	@Column(name="clinic", unique=false, nullable=false)
	private int clinic;
	
	public ClinicAdmin(String firstName, String lastName, String password, String email, String adress, String city,
			String state, String telephone, String uniqueNum, int clinic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.address = adress;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.uniqueNum = uniqueNum;
		this.clinic = clinic;
	}
	
	public ClinicAdmin() {
		
	}

}
