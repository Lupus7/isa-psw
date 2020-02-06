package team47pack.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name="procedure")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Procedure {
	
	// nije gotov model, sale bla bla....
	
	@Id
	@SequenceGenerator(name = "procedure_id_seq", sequenceName = "procedure_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procedure_id_seq")
    protected Long id;

    @Column
    protected String type;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="room",referencedColumnName = "id",nullable = true)
    protected Room room;

    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    protected Date date;

	@Column
	protected Integer time;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id",nullable = true)
    protected Patient patient;

	public Procedure() {
		
	}

	public Procedure(Long id, String type, Date date, Patient patient, Room room) throws ParseException {
		this.id = id;
		this.type = type;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		this.date = sdf.parse(date.toString());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		this.time = calendar.get(Calendar.HOUR_OF_DAY);
		this.patient = patient;
		this.room = room;
	}

	public Procedure(Long id, String type, Date date, Integer time, Patient patient, Room room) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.time = time;
		this.patient = patient;
		this.room = room;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
