package team47pack.models.dto;

import team47pack.models.Clinic;

public class ClinicSearchResult {
    private Clinic clinic;
    private int cost;

    public ClinicSearchResult() {
    }

    public ClinicSearchResult(Clinic clinic, int cost) {
        this.clinic = clinic;
        this.cost = cost;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
