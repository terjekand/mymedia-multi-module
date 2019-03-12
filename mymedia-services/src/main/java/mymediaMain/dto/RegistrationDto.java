package mymediaMain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private String username;
    private String password;
    private String passwordAgain;
    private String email;
    private String fullname;

}
