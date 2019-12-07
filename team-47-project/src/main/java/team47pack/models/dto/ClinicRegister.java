package team47pack.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// @author: Lupus7 (Sinisa Canak)
public class ClinicRegister {
    @NotBlank(message = "Please enter the clinic name")
    @Size(min=5, max=15, message = "Clinic name must contain between 5 and 15 characters!")
    private String name;

    @NotBlank(message = "Please enter the clinic address")
    @Size(min=8, max=30, message = "Clinic name must contain between 8 and 30 characters!")
    private String address;

    @NotBlank(message = "Please enter a description")
    @Size(min=10, message = "Description must contain at least 10 characters!")
    private String description;

    public ClinicRegister() {
    }

    public ClinicRegister(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
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
}
