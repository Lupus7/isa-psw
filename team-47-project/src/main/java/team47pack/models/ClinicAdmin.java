package team47pack.models;

import team47pack.models.dto.RegisterRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;


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

	public ClinicAdmin(RegisterRequest req, int i) {
		this.firstName = req.getFirstName();
		this.lastName = req.getLastName();
		this.password = req.getPassword();
		this.email = req.getEmail();
		this.address = req.getAddress();
		this.city = req.getCity();
		this.state = req.getState();
		this.telephone = req.getTelephone();
		this.uniqueNum = req.getUniqueNum();
		this.accepted = false;
		this.enabled = true;
		this.lastPasswordResetDate = Timestamp.valueOf(LocalDateTime.now());
		this.clinic = i;
	}
}
