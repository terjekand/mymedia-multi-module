package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.LoginDto;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.LoginResponse;
import mymediaMain.response.Response;
import mymediaMain.response.UserResponse;
import mymediaMain.services.interfaces.AuthInterf;


@Slf4j
public class AuthService implements AuthInterf {
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public AuthService() {
    }

    private LoginResponse authenticate(String userId) {
        return SESSION_MANAGER.addUserId(userId);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        User user = USER_DATA_BASE.getUserByUsername(loginDto.getUsername());
        if (user != null && BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
            return authenticate(user.getId());
        }
        return new LoginResponse(ResponseUtil.MSG_WRONG_USERNAME, ResponseUtil.CODE_WRONG_USERNAME, null);
    }

    @Override
    public Response logout(String token) {
        return SESSION_MANAGER.removePermission(token);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = USER_DATA_BASE.getUserById(id);
        if(user != null){
            return new UserResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, user);
        }
        return new UserResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user = USER_DATA_BASE.getUserByUsername(username);
        if(user != null){
            return new UserResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, user);
        }
        return new UserResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = USER_DATA_BASE.getUserByEmail(email);
        if(user != null){
            return new UserResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, user);
        }
        return new UserResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
    }

    public void logoutALlUser(){
        SESSION_MANAGER.clear();
    }
}
