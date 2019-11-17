package team47pack.models;

import team47pack.models.dto.RegisterRequest;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = true)
    private MedicalFile medicalFile;


    @Column(name="isAccepted", unique=false, nullable=false)
    private boolean isAccepted;

    public boolean getIsAccepted() { return isAccepted; }
    public void setIsAccepted(boolean isAccepted) { this.isAccepted = isAccepted; }

    public Patient() {
    }

    public Patient(RegisterRequest req){
        this.firstName = req.firstName;
        this.lastName = req.lastName;
        this.password = req.password;
        this.email = req.email;
        this.address = req.address;
        this.city = req.city;
        this.state = req.state;
        this.telephone = req.telephone;
        this.uniqueNum = req.uniqueNum;
        this.isAccepted = false;
        this.medicalFile = null;

    }

}
