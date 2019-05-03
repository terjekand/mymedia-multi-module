package mymediaMain.services;


import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.Response;

import java.sql.SQLException;

@Slf4j
public class FollowingService {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    public Response followWithUserId(String followerUserId, String destUserId){

        if(followerUserId == null || followerUserId.equals("") || destUserId == null || destUserId.equals("")){
            return new Response(ResponseUtil.MSG_BAD_PARAMETERS, ResponseUtil.CODE_BAD_PARAMETERS);
        }
        User followerUser = USER_DATA_BASE.getUserById(followerUserId);
        if(followerUser == null){
            return new Response(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND);
        }
        User destUser = USER_DATA_BASE.getUserById(destUserId);
        if(destUser == null){
            return new Response(ResponseUtil.MSG_USER_NOT_FOUND, ResponseUtil.CODE_USER_NOT_FOUND);
        }
        String newFollower = followerUserId + Converter.DIVIDER;
        String followers = destUser.getFollowers();
        followers = Converter.addOrRemoveIfContains(followers, newFollower);
        destUser.setFollowers(followers);
        try{
            USER_DATA_BASE.updateFollowers(destUser);
        } catch (SQLException e){
            log.error("Exception occurred while try to update followers in user with id of:" + destUser.getId() + e);
            return new Response(ResponseUtil.MSG_LIKERS_UPDATE_ERROR, ResponseUtil.CODE_LIKERS_UPDATE_ERROR);
        }

        String newFollowing = destUserId + Converter.DIVIDER;
        String following = followerUser.getFollowing();
        following = Converter.addOrRemoveIfContains(following, newFollowing);
        followerUser.setFollowing(following);
        try{
            USER_DATA_BASE.updateFollowing(followerUser);
        } catch (SQLException e){
            log.error("Exception occurred while try to update following in user with id of:" + followerUser.getId() + e);
            return new Response(ResponseUtil.MSG_LIKERS_UPDATE_ERROR, ResponseUtil.CODE_LIKERS_UPDATE_ERROR);
        }
        return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
    }

    public Response followWithToken(String token, String destUserId){
        String followerUserId = SESSION_MANAGER.getUserIdByToken(token);
        return followWithUserId(followerUserId, destUserId);
    }
}
