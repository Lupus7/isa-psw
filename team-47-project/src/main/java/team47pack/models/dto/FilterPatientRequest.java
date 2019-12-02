package team47pack.models.dto;

public class FilterPatientRequest {

	private String filterBy;
	private String valueFilter;

	public FilterPatientRequest() {

	}

	public FilterPatientRequest(String filterBy, String valueFilter) {
		super();
		this.filterBy = filterBy;
		this.valueFilter = valueFilter;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	public String getValueFilter() {
		return valueFilter;
	}

	public void setValueFilter(String valueFilter) {
		this.valueFilter = valueFilter;
	}

}
