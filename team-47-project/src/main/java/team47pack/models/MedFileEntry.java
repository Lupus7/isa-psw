package team47pack.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Entity
@Table(name="med_file_en")
public class MedFileEntry {
    @Id
    @SequenceGenerator(name = "med_en_id_seq", sequenceName = "med_en_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_en_id_seq")
    Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id", nullable = true)
    private Diagnosis diagnosis;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "med_prescription",
            joinColumns = @JoinColumn(name = "med_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"))
    private List<PrescriptionVerification> prescriptions = new ArrayList<>();

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    public MedFileEntry() {
    }

    public MedFileEntry(Long id, Diagnosis diagnosis, List<Prescription> prescriptions, String desc, Patient patient, Doctor doc) {
        this.id = id;
        this.diagnosis = diagnosis;

        List<PrescriptionVerification> pres = new ArrayList<>();
        for (Prescription p : prescriptions) {
            pres.add(new PrescriptionVerification(patient, doc, p));
        }

        this.prescriptions = pres;
        this.description = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public List<PrescriptionVerification> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionVerification> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
