package team47pack.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="operation")
public class Operation extends Procedure{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "oper_doc", joinColumns = @JoinColumn(name = "oper_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private List<Doctor> doctors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operationtype_id", referencedColumnName = "id", nullable = false)
    private OperationType operationtype;

    @Column
    private Boolean approved;

    @Column(columnDefinition = "boolean default false")
    private Boolean ratedDoctor;

    @Column(columnDefinition = "boolean default false")
    private Boolean ratedClinic;

    public Operation() {
    }

    public Operation(Long id, String type, Date date, Integer time, Patient patient, Room room, List<Doctor> doctors, OperationType operationtype, Boolean approved, Boolean ratedDoctor, Boolean ratedClinic) {
        super(id, type, date, time, patient, room);
        this.doctors = doctors;
        this.operationtype = operationtype;
        this.approved = approved;
        this.ratedDoctor = ratedDoctor;
        this.ratedClinic = ratedClinic;
    }

    public Operation(OperationType operationType, Date date, Patient patient, List<Doctor> doctors, int t) {
        this.type = "Operation";
        this.date = date;
        this.patient = patient;
        this.doctors = doctors == null ? new ArrayList<>() : doctors;
        this.operationtype = operationType;
        this.time = t;
        this.room = null;
        this.approved = false;
        this.ratedClinic = false;
        this.ratedDoctor = false;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public OperationType getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(OperationType operationtype) {
        this.operationtype = operationtype;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getRatedDoctor() {
        return ratedDoctor;
    }

    public void setRatedDoctor(Boolean ratedDoctor) {
        this.ratedDoctor = ratedDoctor;
    }

    public Boolean getRatedClinic() {
        return ratedClinic;
    }

    public void setRatedClinic(Boolean ratedClinic) {
        this.ratedClinic = ratedClinic;
    }
}
