package team47pack.models.dto;

import java.util.List;

public class MedicalFileDto {
    private List<String> bolesti;
    private List<String> opisBolesti;

    public MedicalFileDto() {
    }

    public MedicalFileDto(List<String> bolesti, List<String> opisBolesti) {
        this.bolesti = bolesti;
        this.opisBolesti = opisBolesti;
    }

    public List<String> getBolesti() {
        return bolesti;
    }

    public void setBolesti(List<String> bolesti) {
        this.bolesti = bolesti;
    }

    public List<String> getOpisBolesti() {
        return opisBolesti;
    }

    public void setOpisBolesti(List<String> opisBolesti) {
        this.opisBolesti = opisBolesti;
    }
}
