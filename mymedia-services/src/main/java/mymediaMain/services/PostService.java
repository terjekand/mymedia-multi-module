package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.interfaces.PostInterf;
import database.entities.User;

import java.util.List;

/**
 * This service is not tested.
 * TODO: TEST!!!
 * REIMPLEMENT METHODS
 */
@Slf4j
public class PostService implements PostInterf {
    private SessionManager sessionManager = SessionManager.getInstance();

    @Override
    public List<User> getLikersOfPost(Long postId){
        return null;
    }

    @Override
    public Response likeWithToken(String token, Long postId){
        /**
         *
         String userId = sessionManager.getUserIdByToken(token);
         if(userId == null || "".equals(userId)){
         log.error("[likeWithToken] - TOKEN_DOES_NOT_EXIST, token: " + token);
         return new Response(ErrorMessages.TOKEN_DOES_NOT_EXIST, ErrorCodes.TOKEN_DOES_NOT_EXIST);
         }
         return likeWithUserId(userId, postId);
         */
        return new Response(ErrorMessages.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public Response likeWithUserId(String userId, Long postId){
        /**
         *
         if(userId == null || "".equals(userId) || postId == null || postId < 0L){
         log.error("[likeWithUserId] - Some parameters are null or empty");
         return new Response(ErrorMessages.BAD_PARAMETERS, ErrorCodes.BAD_PARAMETERS);
         }
         User user = USER_DATA_BASE.getUserById(userId);

         if(user == null){
         log.error("No user found to the given id");
         return new Response(ErrorMessages.USER_NOT_FOUND, ErrorCodes.USER_NOT_FOUND);
         }

         Post post = POST_DATA_BASE.getPostByPostId(postId);

         if(post == null){
         log.error("[likeWithToken] - POST_DOES_NOT_EXIST, postId: " + postId);
         return new Response(ErrorMessages.POST_DOES_NOT_EXIST, ErrorCodes.POST_DOES_NOT_EXIST);
         }
         List<User> likersTemplate = post.getLikers();
         likersTemplate.add(user);

         try{
         POST_DATA_BASE.save(post);
         return new Response(ErrorMessages.OK, ErrorCodes.OK);
         } catch(Exception ex){
         log.error("" + ex);
         return new Response(ErrorMessages.NO_DATA_BASE_CONNECTION, ErrorCodes.NO_DATA_BASE_CONNECTION);
         }
         */
        return new Response(ErrorMessages.NOT_IMPLEMENTED,ErrorCodes.NOT_IMPLEMENTED);
    }
}
