package team47pack.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nextProcedure")
@Inheritance(strategy = InheritanceType.JOINED)
public class NextProcedure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String type;
	
	@Column
	private Long clinic;
	
	@Column
	private int pickedtime;
	
	@Column
	private boolean arranged;

	@Column
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date date;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
	private Patient patient;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = true)
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "examinationtype_id", referencedColumnName = "id", nullable = true)
	private ExaminationType examinationtype;

	public NextProcedure() {

	}

	public NextProcedure(String type, Date date, Patient patient, Doctor doctor,int t) {

		this.type = type;
		this.date = date;
		this.patient = patient;
		this.doctor = doctor;
		this.clinic = doctor.getClinicId();
		this.arranged = false;
		this.pickedtime = t;
	}

	public NextProcedure(String type, Date date, Patient patient, Doctor doctor, ExaminationType etx,int t) {

		this.type = type;
		this.date = date;
		this.patient = patient;
		this.doctor = doctor;
		this.clinic = doctor.getClinicId();
		this.examinationtype = etx;
		this.arranged = false;
		this.pickedtime = t;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ExaminationType getExaminationtype() {
		return examinationtype;
	}

	public void setExaminationtype(ExaminationType examinationtype) {
		this.examinationtype = examinationtype;
	}

	public Long getIdClinic() {
		return clinic;
	}

	public void setIdClinic(Long idClinic) {
		this.clinic = idClinic;
	}

	public boolean isArranged() {
		return arranged;
	}

	public void setArranged(boolean arranged) {
		this.arranged = arranged;
	}

	public int getPickedtime() {
		return pickedtime;
	}

	public void setPickedtime(int pickedtime) {
		this.pickedtime = pickedtime;
	}
	
	
	

}
