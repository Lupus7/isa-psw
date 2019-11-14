package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;

	@Column(name = "firstName", unique = false, nullable = false)
	protected String firstName;

	@Column(name = "lastName", unique = false, nullable = false)
	protected String lastName;

	@Column(name = "password", unique = false, nullable = false)
	protected String password;

	@Column(name = "email", unique = true, nullable = false)
	protected String email;

	@Column(name = "address", unique = false, nullable = false)
	protected String address;

	@Column(name = "city", unique = false, nullable = false)
	protected String city;

	@Column(name = "state", unique = false, nullable = false)
	protected String state;

	@Column(name = "telephone", unique = false, nullable = false)
	protected String telephone;

	@Column(name = "uniqueNum", unique = true, nullable = false)
	protected String uniqueNum;

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getUniqueNum() {
		return uniqueNum;
	}
}
