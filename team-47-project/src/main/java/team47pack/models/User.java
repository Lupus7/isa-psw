package team47pack.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

	@Id
	@SequenceGenerator(name = "hibernate_id_seq", sequenceName = "hibernate_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_id_seq")
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

	@Column(name = "enabled")
	protected boolean enabled;

	@Column(name = "last_password_reset_date")
	protected Timestamp lastPasswordResetDate;

	@Column(name="isAccepted", unique=false, nullable=false)
	protected boolean accepted;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities;

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

	@Override
	public String getUsername() {
		return email;
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

	public boolean isAccepted() { return accepted; }

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		Timestamp now = new Timestamp(DateTime.now().getMillis());
		this.setLastPasswordResetDate( now );
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setAccepted(boolean isAccepted) { this.accepted = isAccepted; }

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
