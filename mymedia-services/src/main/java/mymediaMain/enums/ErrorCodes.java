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

    /** POST */
    POST_DOES_NOT_EXIST(-301),

    /** SECURITY */
    UNAUTHORIZED(401),

    /** DATABASE */
    NO_DATA_BASE_CONNECTION(-500),
    BAD_PARAMETERS(-501),
    SAVE_ENTITY_ERROR(-502),
    LIKERS_UPDATE_ERROR(-503),
    SAVE_POST_FAILED(-504),

    /** OTHER ERROR CODES */
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
