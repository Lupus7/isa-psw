package team47pack.models.dto;

import java.util.Date;

public class MedicalFileViewDTO {
	
	private Date date;
	private String doctorName;
	private String diagnosisName;
	private String diagnosisDesc;
	
	public MedicalFileViewDTO(Date date, String doctorName, String diagnosisName, String diagnosisDesc) {
		super();
		this.date = date;
		this.doctorName = doctorName;
		this.diagnosisName = diagnosisName;
		this.diagnosisDesc = diagnosisDesc;
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
	
	
	

}
