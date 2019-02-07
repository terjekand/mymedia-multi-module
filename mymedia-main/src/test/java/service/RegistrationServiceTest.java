package service;

import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.RegistrationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mymedia.database.entities.User;

public class RegistrationServiceTest {

    RegistrationService registrationService;
    User user;

    @Before
    public void init(){
        registrationService = new RegistrationService();
        user = new User("teszt1", "teszt1", "teszt1", "teszt1");
    }

    @Test
    public void should_registrate_user(){
        Response res = registrationService.userRegistration(user);
        Assert.assertEquals(ErrorMessages.OK, res.getErrorMessage());
    }

    @Test
    public void should_not_registrate_user_second_time(){
        User user1 = new User("teszt2", "teszt2", "teszt2", "teszt2");
        registrationService.userRegistration(user1);
        Response res = registrationService.userRegistration(user1);
        Assert.assertNotEquals(ErrorMessages.OK, res.getErrorMessage());
    }

    @Test
    public void should_return_used_username_error(){
        User user1 = new User("teszt2", "teszt2", "teszt2", "teszt2");
        registrationService.userRegistration(user1);
        Response res = registrationService.userRegistration(user1);
        Assert.assertEquals(ErrorMessages.USED_USERNAME, res.getErrorMessage());
    }

    @Test
    public void should_return_used_email_error(){
        User user1 = new User("teszt2", "teszt2", "teszt2", "teszt2");
        User user2 = new User("teszt3", "teszt2", "teszt2", "teszt2");
        registrationService.userRegistration(user1);
        Response res = registrationService.userRegistration(user2);
        Assert.assertEquals(ErrorMessages.USED_EMAIL, res.getErrorMessage());
    }
}
