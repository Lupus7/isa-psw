package team47pack.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// @author: Lupus7 (Sinisa Canak)
public class CAdminRegReq extends RegisterRequest {

    @NotNull(message = "Choose the clinic id!")
    @Positive(message = "Invalid clinic id!")
    private int clinic;

    public CAdminRegReq() {
    }

    public CAdminRegReq(String firstName, String lastName, String password, String email, String address, String city, String state, String telephone, String uniqueNum, int clinic) {
        super(firstName, lastName, password, email, address, city, state, telephone, uniqueNum);
        this.clinic = clinic;
    }

    public int getClinic() {
        return clinic;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }
}
