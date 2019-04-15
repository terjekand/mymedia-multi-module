package mymediaMain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalData implements Serializable {
    private String username;
    private String email;
    private String phoneNumber;
    private String bio;
    private String fullName;
}
