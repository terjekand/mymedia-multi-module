package mymediaMain.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    UNAUTHORIZED("Access denied!"),
    WRONG_USERNAME("This user name is taken!"),
    WRONG_EMAIL("This email address is taken!"),
    WRONG_EMAIL_FORMAT("This is not a valid email adress!"),
    OK("Successful!"),
    UNKNOWN_ERROR("UNKNOWN_ERROR"),
    TOKEN_REFRESH_ERROR("Token refresh error!"),
    ALREADY_AUTHORIZED_USER("This user already has permissions"),
    USER_ALREADY_HAS_NO_PREMISSION("Already unauthorized user");

    private String message;

    @Override
    public String toString() {
        return "ErrorMessages{" +
                "message='" + message + '\'' +
                '}';
    }
}
