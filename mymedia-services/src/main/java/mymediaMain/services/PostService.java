package mymediaMain.services;

import database.dao.PostDataBase;
import database.dao.UserDataBase;
import database.entities.Post;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.CreatePostDto;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.LikersResponse;
import mymediaMain.response.PostListResponse;
import mymediaMain.response.PostResponse;
import mymediaMain.response.Response;
import mymediaMain.services.interfaces.PostInterf;
import database.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PostService implements PostInterf {

    private static final PostDataBase POST_DATA_BASE = PostDataBase.getInstance();
    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();
    private static final String DIVIDER = "###";

    /**
     * Convert String to a list.
     * The liker's of a post is stored in a concatenated string divide with DIVIDER.
     * This function creates substrings and return them in a list.
     *
     * @param concatedIdString it usually comes from the database.
     * @return divided userId list.
     */
    private List<String> convertStringToIdList(String concatedIdString){
        List<String> result = new ArrayList<>();
        String tmp;

        while(concatedIdString.contains(DIVIDER)){
            tmp = concatedIdString.substring(0, concatedIdString.indexOf(DIVIDER));
            result.add(tmp);
            concatedIdString = concatedIdString.substring(concatedIdString.indexOf(DIVIDER) + DIVIDER.length());
        }
        return result;
    }

    /**
     * Convert userId list to a list witch contains Users for the ids.
     *
     * @param userIds a list with user ids.
     * @return list of users.
     */
    private List<User> convertIdsToUsers(List<String> userIds){
        List<User> users = new ArrayList<>();
        for(String id : userIds){
            User tmp = USER_DATA_BASE.getUserById(id);
            if(tmp != null)
                users.add(tmp);
            else
                log.error("Unable to get User for this id: " + id);
        }
        return users;
    }

    @Override
    public LikersResponse getLikersOfPost(String postId){
        Post post = POST_DATA_BASE.getPostById(postId);
        if(post == null){
            return new LikersResponse(ResponseUtil.MSG_POST_DOES_NOT_EXIST, ResponseUtil.CODE_POST_DOES_NOT_EXIST, null);
        }
        List<String> userIdList = convertStringToIdList(post.getLikers());
        List<User> likers = convertIdsToUsers(userIdList);
        return new LikersResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, likers);
    }

    @Override
    public Response likeWithToken(String token, String postId){
        String userId = SESSION_MANAGER.getUserIdByToken(token);
        return likeWithUserId(userId, postId);
    }

    @Override
    public PostResponse createPost(CreatePostDto createPostDto) {
        Post post = new Post(createPostDto.getUserId(), createPostDto.getText());
        try{
            POST_DATA_BASE.save(post);
        } catch (SQLException e){
            log.error("An error occurred while save post!\n" + e);
            return new PostResponse(ResponseUtil.MSG_SAVE_POST_FAILED, ResponseUtil.CODE_SAVE_POST_FAILED, null);
        }
        return new PostResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, post.getId());
    }

    @Override
    public Response likeWithUserId(String userId, String postId){
        Post post = POST_DATA_BASE.getPostById(postId);
        if(post == null){
            return new Response(ResponseUtil.MSG_POST_DOES_NOT_EXIST, ResponseUtil.CODE_POST_DOES_NOT_EXIST);
        }
        if(userId == null || userId.equals("")){
            return new Response(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS);
        }
        String newLike = userId + DIVIDER;
        String likers = post.getLikers();
        if(likers.contains(newLike)){
            String before = likers.substring(0, likers.indexOf(newLike));
            String after = likers.substring(likers.indexOf(newLike) + newLike.length());
            likers = before + after;
        }
        else{
            likers += newLike;
        }
        post.setLikers(likers);
        try{
            POST_DATA_BASE.updateLikers(post);
        } catch (SQLException e){
            log.error("" + e);
            return new Response(ResponseUtil.MSG_LIKERS_UPDATE_ERROR, ResponseUtil.CODE_LIKERS_UPDATE_ERROR);
        }
        return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
    }

    @Override
    public Post getPostById(String postId) {
        return POST_DATA_BASE.getPostById(postId);
    }

    @Override
    public PostListResponse getPostsOfUserByUserId(String userId) {
        if(userId == null || userId.equals("")){
            return new PostListResponse(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS, null);
        }
        List<Post> posts = POST_DATA_BASE.getPostByUserId(userId);
        return new PostListResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, posts);
    }

    @Override
    public PostListResponse getPostsOfUserByUsername(String username) {
        String userId = USER_DATA_BASE.getUserIdByUsername(username);
        return getPostsOfUserByUserId(userId);
    }

    @Override
    public PostListResponse getPostsOfUserByToken(String token) {
        String userId = SESSION_MANAGER.getUserIdByToken(token);

        return getPostsOfUserByUserId(userId);
    }
}
