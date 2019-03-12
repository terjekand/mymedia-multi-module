package mymediaMain.services;

import database.dao.UserDataBase;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.dto.RegistrationDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
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
            return new Response(ErrorMessages.USED_EMAIL, ErrorCodes.USED_EMAIL);
        }

        if(isUsedUsername(user.getUsername())){
            return new Response(ErrorMessages.USED_USERNAME, ErrorCodes.USED_USERNAME);
        }
        try {
            String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashPw);
             USER_DATA_BASE.save(user);
        } catch (Exception e) {
            log.error("" + e);
            return new Response(ErrorMessages.UNKNOWN_ERROR, ErrorCodes.UNKNOWN_ERROR);
        }

        return new Response(ErrorMessages.OK, ErrorCodes.OK);
    }
}
