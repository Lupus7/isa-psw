package team47pack.models.dto;

import java.util.Date;

public class DayReportDTO {

	private Date date;
	private double procedures;

	public DayReportDTO() {
		super();
	}

	public DayReportDTO(Date date, int procedures) {
		super();
		this.date = date;
		this.procedures = procedures;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getProcedures() {
		return procedures;
	}

	public void setProcedures(double procedures) {
		this.procedures = procedures;
	}

}
