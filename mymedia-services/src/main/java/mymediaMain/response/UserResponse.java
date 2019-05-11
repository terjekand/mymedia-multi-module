package mymediaMain.response;

import database.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse extends Response{
    private User user;

    public UserResponse(String errorMessages, int errorCodes, User user) {
        super(errorMessages, errorCodes);
        this.user = user;
    }
}
