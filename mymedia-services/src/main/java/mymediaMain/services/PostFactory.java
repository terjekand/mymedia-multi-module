package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.Post;
import database.entities.User;

public class PostFactory {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public static Post create(String userId, String text){
        if (userId == null || "".equals(userId) || text == null || "".equals(text)) {
            return null;
        }
        User user = USER_DATA_BASE.getUserById(userId);
        if(user == null){
            return null;
        }
        Post post = new Post(userId, text);
        post.setUserName(user.getUsername());
        return post;
    }
}
