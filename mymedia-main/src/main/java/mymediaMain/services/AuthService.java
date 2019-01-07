package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.config.SessionManager;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;


@Slf4j
public class AuthService {
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();
    public AuthService(){
        try {
            USER_DATA_BASE.connectDB();
            log.trace("Connected to database!");

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private Response authenticate(String userId){
        return SESSION_MANAGER.addUserId(userId);
    }

    public Response login(AuthDto authDto){
        User user = USER_DATA_BASE.getUserByUsername(authDto.getUsername());
        if (user != null && BCrypt.checkpw(authDto.getPassword(), user.getPassword())){
            return authenticate(user.getId());
        }
        return new Response(ErrorMessages.USED_USERNAME, ErrorCodes.USED_USERNAME);
    }

    public Response logout(String userId){
        return SESSION_MANAGER.removePermission(userId);
    }
}
