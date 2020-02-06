package team47pack.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MedicalStaff extends User {

	@Column(name = "onVacation", unique = false, nullable = false)
	protected Boolean onVacation;

	@Column(name = "clinic_id", unique = false, nullable = false)
	protected Long clinicId;

	@Column(name = "holiday_end", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date holidayEnd;

	@Column(name = "holiday_begin", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date holidayBegin;

	@Column(name = "shift", unique = false, nullable = false)
	protected int shift;

	public Boolean getOnVacation() {
		return onVacation;
	}

	public void setOnVacation(Boolean onVacation) {
		this.onVacation = onVacation;
	}

	public Long getClinicId() {
		return clinicId;
	}

	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}

	public Date getHolidayEnd() {
		return holidayEnd;
	}

	public void setHolidayEnd(Date holidayEnd) {
		this.holidayEnd = holidayEnd;
	}

	public Date getHolidayBegin() {
		return holidayBegin;
	}

	public void setHolidayBegin(Date holidayBegin) {
		this.holidayBegin = holidayBegin;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}
}
