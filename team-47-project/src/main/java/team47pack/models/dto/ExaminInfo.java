package team47pack.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class ExaminInfo {
	// @author: Lupus7 (Sinisa Canak)
	@NotNull(message = "Patient id not valid")
	@Positive(message = "Patient id not valid")
	private Long patientId;
	@NotBlank(message = "Please fill the examination description")
	@Size(min = 10, message = "Examination info needs to contain at least 10 characters!")
	private String desc;
	@NotNull
	private DiagnosisDTO diag;
	private List<PrescriptionDTO> prescs;
	////

	private Date date;
	private String procedure;

	public ExaminInfo() {
	}

	public ExaminInfo(Long patientId, String desc, DiagnosisDTO diag, List<PrescriptionDTO> prescs, Date date,
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

	public @NotNull DiagnosisDTO getDiag() {
		return diag;
	}

	public void setDiag(DiagnosisDTO diag) {
		this.diag = diag;
	}

	public List<PrescriptionDTO> getPrescs() {
		return prescs;
	}

	public void setPrescs(List<PrescriptionDTO> prescs) {
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
