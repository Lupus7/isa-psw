package team47pack.models;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private MedicalFile medicalFile;


    @Column(name="isAccepted", unique=false, nullable=false)
    private boolean isAccepted;

    public boolean getIsAccepted() { return isAccepted; }
    public void setIsAccepted(boolean isAccepted) { this.isAccepted = isAccepted; }
}
