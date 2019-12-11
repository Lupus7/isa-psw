package team47pack.models.dto;

import team47pack.models.Doctor;
import team47pack.models.Patient;

import javax.validation.constraints.NotNull;

// @author: Lupus7 (Sinisa Canak)
public class PrescriptionVerifyDTO {
    @NotNull(message = "Verification id invalid!")
    Long id;

    Long doctorId;
    Long patientId;
    String doctorName;
    String patientName;
    String doctorUsername;
    String patientUsername;
    PrescriptionDTO prescription;

    @NotNull(message = "Verification value invalid!")
    Boolean verified;

    public PrescriptionVerifyDTO() {
    }

    public PrescriptionVerifyDTO(Long id, Doctor doc, Patient pat, PrescriptionDTO prescription) {
        this.id = id;
        this.doctorId = doc.getId();
        this.patientId = pat.getId();
        this.doctorName = doc.getFirstName() + " " + doc.getLastName();
        this.patientName = pat.getFirstName() + " " + pat.getLastName();
        this.doctorUsername = doc.getUsername();
        this.patientUsername = pat.getUsername();
        this.prescription = prescription;
        this.verified = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public PrescriptionDTO getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionDTO prescriptions) {
        this.prescription = prescription;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
