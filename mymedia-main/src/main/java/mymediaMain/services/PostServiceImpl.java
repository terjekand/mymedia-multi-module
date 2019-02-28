package mymediaMain.services;

import mymediaMain.config.SessionManager;
import mymediaMain.response.Response;
import org.mymedia.database.dao.PostDataBase;

public class PostServiceImpl {
    private PostDataBase POST_DATA_BASE = PostDataBase.getDataBase();
    private SessionManager sessionManager = SessionManager.getInstance();
    PostServiceImpl(){

    }

    public String getLikersOfPost(Long postId){
        return POST_DATA_BASE.getLikersOfPost(postId);
    }

    public Response likeWithToken(String token, Long postId){
        return null;
    }

    public Response likeWithUserId(String userId, Long postId){
        return null;
    }
}
