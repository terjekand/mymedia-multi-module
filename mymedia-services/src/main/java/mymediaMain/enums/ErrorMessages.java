package mymediaMain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    UNAUTHORIZED("Access denied!"),
    USED_USERNAME("This user name is taken!"),
    USED_EMAIL("This email address is taken!"),
    WRONG_EMAIL_FORMAT("This is not a valid email address!"),
    WRONG_USERNAME("Wrong username!"),
    TOKEN_DOES_NOT_EXIST("Token does not exist!"),
    USER_NOT_FOUND("User not found!"),
    OK("Successful!"),
    UNKNOWN_ERROR("UNKNOWN_ERROR"),
    TOKEN_REFRESH_ERROR("Token refresh error!"),
    ALREADY_AUTHORIZED_USER("This user already has permissions"),
    USER_ALREADY_HAS_NO_PREMISSION("Already unauthorized user"),
    MALFORMED_TOKEN("Token has no prefix!"),
    NOT_IMPLEMENTED("Not implemented yet!"),
    NO_DATA_BASE_CONNECTION("No database connection!"),
    SAVE_ENTITY_ERROR("Error while save entity!"),
    BAD_PARAMETERS("Some parameters are empty or null!"),
    POST_DOES_NOT_EXIST("Post does not exist!");

    private String message;

    @Override
    public String toString() {
        return "ErrorMessages{" +
                "message='" + message + '\'' +
                '}';
    }
}
