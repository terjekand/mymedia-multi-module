package mymediaMain.response;


public class PostResponse extends Response {
    String postId;

    public PostResponse(String errorMessages, int errorCodes, String postId) {
        super(errorMessages, errorCodes);
        this.postId = postId;
    }
}
