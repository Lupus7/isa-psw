package team47pack.models.dto;

import java.util.Date;

public class SearchDoctorRequest {
    private String name;
    private String surname;
    private String specialization;
    private Date date;

    public SearchDoctorRequest() {
    }

    public SearchDoctorRequest(String name, String surname,String specialization,Date date) {
        this.name = name;
        this.surname = surname;
        this.specialization=specialization;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
