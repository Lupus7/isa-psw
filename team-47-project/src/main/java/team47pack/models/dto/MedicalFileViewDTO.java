package team47pack.models.dto;

import java.util.Date;

public class MedicalFileViewDTO {
	
	private Date date;
	private String doctorName;
	private String diagnosisName;
	private String diagnosisDesc;
	private String desc;
	private boolean canChange;
	private long id;
	
	public MedicalFileViewDTO(Date date, String doctorName, String diagnosisName, String diagnosisDesc, String desc, boolean canChange, long id) {
		super();
		this.date = date;
		this.doctorName = doctorName;
		this.diagnosisName = diagnosisName;
		this.diagnosisDesc = diagnosisDesc;
		this.desc = desc;
		this.canChange = canChange;
		this.id = id;
	}
	
	public MedicalFileViewDTO() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getDiagnosisDesc() {
		return diagnosisDesc;
	}

	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isCanChange() {
		return canChange;
	}

	public void setCanChange(boolean canChange) {
		this.canChange = canChange;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
