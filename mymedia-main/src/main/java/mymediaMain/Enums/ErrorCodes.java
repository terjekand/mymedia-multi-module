package mymediaMain.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    WRONG_USERNAME(-1),
    WRONG_EMAIL(-2),
    TOKEN_REFRESH_ERROR(-3),
    USER_ALREADY_HAS_NO_PREMISSION(-4),
    ALREADY_AUTHORIZED_USER(-5),
    WRONG_EMAIL_FORMAT(-6),
    OK(200),
    UNAUTHORIZED(401),
    UNKNOWN_ERROR(-999);

    private Integer number;

    @Override
    public String toString() {
        return "ErrorCodes{" +
                "number=" + number +
                '}';
    }
}
