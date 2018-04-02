package in.epaylater.testApp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginRequest {

    @NotNull
    @NotEmpty
    //@Pattern(regexp = "^[6-9]\\d{9}$\n", message = "phone should be valid")
    private String phone;

    @JsonCreator
    public LoginRequest(@JsonProperty("phone") String phone) {
        this.phone = phone;
    }

    public LoginRequest() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
