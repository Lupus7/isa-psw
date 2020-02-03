package team47pack.models;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

// @author: Lupus7 (Sinisa Canak)
@Entity
@Table(name = "pres_verify")
public class PrescriptionVerification {

    @Id
    @SequenceGenerator(name = "verification_id_seq", sequenceName = "verification_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_id_seq")
    protected Long id;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id")
    protected Patient patient;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="doctor_id",referencedColumnName = "id")
    protected Doctor doctor;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="nurse_id",referencedColumnName = "id")
    protected Nurse nurse; // if null, prescription not verified

    @Column(name = "verification_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    protected Date verificationTime; // if null, prescription not verified

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="prescription_id",referencedColumnName = "id")
    protected Prescription prescription;

    public PrescriptionVerification() {
    }

    public PrescriptionVerification(Patient patient, Doctor doctor, Prescription pres) {
        this.patient = patient;
        this.doctor = doctor;
        this.prescription = pres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
