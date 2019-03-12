package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.interfaces.PostInterf;
import database.entities.User;

import java.util.List;

@Slf4j
public class PostService implements PostInterf {
    private SessionManager sessionManager = SessionManager.getInstance();

    @Override
    public List<User> getLikersOfPost(Long postId){
        return null;
    }

    @Override
    public Response likeWithToken(String token, Long postId){
        return new Response(ErrorMessages.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public Response likeWithUserId(String userId, Long postId){
        return new Response(ErrorMessages.NOT_IMPLEMENTED,ErrorCodes.NOT_IMPLEMENTED);
    }
}
