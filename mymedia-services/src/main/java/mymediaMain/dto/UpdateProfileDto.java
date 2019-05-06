package mymediaMain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProfileDto implements Serializable {
    private String userId;
    private String token;
    private String fullName;
    private String bio;
    private String phoneNumber;
}
