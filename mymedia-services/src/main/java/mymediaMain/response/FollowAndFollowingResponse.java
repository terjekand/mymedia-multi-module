package mymediaMain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowAndFollowingResponse extends Response {
    private Long following;
    private Long followers;
    private Long posts;

    public FollowAndFollowingResponse(String errorMessages, int errorCodes, Long following, Long followers, Long posts) {
        super(errorMessages, errorCodes);
        this.following = following;
        this.followers = followers;
        this.posts = posts;
    }
}
