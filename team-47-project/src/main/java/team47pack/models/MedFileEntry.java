package team47pack.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="med_file_entry")
public class MedFileEntry {
    @Id
    @SequenceGenerator(name = "med_en_id_seq", sequenceName = "med_en_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_en_id_seq")
    Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id", nullable = true)
    private Diagnosis diagnosis;

    @OneToMany(mappedBy = "med_file_entry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @Column(name = "desc", nullable = false, unique = false)
    private String desc;

    public MedFileEntry() {
    }

    public MedFileEntry(Long id, Diagnosis diagnosis, List<Prescription> prescriptions, String desc) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.desc = desc;
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
