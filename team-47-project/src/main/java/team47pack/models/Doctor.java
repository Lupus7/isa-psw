package team47pack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor extends MedicalStaff {

	@Column(name = "specialization", unique = false, nullable = false)
	private String specialization;

	//@Author bokimilinkovic
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "doctor_ratings",
			joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rate_id", referencedColumnName = "id"))
	private List<Rate> ratings;

	private Double average;

	public Doctor(String firstName, String lastName, String password, String email, String adress, String city,
			String state, String telephone, String uniqueNum, Boolean onVacation, String specialization, int shift) {
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
		this.shift = shift;

	}

	public Doctor() {

	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Rate> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rate> ratings) {
		this.ratings = ratings;
	}

	public Double calculateRate(){
		int sum = 0;
		if(this.ratings.size() != 0) {
			for (Rate r : this.ratings) {
				System.out.println(r.getValue());
				sum += r.getValue();
			}
			this.average = Double.valueOf(sum / this.ratings.size());
			System.out.println("Izracunat prosek: "+this.average);
			return Double.valueOf(sum / this.ratings.size());
		}
		return 0.0;
	}

	public Double getAverage() {
		return this.average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}
}
