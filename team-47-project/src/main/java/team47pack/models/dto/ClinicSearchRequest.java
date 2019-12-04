package team47pack.models.dto;

public class ClinicSearchRequest {
    private String location;
    private String examination;

    public ClinicSearchRequest() {
    }

    public ClinicSearchRequest(String location, String examination) {
        this.location = location;
        this.examination = examination;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }
}
