package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserIdResponse extends Response {
    private String userId;

    public UserIdResponse(String errorMessages, int errorCodes, String userId) {
        super(errorMessages, errorCodes);
        this.userId = userId;
    }
}
