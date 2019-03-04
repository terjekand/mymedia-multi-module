package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import database.dao.PostDataBase;
import database.dao.UserDataBase;

@Slf4j
public class Connector {


    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();
    private static final PostDataBase POST_DATA_BASE = PostDataBase.getDataBase();


    public void userConnection(){
        if (!USER_DATA_BASE.connected()) {
            try {
                USER_DATA_BASE.connectDB();
                log.trace("Connected to database!");

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    public void postConnection(){

        if (!POST_DATA_BASE.connected()) {
            try {
                POST_DATA_BASE.connectDB();
                log.trace("Connected to database!");

            } catch (Exception e) {
                log.error("" + e);
            }
        }
    }

    public void fullConnection(){
        userConnection();
        postConnection();
    }
}
