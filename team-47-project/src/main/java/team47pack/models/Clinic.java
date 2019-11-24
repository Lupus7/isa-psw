package team47pack.models;

import team47pack.models.dto.ClinicRegister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clinic")
public class Clinic {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "desc", nullable = false)
    private String description;

    // TODO : private List<Appointment> appointmens;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_doctor",
            joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private List<Doctor> doctors;

    // TODO : private List<Room> rooms;
    // TODO : private List<Procedure> procedures;

    public Clinic() {
    }

    public Clinic(ClinicRegister reg) {
        this.name = reg.getName();
        this.address = reg.getAddress();
        this.description = reg.getDescription();
        this.doctors = new ArrayList<Doctor>();
    }
}
