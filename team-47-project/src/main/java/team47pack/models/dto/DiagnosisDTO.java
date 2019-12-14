package team47pack.models.dto;

import team47pack.models.Diagnosis;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class DiagnosisDTO {

    @Positive(message = "Diagnosis ID not valid!")
    Long id;

    @NotBlank(message = "Diagnosis name not valid!")
    String name;

    @NotBlank(message = "Diagnosis description not valid!")
    String description;

    public DiagnosisDTO() {
    }

    public DiagnosisDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public DiagnosisDTO(Diagnosis d) {
        this.id = d.getId();
        this.name = d.getName();
        this.description = d.getDesc();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
