package mymediaMain.response;

import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

public class PostResponse extends Response {
    String postId;

    public PostResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, String postId) {
        super(errorMessages, errorCodes);
        this.postId = postId;
    }
}
