package team47pack.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examination")
public class Examination extends Procedure {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "examinationtype_id", referencedColumnName = "id", nullable = false)
	private ExaminationType examinationtype;

	@Column
	private Boolean approved;

	@Column(columnDefinition = "boolean default false")
	private Boolean ratedDoctor;

	@Column(columnDefinition = "boolean default false")
	private Boolean ratedClinic;

	public Examination() {

	}

	public Examination(Doctor doctor) {
		this.doctor = doctor;
	}

	public Examination(String type, Date date, Patient patient, Doctor doc, Boolean approved) {
		this.type = type;
		this.date = date;
		this.patient = patient;
		this.doctor = doc;
		this.approved = approved;
		//this.ratedClinic = false;
		//this.ratedDoctor = false;
	}

	public Examination(String type, Date date, Patient patient, Doctor doc, Boolean approved, ExaminationType et) {
		this.type = type;
		this.date = date;
		this.patient = patient;
		this.doctor = doc;
		this.approved = approved;
		this.examinationtype = et;
		//this.ratedClinic = false;
		//this.ratedDoctor= false;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
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

	public Boolean getRatedDoctor() {
		return ratedDoctor;
	}

	public void setRatedDoctor(Boolean ratedDoctor) {
		this.ratedDoctor = ratedDoctor;
	}

	public Boolean getRatedClinic() {
		return ratedClinic;
	}

	public void setRatedClinic(Boolean ratedClinic) {
		this.ratedClinic = ratedClinic;
	}
}
