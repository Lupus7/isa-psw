package team47pack.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@author:Jokara
@Entity
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "number", unique = false, nullable = false)
	private int number;

	@Column(name = "roomtype", unique = false, nullable = false)
	private String type;

	@Column(name = "clinic_id", unique = false, nullable = false)
	private int clinicId;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "takendates",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roomarrange_id", referencedColumnName = "id"))
	private List<RoomArrange> takenDates;


	public Room() {

	}

	public Room(String name, int number, int i, String type) {
		super();
		this.name = name;
		this.number = number;
		this.clinicId = i;
		this.type = type;
		this.takenDates = new ArrayList<RoomArrange>();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RoomArrange> getTakenDates() {
		return takenDates;
	}

	public void setTakenDates(List<RoomArrange> takenDates) {
		this.takenDates = takenDates;
	}
	


	
}
