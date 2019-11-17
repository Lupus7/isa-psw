package team47pack.models.dto;

import team47pack.models.User;

public class RegisterRequest extends User {
    public RegisterRequest(String firstName, String lastName, String password, String email, String adress, String city,
                             String state, String telephone, String uniqueNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = adress;
        this.city = city;
        this.state = state;
        this.telephone = telephone;
        this.uniqueNum = uniqueNum;
    }

}
