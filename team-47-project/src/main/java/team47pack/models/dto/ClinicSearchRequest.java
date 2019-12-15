package team47pack.models.dto;

import java.util.Date;

public class ClinicSearchRequest {
    private String location;
    private String examination;
    private Date date;
    private Double rate;

    public ClinicSearchRequest() {
    }

    public ClinicSearchRequest(String location, String examination,Date date,Double rate) {
        this.location = location;
        this.examination = examination;
        this.date = date;
        this.rate = rate;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
