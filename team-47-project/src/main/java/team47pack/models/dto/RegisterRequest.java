package team47pack.models.dto;

import team47pack.models.User;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String uniqueNum;

    public RegisterRequest() {

    }

    public RegisterRequest(String firstName, String lastName, String password, String email, String address, String city,
                           String state, String telephone, String uniqueNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.telephone = telephone;
        this.uniqueNum = uniqueNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUniqueNum() {
        return uniqueNum;
    }
}
