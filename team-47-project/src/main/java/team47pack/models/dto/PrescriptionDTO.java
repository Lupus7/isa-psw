package team47pack.models.dto;

import team47pack.models.Prescription;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PrescriptionDTO {

    @NotNull(message = "Prescription ID not valid!")
    @Positive(message = "Prescription ID not valid!")
    Long id;

    @NotBlank(message = "Prescription name not valid!")
    String name;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PrescriptionDTO(Prescription p) {
        this.id = p.getId();
        this.name = p.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
