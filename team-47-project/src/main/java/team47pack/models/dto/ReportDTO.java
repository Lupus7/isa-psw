package team47pack.models.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportDTO {
	private String clinicName;
	private double clinicRate;
	private List<DoctorRateDTO> doctors;

	public ReportDTO(String clinicName, double clinicRate) {
		super();
		this.clinicName = clinicName;
		this.clinicRate = clinicRate;
		this.doctors = new ArrayList<>();

	}

	public ReportDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public double getClinicRate() {
		return clinicRate;
	}

	public void setClinicRate(double clinicRate) {
		this.clinicRate = clinicRate;
	}

	public List<DoctorRateDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorRateDTO> doctors) {
		this.doctors = doctors;
	}

}
