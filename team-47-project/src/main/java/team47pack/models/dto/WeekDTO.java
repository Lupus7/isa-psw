package team47pack.models.dto;

public class WeekDTO {

	private int week;
	private int procedures;

	public WeekDTO() {
		super();
	}

	public WeekDTO(int week, int procedures) {
		super();
		this.week = week;
		this.procedures = procedures;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getProcedures() {
		return procedures;
	}

	public void setProcedures(int procedures) {
		this.procedures = procedures;
	}

}
