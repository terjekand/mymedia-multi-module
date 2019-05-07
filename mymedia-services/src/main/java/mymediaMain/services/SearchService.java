package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.dto.SearchDto;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.model.SearchData;
import mymediaMain.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SearchService {
    private UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public SearchService(){

    }

    public SearchResponse searchUser(SearchDto searchDto){
        if(searchDto.getDestUserId() == null || "".equals(searchDto.getDestUserId()) || searchDto.getReq() == null || "".equals(searchDto.getReq())){
            return new SearchResponse(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS, null);
        }
        List<User> users = USER_DATA_BASE.searchUser(searchDto.getReq());
        if(users.isEmpty()){
            log.info("[SearchService - searchUser] No entity found for query.");
            return new SearchResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
        }
        List<SearchData> searchDataList = new ArrayList<>();
        for(User user : users){
            searchDataList.add(Converter.convertUserToSearcData(searchDto.getDestUserId(), user));
        }
        return new SearchResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, searchDataList);
    }
}
