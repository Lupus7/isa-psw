package team47pack.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	}

	public Examination(String type, Date date, Patient patient, Doctor doc, Boolean approved, ExaminationType et) {
		this.type = type;
		this.date = date;
		this.patient = patient;
		this.doctor = doc;
		this.approved = approved;
		this.examinationtype = et;
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

}
