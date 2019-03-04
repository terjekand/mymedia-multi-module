package mymediaMain.services.interfaces;

import mymediaMain.response.Response;
import org.mymedia.database.entities.User;

import java.util.List;

public interface PostInterf {
    /**
     * Returns users who liked the specific post.
     *
     * @param postId id of the post we are looking for.
     * @return a list of Users, 'likers'.
     */
    List<User> getLikersOfPost(Long postId);

    /**
     * Allows a user to like a specific post.
     *
     * @param userId we save userId's as context parameter.
     * @param postId id of the liked post .
     * @return a response whit named error_codes and error_messages look after at: mymediaMain.enums.ErrorCodes
     *                                                                             and mymediaMain.enums.ErrorMessages
     */
    Response likeWithUserId(String userId, Long postId);

    /**
     * Allows a user to like a specific post.
     *
     * @param token need userId from token, because we save userId's as context parameter.
     * @param postId id of the liked post .
     * @return a response whit named error_codes and error_messages look after at: mymediaMain.enums.ErrorCodes
     *                                                                             and mymediaMain.enums.ErrorMessages
     */
    Response likeWithToken(String token, Long postId);

}
