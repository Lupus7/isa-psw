package team47pack.models.dto;

import team47pack.models.Examination;
import team47pack.models.Operation;

import java.util.Date;

public class ExaminationDTO {
    private Long doctor_id;
    private Long patient_id;
    private Long examinationID;
    private Boolean approved;
    private String type;
    private Date date;
    private Boolean ratedDoctor;
    private Boolean ratedClinic;

    public ExaminationDTO(Examination e) {
        this.approved = e.getApproved();
        this.date = e.getDate();
        this.type =e.getType();
        this.examinationID = e.getId();
        this.doctor_id = e.getDoctor().getId();
        this.patient_id = e.getPatient().getId();
        this.ratedClinic = e.getRatedClinic();
        this.ratedDoctor = e.getRatedDoctor();
    }

    public ExaminationDTO() {
    }

    public ExaminationDTO(Operation o) {
        this.approved = o.getApproved();
        this.date = o.getDate();
        this.type = o.getType();
        this.examinationID = o.getId();
        if (o.getDoctors() == null || o.getDoctors().size() == 0)
            this.doctor_id = -1L;
        else
            this.doctor_id = o.getDoctors().get(0).getId();
        this.patient_id = o.getPatient().getId();
        this.ratedClinic = o.getRatedClinic();
        this.ratedDoctor = o.getRatedDoctor();
    }

    public Long getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(Long examinationID) {
        this.examinationID = examinationID;
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
