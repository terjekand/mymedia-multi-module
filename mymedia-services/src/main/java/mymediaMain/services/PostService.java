package mymediaMain.services;

import database.dao.PostDataBase;
import database.dao.UserDataBase;
import database.entities.Post;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.CreatePostDto;
import mymediaMain.dto.CreatePostWithTokenDto;
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



    @Override
    public LikersResponse getLikersOfPost(String postId){
        Post post = POST_DATA_BASE.getPostById(postId);
        if(post == null){
            return new LikersResponse(ResponseUtil.MSG_POST_DOES_NOT_EXIST, ResponseUtil.CODE_POST_DOES_NOT_EXIST, null);
        }
        List<String> userIdList = Converter.convertStringToIdList(post.getLikers());
        List<User> likers = Converter.convertIdsToUsers(userIdList);
        return new LikersResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, likers);
    }

    @Override
    public Response likeWithToken(String token, String postId){
        String userId = SESSION_MANAGER.getUserIdByToken(token);
        return likeWithUserId(userId, postId);
    }

    public PostResponse createPostWithToken(CreatePostWithTokenDto CreatePostWithTokenDto){
        String userId = SESSION_MANAGER.getUserIdByToken(CreatePostWithTokenDto.getToken());
        if(userId == null){
            return new PostResponse(ResponseUtil.MSG_TOKEN_DOES_NOT_EXIST, ResponseUtil.CODE_TOKEN_DOES_NOT_EXIST, null);
        }
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setText(CreatePostWithTokenDto.getText());
        createPostDto.setUserId(userId);
        return createPost(createPostDto);
    }

    @Override
    public PostResponse createPost(CreatePostDto createPostDto) {
        if(createPostDto.getText() == null || "".equals(createPostDto.getText()) ||
           createPostDto.getUserId() == null ||"".equals(createPostDto.getUserId())){
            return  new PostResponse(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS, null);
        }
        User user = USER_DATA_BASE.getUserById(createPostDto.getUserId());
        if(user == null){
            return new PostResponse(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND, null);
        }
        Post post = PostFactory.create(createPostDto.getUserId(), createPostDto.getText());
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
        String newLike = userId + Converter.DIVIDER;
        String likers = post.getLikers();
        likers = Converter.addOrRemoveIfContains(likers, newLike);
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

    @Override
    public Response deletePostById(String postId) {

        if(postId == null || postId == ""){
            return new Response(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS);
        }

        Post post = POST_DATA_BASE.getPostById(postId);
        if(post == null){
            return new Response(ResponseUtil.MSG_POST_DOES_NOT_EXIST, ResponseUtil.CODE_POST_DOES_NOT_EXIST);
        }
        try{
            POST_DATA_BASE.deletePostById(postId);
        } catch (SQLException e){
            log.error("An error occurred while delete post: " + e);
            return new Response(ResponseUtil.MSG_DELETE_POST_FAILED, ResponseUtil.CODE_DELETE_POST_FAILED);
        }
        return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
    }

}
