package team47pack.models.dto;

import java.util.Date;
import java.util.List;

import team47pack.models.Diagnosis;
import team47pack.models.Prescription;

public class ExaminInfo {
	private Long patientId;
	private String desc;
	private Diagnosis diag;
	private List<Prescription> prescs;
	private Date date;
	private String procedure;

	public ExaminInfo() {
	}

	public ExaminInfo(Long patientId, String desc, Diagnosis diag, List<Prescription> prescs, Date date,
			String procedure) {
		this.patientId = patientId;
		this.desc = desc;
		this.diag = diag;
		this.prescs = prescs;
		this.date = date;
		this.procedure = procedure;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

}
