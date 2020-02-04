package team47pack.models.dto;

public class DoctorRateDTO {
	public String name;
	public double rate;

	public DoctorRateDTO(String name, double rate) {
		super();
		this.name = name;
		this.rate = rate;
	}

	public DoctorRateDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
