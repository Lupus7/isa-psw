package team47pack.models.dto;

import java.util.ArrayList;

public class MedicalFileDto {
    private ArrayList<String> opisi;
    private ArrayList<DiagnosisDTO> dijagnoze;

    public MedicalFileDto() {
    }

    public MedicalFileDto(ArrayList<String> opisi, ArrayList<DiagnosisDTO> dijagnoze) {
        this.opisi = opisi;
        this.dijagnoze = dijagnoze;
    }

    public ArrayList<String> getOpisi() {
        return opisi;
    }

    public void setOpisi(ArrayList<String> opisi) {
        this.opisi = opisi;
    }

    public ArrayList<DiagnosisDTO> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(ArrayList<DiagnosisDTO> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

}
