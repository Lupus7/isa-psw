package team47pack.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "holidaytimeoff")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HolidayTimeOff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date beginDate;

	@Column
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date endDate;

	@Column
	private String typeRequest;
	@Column
	private String reason;
	@Column
	private boolean accepted;

	@OneToOne(cascade = CascadeType.ALL)
	private MedicalStaff staff;

	public HolidayTimeOff() {

	}

	public HolidayTimeOff(Long id, Date beginDate, Date endDate, String typeRequest, String reason, boolean accepted,
			MedicalStaff staff) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.typeRequest = typeRequest;
		this.reason = reason;
		this.accepted = accepted;
		this.staff = staff;
	}
	
	public HolidayTimeOff(Date beginDate, Date endDate, String typeRequest, String reason,MedicalStaff staff) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.typeRequest = typeRequest;
		this.reason = reason;
		this.staff = staff;
		this.accepted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return typeRequest;
	}

	public void setType(String typeRequest) {
		this.typeRequest = typeRequest;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public MedicalStaff getStaff() {
		return staff;
	}

	public void setStaff(MedicalStaff staff) {
		this.staff = staff;
	}

}
