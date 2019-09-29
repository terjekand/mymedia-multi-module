package mymediaMain.services;

import database.dao.PostDataBase;
import database.dao.UserDataBase;
import database.entities.Post;
import database.entities.User;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.ExtendedPost;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.PostListResponse;

import java.util.ArrayList;
import java.util.List;


public class NewsFeedService {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();
    private static final PostDataBase POST_DATA_BASE = PostDataBase.getInstance();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    public PostListResponse fillNewsFeedByUserId(String userId){
        User user = USER_DATA_BASE.getUserById(userId);
        if(user == null){
            return new PostListResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
        }
        List<String> userIds = Converter.convertStringToIdList(user.getFollowing());
        userIds.add(userId);
        List<Post> posts = POST_DATA_BASE.getPostsByUserIdList(userIds);

        return new PostListResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, Converter.convertPostListToExtendedPostList(posts));
    }

    public PostListResponse fillNewsFeedByToken(String token){
        String userId = SESSION_MANAGER.getUserIdByToken(token);
        return fillNewsFeedByUserId(userId);
    }
}
