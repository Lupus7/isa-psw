package team47pack.models.dto;

import team47pack.models.Examination;

import java.util.Date;

public class ExaminationDTO {
    private Long doctor_id;
    private Long patient_id;
    private Boolean approved;
    private String type;
    private Date date;

    public ExaminationDTO(Examination e) {
        this.approved = e.getApproved();
        this.date = e.getDate();
        this.type =e.getType();
        this.doctor_id = e.getDoctor().getId();
        this.patient_id = e.getPatient().getId();
    }

    public ExaminationDTO() {
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
