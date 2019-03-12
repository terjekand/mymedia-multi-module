package database.dao;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostDataBase {

    private static final PostDataBase POST_DATA_BASE = new PostDataBase();

    private static final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    private static final String TABLE_NAME = "POSTS";

    private PostDataBase(){
        try{
            if(databaseConnector.connection.isClosed()){
                databaseConnector.connect();
            }
        } catch (SQLException e){
            log.error(e + "");
        }
    }

    public static final  PostDataBase getInstance(){
        return POST_DATA_BASE;
    }


}
