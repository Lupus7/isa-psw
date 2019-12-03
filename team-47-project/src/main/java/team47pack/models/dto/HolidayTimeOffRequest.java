package team47pack.models.dto;

public class HolidayTimeOffRequest {

	private String email;
	private String beginDate;
	private String endDate;
	private String typeRequest;
	private String reason;

	public HolidayTimeOffRequest(String email, String beginDate, String endDate, String typeRequest, String reason) {
		super();
		this.email = email;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.typeRequest = typeRequest;
		this.reason = reason;
	}

	public HolidayTimeOffRequest() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTypeRequest() {
		return typeRequest;
	}

	public void setTypeRequest(String typeRequest) {
		this.typeRequest = typeRequest;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
