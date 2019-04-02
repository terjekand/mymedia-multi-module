package mymediaMain.response;

import database.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse extends Response {
    List<User> result;

    public SearchResponse(String errorMessages, int errorCodes, List<User> result) {
        super(errorMessages, errorCodes);
        this.result = result;
    }
}
