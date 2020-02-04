package team47pack.models.dto;

import java.util.Date;

public class FastExamDto {
    private Long id;
    private String clinic;
    private String doctor;
    private String doctorSpec;
    private String type;
    private String examinationType;
    private Date date;
    private Long PatientID;

    public FastExamDto(Long id, String clinic, String doctor, String doctorSpec, String type, String examinationType, Date date, Long patientID) {
        this.id = id;
        this.clinic = clinic;
        this.doctor = doctor;
        this.doctorSpec = doctorSpec;
        this.type = type;
        this.examinationType = examinationType;
        this.date = date;
        PatientID = patientID;
    }

    public FastExamDto(Long id, String clinic, String doctor, String doctorSpec, String type, String examinationType, Date date) {
        this.id = id;
        this.clinic = clinic;
        this.doctor = doctor;
        this.doctorSpec = doctorSpec;
        this.type = type;
        this.examinationType = examinationType;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPatientID() {
        return PatientID;
    }

    public void setPatientID(Long patientID) {
        PatientID = patientID;
    }

    public String getDoctorSpec() {
        return doctorSpec;
    }

    public void setDoctorSpec(String doctorSpec) {
        this.doctorSpec = doctorSpec;
    }
}
