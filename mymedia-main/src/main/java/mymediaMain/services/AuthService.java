package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.Response.Response;
import mymediaMain.config.SessionManager;
import org.mymedia.database.dao.UserDataBase;


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
    public Response authenticate(Long userId){
        return SESSION_MANAGER.addUserId(userId);
    }

    public Response logout(Long userId){
        return SESSION_MANAGER.removePermission(userId);
    }
}
