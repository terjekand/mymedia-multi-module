package mymediaMain.response;

import database.entities.User;

import java.util.List;

public class SearchResponse extends Response {
    List<User> result;

    public SearchResponse(String errorMessages, int errorCodes, List<User> result) {
        super(errorMessages, errorCodes);
        this.result = result;
    }
}
