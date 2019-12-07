package team47pack.models;

import team47pack.models.dto.ClinicRegister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Entity
@Table(name="clinic")
public class Clinic {

    @Id
    @SequenceGenerator(name = "clinic_id_seq", sequenceName = "clinic_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinic_id_seq")
    Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    // TODO : private List<Appointment> appointmens;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_doctor",
            joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private List<Doctor> doctors;

    // @author: Jokara
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "cadmin",
            joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_admin_id", referencedColumnName = "id"))
    private List<ClinicAdmin> clinicAdmins;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

	public List<ClinicAdmin> getClinicAdmins() {
		return clinicAdmins;
	}

	public void setClinicAdmins(List<ClinicAdmin> clinicAdmins) {
		this.clinicAdmins = clinicAdmins;
	}
    
    
}
