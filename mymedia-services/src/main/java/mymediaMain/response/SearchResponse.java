package mymediaMain.response;

import database.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.model.SearchData;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse extends Response {
    private List<SearchData> searchData;

    public SearchResponse(String errorMessages, int errorCodes, List<SearchData> searchData) {
        super(errorMessages, errorCodes);
        this.searchData = searchData;
    }
}
