package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponse extends Response {
    private String token;

    public LoginResponse(String errorMessages, int errorCodes, String token) {
        super(errorMessages, errorCodes);
        this.token = token;
    }
}
