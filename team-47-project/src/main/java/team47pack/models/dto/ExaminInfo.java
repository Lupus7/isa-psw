package team47pack.models.dto;

import team47pack.models.Diagnosis;
import team47pack.models.Prescription;

import java.util.List;

public class ExaminInfo {
    private Long patientId;
    private String desc;
    private Diagnosis diag;
    private List<Prescription> prescs;

    public ExaminInfo() {
    }

    public ExaminInfo(Long patientId, String desc, Diagnosis diag, List<Prescription> prescs) {
        this.patientId = patientId;
        this.desc = desc;
        this.diag = diag;
        this.prescs = prescs;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Diagnosis getDiag() {
        return diag;
    }

    public void setDiag(Diagnosis diag) {
        this.diag = diag;
    }

    public List<Prescription> getPrescs() {
        return prescs;
    }

    public void setPrescs(List<Prescription> prescs) {
        this.prescs = prescs;
    }
}
