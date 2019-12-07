package team47pack.models;

import javax.persistence.*;

// @author: Lupus7 (Sinisa Canak)
@Entity
@Table(name="diagnosis")
public class Diagnosis {
    @Id
    @SequenceGenerator(name = "diag_id_seq", sequenceName = "diag_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diag_id_seq")
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    public Diagnosis() {
    }

    public Diagnosis(Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }
}
