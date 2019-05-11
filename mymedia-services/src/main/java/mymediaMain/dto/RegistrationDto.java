package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationDto implements Serializable  {

    private String username;
    private String password;
    private String passwordAgain;
    private String email;
    private String fullname;

}
