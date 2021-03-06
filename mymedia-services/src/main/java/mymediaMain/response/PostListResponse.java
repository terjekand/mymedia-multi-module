package mymediaMain.response;

import database.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostListResponse extends Response{
    private List<Post> postList;

    public PostListResponse(String errorMessages, int errorCodes, List<Post> postList) {
        super(errorMessages, errorCodes);
        this.postList = postList;
    }
}
