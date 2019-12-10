package team47pack.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    protected Date date;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id",nullable = true)
    protected Patient patient;

	public Procedure() {
		
	}

	public Procedure(Long id, String type, Date date, Patient patient) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.patient = patient;
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

    

}
