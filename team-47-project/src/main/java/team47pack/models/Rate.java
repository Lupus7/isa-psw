package team47pack.models;

import javax.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @SequenceGenerator(name = "rate_id_seq", sequenceName = "rate_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_id_seq")
    Long id;

    @Column(name = "value", nullable = false)
    private Integer value;

    public Rate(Integer value) {
        this.value = value;
    }
    public Rate(Long id,Integer value) {
        this.value = value;
        this.id=id;
    }
    public Rate(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
