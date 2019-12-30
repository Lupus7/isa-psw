package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "examinationtype")
public class ExaminationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String name;
	
	@Column(name="clinic", unique=false, nullable=false)
	private Long clinic;

	public ExaminationType() {

	}

	public ExaminationType(Long clinicId, String name) {
		super();
		this.clinic = clinicId;
		this.name = name;
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

	public Long getClinic() {
		return clinic;
	}

	public void setClinic(Long clinic_id) {
		this.clinic = clinic_id;
	}
	
	

	
}
