package service;

import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mymedia.database.entities.User;

public class RegistrationServiceTest {

    RegistrationService registrationService;
    User user;

    @Before
    public void init() {
        registrationService = mock(RegistrationService.class);
        user = new User("teszt1", "teszt1", "teszt1", "teszt1");
    }

    @Test
    public void should_registrate_user() {
        Response res = registrationService.userRegistration(user);
        when(registrationService.userRegistration(user)).thenReturn(new Response(ErrorMessages.OK, ErrorCodes.OK));
    }

    @Test
    public void should_return_used_username_error() {
        User user1 = new User("teszt2", "teszt2", "teszt2", "teszt2");
        registrationService.userRegistration(user1);
        registrationService.userRegistration(user1);
        when(registrationService.userRegistration(user1)).thenReturn(new Response(ErrorMessages.USED_USERNAME, ErrorCodes.USED_USERNAME));
    }

    @Test
    public void should_return_used_email_error() {
        User user1 = new User("teszt2", "teszt2", "teszt2", "teszt2");
        User user2 = new User("teszt3", "teszt2", "teszt2", "teszt2");
        registrationService.userRegistration(user1);
        registrationService.userRegistration(user2);
        when(registrationService.userRegistration(user2)).thenReturn(new Response(ErrorMessages.USED_EMAIL, ErrorCodes.USED_EMAIL));
    }
}
