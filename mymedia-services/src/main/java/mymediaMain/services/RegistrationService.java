package mymediaMain.services;

import database.dao.UserDataBase;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.dto.RegistrationDto;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.Response;
import database.entities.User;

@Slf4j
public class RegistrationService {


    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public RegistrationService() {
    }


    private Boolean isUsedUsername(String username) {
        return USER_DATA_BASE.getUserByUsername(username) != null;
    }

    private Boolean isUsedEmail(String email) {
        return USER_DATA_BASE.getUserByEmail(email) != null;
    }

    private User createUser(RegistrationDto registrationDto){
        return new User(registrationDto.getUsername(),
                        registrationDto.getPassword(),
                        registrationDto.getEmail(),
                        registrationDto.getFullname());
    }

    public Response userRegistration(RegistrationDto registrationDto) {

        User user = createUser(registrationDto);

        if(isUsedEmail(user.getEmail())){
            return new Response(ResponseUtil.MSG_USED_EMAIL, ResponseUtil.CODE_USED_EMAIL);
        }

        if(isUsedUsername(user.getUsername())){
            return new Response(ResponseUtil.MSG_USED_USERNAME, ResponseUtil.CODE_USED_USERNAME);
        }
        try {
            String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashPw);
             USER_DATA_BASE.save(user);
        } catch (Exception e) {
            log.error("" + e);
            return new Response(ResponseUtil.MSG_UNKNOWN_ERROR, ResponseUtil.CODE_UNKNOWN_ERROR);
        }

        return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
    }
}
