package mymediaMain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    USED_USERNAME(-1),
    USED_EMAIL(-2),
    TOKEN_REFRESH_ERROR(-3),
    USER_ALREADY_HAS_NO_PERMISSION(-4),
    ALREADY_AUTHORIZED_USER(-5),
    WRONG_EMAIL_FORMAT(-6),
    WRONG_USERNAME(-7),
    MALFORMED_TOKEN(-8),
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
