package mymediaMain.response;

import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

public class PostResponse extends Response {
    Long postId;

    public PostResponse(ErrorMessages errorMessages, ErrorCodes errorCodes, Long postId) {
        super(errorMessages, errorCodes);
        this.postId = postId;
    }
}
