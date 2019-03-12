package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.interfaces.AuthInterf;


@Slf4j
public class AuthService implements AuthInterf {
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

//    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();

    public AuthService() {
    }

    private Response authenticate(String userId) {
        return SESSION_MANAGER.addUserId(userId);
    }

    @Override
    public Response login(AuthDto authDto) {
//        User user = USER_DATA_BASE.getUserByUsername(authDto.getUsername());
////        if (user != null && BCrypt.checkpw(authDto.getPassword(), user.getPassword())) {
////            return authenticate(user.getId());
////        }
////        return new Response(ErrorMessages.USED_USERNAME, ErrorCodes.USED_USERNAME);
        return new Response(ErrorMessages.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public Response logout(String token) {
//        return SESSION_MANAGER.removePermission(token);
        return new Response(ErrorMessages.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED);
    }
}
