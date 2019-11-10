package team47pack.models;

import javax.persistence.*;

@Entity
@Table(name = "medicalfile")
public class MedicalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //private Patient patient;

}
