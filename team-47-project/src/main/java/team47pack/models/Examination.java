package team47pack.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="examination")
public class Examination extends Procedure{
  
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = true)
    private Doctor doctor;

    @Column
    private Boolean approved;

    public Examination(Doctor doctor) {
        this.doctor=doctor;
    }
    
    public Examination() {
	}
	public Examination( String type, Date date, Patient patient,Doctor doc,Boolean approved){
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.doctor = doc;
        this.approved = approved;
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
}
