package mymediaMain.response;

import database.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.dto.ExtendedPost;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostListResponse extends Response{
    private List<ExtendedPost> postList;

    public PostListResponse(String errorMessages, int errorCodes, List<ExtendedPost> postList) {
        super(errorMessages, errorCodes);
        this.postList = postList;
    }
}
