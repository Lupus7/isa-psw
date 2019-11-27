package team47pack.models;

import team47pack.models.dto.RegisterRequest;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends User {

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = true)
    private MedicalFile medicalFile;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="examination",referencedColumnName = "id", nullable = true)
//    private List<Examination> examinations;

    public Patient() {
    }

    public Patient(RegisterRequest req){
        this.firstName = req.getFirstName();
        this.lastName = req.getLastName();
        this.password = req.getPassword();
        this.email = req.getEmail();
        this.address = req.getAddress();
        this.city = req.getCity();
        this.state = req.getState();
        this.telephone = req.getTelephone();
        this.uniqueNum = req.getUniqueNum();
        this.accepted = false;
        this.medicalFile = null;
        this.enabled = true;
        this.lastPasswordResetDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public MedicalFile getMedicalFile() {
        return medicalFile;
    }



    public void setMedicalFile(MedicalFile medicalFile) {
        this.medicalFile = medicalFile;
    }
/*
    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }
    public List<Examination> getExaminations() {
        return examinations;
    }
    */

    

}
