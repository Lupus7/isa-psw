package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@author:Jokara
@Entity
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "name", unique = false, nullable = false)
	protected String name;

	@Column(name = "number", unique = false, nullable = false)
	protected int number;

	@Column(name = "clinic_id", unique = false, nullable = false)
	protected int clinicId;

	public Room() {

	}

	public Room(String name, int number, int i) {
		super();
		this.name = name;
		this.number = number;
		this.clinicId = i;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

}
