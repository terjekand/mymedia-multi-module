package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.UpdateProfileDto;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.PersonalDataResponse;
import mymediaMain.response.Response;

import java.sql.SQLException;

@Slf4j
public class PersonalService {
    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    public PersonalDataResponse getPersonalData(String userId){
        if(userId == null || userId.equals("")){
            return new PersonalDataResponse(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS, null);
        }
        User user = USER_DATA_BASE.getUserById(userId);

        if(user == null){
            return new PersonalDataResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
        }
        return new PersonalDataResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK,Converter.convertUserToPersonalData(user));
    }

    private Response updatePersonalDataByToken(UpdateProfileDto updateProfileDto){
        String userId = SESSION_MANAGER.getUserIdByToken(updateProfileDto.getToken());
        updateProfileDto.setUserId(userId);
        return updatePersonalDataByUserId(updateProfileDto);
    }

    private Response updatePersonalDataByUserId(UpdateProfileDto updateProfileDto){
        User user = Converter.convertUpdateProfileDtoToUser(updateProfileDto);
        if(user == null){
            return new Response(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND);
        }
        try{
            USER_DATA_BASE.update(user);
            return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
        } catch (SQLException e){
            log.error("" + e);
            return new Response(ResponseUtil.MSG_USER_UPDATE_ERROR, ResponseUtil.CODE_USER_UPDATE_ERROR);
        }
    }

    public Response updatePersonalData(UpdateProfileDto updateProfileDto){
        if(updateProfileDto.getToken() != null && !updateProfileDto.getToken().equals("")){
            return updatePersonalDataByToken(updateProfileDto);
        }
        else if(updateProfileDto.getUserId() != null && !updateProfileDto.getUserId().equals("")){
            return updatePersonalDataByUserId(updateProfileDto);
        }
        else{
            log.error("Some of the parameters were null (userId/token");
            return new Response(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS);
        }
    }

}
