package security;


import mymediaMain.config.SessionManager;
import mymediaMain.exceptions.MalformedTokenException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mymedia.database.entities.User;

import java.util.UUID;


public class SessionManagerTest {
    SessionManager sessionManager;
    User user;
    @Before
    public void init(){
        sessionManager = SessionManager.getInstance();
        sessionManager.clear();
        user = new User("teszt1" ,"teszt1", "teszt1","teszt1");
    }

    @Test
    public void should_return_zero_for_number_of_users_after_init_the_class(){
        Assert.assertEquals(0, sessionManager.numberOfUsers());
    }

    @Test
    public void should_return_false_of_user_permission(){
        Assert.assertFalse(sessionManager.hasPermission(user.getId()));
    }

    @Test
    public void should_add_permission_to_user(){
        sessionManager.addUserId(user.getId());
        Assert.assertTrue(sessionManager.hasPermission(user.getId()));
    }

    @Test
    public void should_remove_permission_of_user(){
        sessionManager.addUserId(user.getId());
        sessionManager.removePermission(sessionManager.getTokenOfUser(user.getId()));
        Assert.assertFalse(sessionManager.hasPermission(user.getId()));
    }

    @Test
    public void should_return_ten_tokens(){
        for(int i = 0; i < 10; i++){
            String temp = UUID.randomUUID().toString();
            sessionManager.addUserId(temp);
        }
        Assert.assertEquals(10 ,sessionManager.listAllTokensOfSession().size());
    }

    @Test
    public void should_return_token_of_one_user(){
        sessionManager.addUserId(user.getId());
        Assert.assertEquals(sessionManager.listAllTokensOfSession().size(), 1);
    }

}
