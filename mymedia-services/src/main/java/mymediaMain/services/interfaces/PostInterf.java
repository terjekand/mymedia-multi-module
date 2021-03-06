package mymediaMain.services.interfaces;

import database.entities.Post;
import mymediaMain.dto.CreatePostDto;
import mymediaMain.response.LikersResponse;
import mymediaMain.response.PostListResponse;
import mymediaMain.response.PostResponse;
import mymediaMain.response.Response;
import database.entities.User;

import java.util.List;

public interface PostInterf {
    /**
     * Returns users who liked the specific post.
     *
     * @param postId id of the post we are looking for.
     * @return a list of Users, 'likers' with error codes and massages.
     */
    LikersResponse getLikersOfPost(String postId);

    /**
     * Allows a user to like a specific post.
     *
     * @param userId we save userId's as context parameter.
     * @param postId id of the liked post .
     * @return a response whit named error_codes and error_messages look after at: mymediaMain.enums.ErrorCodes
     *                                                                             and mymediaMain.enums.ErrorMessages
     */
    Response likeWithUserId(String userId, String postId);

    /**
     * Allows a user to like a specific post.
     *
     * @param token need userId from token, because we save userId's as context parameter.
     * @param postId id of the liked post .
     * @return a response whit named error_codes and error_messages look after at: mymediaMain.enums.ErrorCodes
     *                                                                             and mymediaMain.enums.ErrorMessages
     */
    Response likeWithToken(String token, String postId);

    /**
     * Used when a user creates a new post.
     * Save the specific post to the database.
     *
     * @param createPostDto the post what we wants to save.
     * @return PostResponse contains ErrorMessages, ErrorCodes and the id of the post.
     */
    PostResponse createPost(CreatePostDto createPostDto);

    /**
     * Returns a post for an id if it's exists.
     *
     * @param postId the id of the post, what we are looking for.
     * @return the post entity.
     */
    Post getPostById(String postId);

    /**
     * Returns the posts of the user by its userId.
     *
     * @param userId the ID of the user.
     * @return a list with posts who's belong to the user.
     */
    PostListResponse getPostsOfUserByUserId(String userId);

    /**
     * Returns the posts of the user by its username.
     *
     * @param username the username of the user.
     * @return a list with posts who's belong to the user.
     */
    PostListResponse getPostsOfUserByUsername(String username);

    /**
     * Returns the posts of the user by its token, if its authorized.
     *
     * @param token the token of the authorized user.
     * @return a list with posts who's belong to the user.
     */
    PostListResponse getPostsOfUserByToken(String token);

    /**
     * Deletes the post which belongs to te given id.
     *
     * @param postId the id of the post what we wants to delete.
     * @return basic response with an error code and message.
     */
    Response deletePostById(String postId);



}
