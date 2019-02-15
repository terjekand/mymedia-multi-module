package service;

import mymediaMain.config.SessionManager;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;
import mymediaMain.services.BCrypt;
import mymediaMain.services.RegistrationService;
import mymediaMain.servlets.auth.LoginServlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mymedia.database.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    AuthService authService;
    RegistrationService registrationService;
    SessionManager sessionManager;
    User user;
    User user1;

    @Before
    public void init() {
        sessionManager = SessionManager.getInstance();
        sessionManager.clear();
        authService = mock(AuthService.class);
        registrationService = mock(RegistrationService.class);
        user = new User("teszt_auth_1", "teszt_auth_1", "teszt_auth_1", "teszt_auth_1");
        user1 = new User("teszt_auth_2", "teszt_auth_2", "teszt_auth_2", "teszt_auth_2");
        user.setPassword(BCrypt.hashpw("teszt_auth_1", BCrypt.gensalt()));
        user1.setPassword(BCrypt.hashpw("teszt_auth_2", BCrypt.gensalt()));
    }

    @Test
    public void should_login_user() {
        registrationService.userRegistration(user);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_1");
        authDto.setPassword("teszt_auth_1");
        when(authService.login(authDto)).thenReturn(new Response(ErrorMessages.OK, ErrorCodes.OK));
    }

    @Test
    public void should_get_permission_after_login() {
        AuthService authService1 = new AuthService();
        RegistrationService registrationService1 = new RegistrationService();
        registrationService1.userRegistration(user);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_1");
        authDto.setPassword("teszt_auth_1");
        authService1.login(authDto);
        Assert.assertTrue(sessionManager.hasPermission(user.getId()));
    }

    @Test
    public void should_have_no_permission_after_logout() {
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        Response resp = authService.login(authDto);
        authService.logout(sessionManager.getTokenOfUser(user1.getId()));
        Assert.assertFalse(sessionManager.hasPermission(user1.getId()));
    }

    @Test
    public void should_return_ok_after_login() {
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        when(authService.login(authDto)).thenReturn(new Response(ErrorMessages.OK, ErrorCodes.OK));
    }

    @Test
    public void should_return_already_authorized_user_error_after_login() {
        registrationService.userRegistration(user1);
        AuthDto authDto = new AuthDto();
        authDto.setUsername("teszt_auth_2");
        authDto.setPassword("teszt_auth_2");
        authService.login(authDto);
        Response resp = authService.login(authDto);
        when(authService.login(authDto)).thenReturn(new Response(ErrorMessages.ALREADY_AUTHORIZED_USER, ErrorCodes.ALREADY_AUTHORIZED_USER));
    }
}
