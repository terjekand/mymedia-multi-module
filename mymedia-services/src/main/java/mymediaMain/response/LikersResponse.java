package mymediaMain.response;

import database.entities.User;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

import java.util.List;

public class LikersResponse extends Response {
    List<User> likers;

    public LikersResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, List<User> likers) {
        super(errorMessages, errorCodes);
        this.likers = likers;
    }
}
