package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponse extends Response {
    private String token;

    public LoginResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, String token) {
        super(errorMessages, errorCodes);
        this.token = token;
    }
}
