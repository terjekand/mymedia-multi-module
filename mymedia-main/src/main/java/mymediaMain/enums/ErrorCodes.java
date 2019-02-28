package mymediaMain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    USED_USERNAME(-101),
    USED_EMAIL(-102),
    TOKEN_REFRESH_ERROR(-103),
    USER_ALREADY_HAS_NO_PERMISSION(-104),
    ALREADY_AUTHORIZED_USER(-105),
    WRONG_EMAIL_FORMAT(-106),
    WRONG_USERNAME(-107),
    MALFORMED_TOKEN(-108),
    TOKEN_DOES_NOT_EXIST(-109),
    USER_NOT_FOUND(-110),

    OK(200),


    POST_DOES_NOT_EXIST(-301),


    UNAUTHORIZED(401),

    NO_DATA_BASE_CONNECTION(-500),
    BAD_PARAMETERS(-501),

    UNKNOWN_ERROR(-999),
    NOT_IMPLEMENTED(-3166);

    private Integer number;

    @Override
    public String toString() {
        return "ErrorCodes{" +
                "number=" + number +
                '}';
    }
}
