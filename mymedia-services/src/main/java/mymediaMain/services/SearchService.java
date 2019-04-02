package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.SearchResponse;
import java.util.List;

@Slf4j
public class SearchService {
    private UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public SearchService(){

    }

    public SearchResponse searchUser(String req){
        List<User> users = USER_DATA_BASE.searchUser(req);
        if(users.isEmpty()){
            log.info("[SearchService - searchUser] No entity found for query.");
            return new SearchResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
        }
        return new SearchResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, users);
    }
}
