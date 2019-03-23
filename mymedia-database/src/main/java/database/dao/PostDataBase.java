package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.entities.Post;
import database.entities.User;
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

    private Post convertResultSetToPost(ResultSet resultSet){
        Post post = new Post();
        try{
            post.setId(resultSet.getLong("ID"));
            post.setLikers(resultSet.getString("LIKERS"));
            post.setPostDate(resultSet.getDate("POST_DATE"));
            post.setText(resultSet.getString("TEXT"));
            post.setUserId(resultSet.getString("USERID"));
            return post;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

    public void save(Post post) throws SQLException{
        Statement statement = databaseConnector.connection.createStatement();
        statement.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES('" + post.getId() + "', '" +
                    post.getUserId() + "', '" +
                    post.getText() + "', '" +
                    post.getLikers() + "', '" +
                    post.getPostDate() + "')");

    }

    public void updateLikers(Post post) throws SQLException{
        String sql = "update " + TABLE_NAME + " set likers ='" + post.getLikers() + "' where id =" + post.getId();
        Statement statement = databaseConnector.connection.createStatement();
        statement.executeUpdate(sql);
    }

    public Post getPostById(Long postId){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE ID = '"+ postId +"'" );
            if (resultSet.next()){
                return convertResultSetToPost(resultSet);
            }
            return null;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

}
