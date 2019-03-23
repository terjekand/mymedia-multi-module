package mymediaMain.response;

import database.entities.User;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

import java.util.List;

public class SearchResponse extends Response {
    List<User> result;

    public SearchResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, List<User> result) {
        super(errorMessages, errorCodes);
        this.result = result;
    }
}
