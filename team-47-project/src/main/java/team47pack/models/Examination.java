package team47pack.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="examination")
public class Examination extends Procedure{
  
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = true)
    private Doctor doctor;

    public Examination(Doctor doctor) {
        this.doctor=doctor;
    }
    
    public Examination() {
	}


	public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
