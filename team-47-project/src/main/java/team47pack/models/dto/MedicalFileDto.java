package team47pack.models.dto;

import team47pack.models.Diagnosis;

import java.util.List;

public class MedicalFileDto {
    private List<String> opisi;
    private List<Diagnosis> dijagnoze;

    public MedicalFileDto() {
    }

    public MedicalFileDto(List<String> opisi, List<Diagnosis> dijagnoze) {
        this.opisi = opisi;
        this.dijagnoze = dijagnoze;
    }

    public List<String> getOpisi() {
        return opisi;
    }

    public void setOpisi(List<String> opisi) {
        this.opisi = opisi;
    }

    public List<Diagnosis> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Diagnosis> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }
}
