package mymediaMain.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse extends Response {
    private String postId;

    public PostResponse(String errorMessages, int errorCodes, String postId) {
        super(errorMessages, errorCodes);
        this.postId = postId;
    }
}
