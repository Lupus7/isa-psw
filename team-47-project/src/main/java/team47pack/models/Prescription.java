package team47pack.models;

import javax.persistence.*;

@Entity
@Table(name="prescription")
public class Prescription {
    @Id
    @SequenceGenerator(name = "pres_id_seq", sequenceName = "pres_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pres_id_seq")
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Prescription() {
    }

    public Prescription(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
