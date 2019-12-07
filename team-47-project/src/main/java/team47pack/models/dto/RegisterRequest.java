package team47pack.models.dto;

import javax.validation.constraints.NotBlank;

public class RegisterRequest {

    // TODO: ADD ADDITIONAL CONSTRAINTS (REF: https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints)
    @NotBlank(message = "Please enter the ...")
    protected String firstName;

    @NotBlank(message = "Please enter the ...")
    protected String lastName;

    @NotBlank(message = "Please enter the ...")
    protected String password;

    @NotBlank(message = "Please enter the ...")
    protected String email;

    @NotBlank(message = "Please enter the ...")
    protected String address;

    @NotBlank(message = "Please enter the ...")
    protected String city;

    @NotBlank(message = "Please enter the ...")
    protected String state;

    @NotBlank(message = "Please enter the ...")
    protected String telephone;

    @NotBlank(message = "Please enter the ...")
    protected String uniqueNum;

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
