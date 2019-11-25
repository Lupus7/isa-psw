package team47pack.models.dto;

import team47pack.models.Clinic;

import java.util.List;

public class ClinicAndAdmin {
    private String name;
    private String address;
    private String description;
    private List<String> admin;

    public ClinicAndAdmin() {
    }

    public ClinicAndAdmin(String name, String address, String description, List<String> admin) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.admin = admin;
    }

    public ClinicAndAdmin(Clinic c, List<String> admin) {
        this.name = c.getName();
        this.address = c.getAddress();
        this.description = c.getDescription();
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAdmin() {
        return admin;
    }

    public void setAdmin(List<String> admin) {
        this.admin = admin;
    }
}
