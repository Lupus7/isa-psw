package team47pack.models.dto;

public class MonthDTO {

	private String month;
	private int procedures;

	public MonthDTO() {
		super();
	}

	public MonthDTO(String month, int procedures) {
		super();
		this.month = month;
		this.procedures = procedures;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getProcedures() {
		return procedures;
	}

	public void setProcedures(int procedures) {
		this.procedures = procedures;
	}

}
