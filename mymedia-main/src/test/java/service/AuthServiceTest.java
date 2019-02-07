package service;

import mymediaMain.config.SessionManager;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;
import mymediaMain.services.BCrypt;
import mymediaMain.services.RegistrationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mymedia.database.entities.User;

public class AuthServiceTest {

    AuthService authService;
    RegistrationService registrationService;
    SessionManager sessionManager;
    User user;
    User user1;
    @Before
    public void init(){
        sessionManager = SessionManager.getInstance();
        sessionManager.clear();
        authService = new AuthService();
        registrationService = new RegistrationService();
        user = new User("teszt_auth_1", "teszt_auth_1", "teszt_auth_1", "teszt_auth_1");
        user1 = new User("teszt_auth_2", "teszt_auth_2", "teszt_auth_2", "teszt_auth_2");
        user.setPassword(BCrypt.hashpw("teszt_auth_1", BCrypt.gensalt()));
        user1.setPassword(BCrypt.hashpw("teszt_auth_2", BCrypt.gensalt()));
    }

    @Test
    public void should_login_user(){
        registrationService.userRegistration(user);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_1");
        authDto.setPassword("teszt_auth_1");
        Response resp = authService.login(authDto);
        Assert.assertEquals(ErrorMessages.OK, resp.getErrorMessage());
    }

    @Test
    public void should_get_permission_after_login(){
        registrationService.userRegistration(user);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_1");
        authDto.setPassword("teszt_auth_1");
        Response resp = authService.login(authDto);
        Assert.assertTrue(sessionManager.hasPermission(user.getId()));
    }

    @Test
    public void should_have_no_permission_after_logout(){
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        Response resp = authService.login(authDto);
        authService.logout(sessionManager.getTokenOfUser(user1.getId()));
        Assert.assertFalse(sessionManager.hasPermission(user1.getId()));
    }

    @Test
    public void should_return_ok_after_login(){
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        Response resp = authService.login(authDto);
        Assert.assertEquals(resp.getErrorMessage(), ErrorMessages.OK);
    }

    @Test
    public void should_return_err_after_login(){
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        authService.login(authDto);
        Response resp = authService.login(authDto);
        Assert.assertEquals(resp.getErrorMessage(), ErrorMessages.ALREADY_AUTHORIZED_USER);
    }
}
