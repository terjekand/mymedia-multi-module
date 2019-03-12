package mymediaMain.response;

import database.entities.User;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

public class UserResponse extends Response{
    private User user;

    public UserResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, User user) {
        super(errorMessages, errorCodes);
        this.user = user;
    }
}
