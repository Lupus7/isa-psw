package beans;

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
	
	@Column(name="firstName", unique=false, nullable=false)
	protected String firstName;
	
	@Column(name="lastName", unique=false, nullable=false)
	protected String lastName;
	
	@Column(name="password", unique=false, nullable=false)
	protected String password;
	
	@Column(name="email", unique=true, nullable=false)
	protected String email;
	
	@Column(name="adress", unique=false, nullable=false)
	protected String adress;
	
	@Column(name="city", unique=false, nullable=false)
	protected String city;
	
	@Column(name="state", unique=false, nullable=false)
	protected String state;
	
	@Column(name="telephone", unique=false, nullable=false)
	protected String telephone;
	
	@Column(name="uniqueNum", unique=true, nullable=false)
	protected String uniqueNum;
}
