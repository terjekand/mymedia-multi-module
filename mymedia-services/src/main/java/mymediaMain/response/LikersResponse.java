package mymediaMain.response;

import database.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LikersResponse extends Response {
    private List<User> likers;

    public LikersResponse(String errorMessages, int errorCodes, List<User> likers) {
        super(errorMessages, errorCodes);
        this.likers = likers;
    }
}
