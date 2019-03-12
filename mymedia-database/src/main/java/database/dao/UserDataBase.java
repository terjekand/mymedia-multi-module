package database.dao;


import database.entities.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class UserDataBase {

    private static final UserDataBase USER_DATA_BASE = new UserDataBase();
    private static final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    private static final String TABLE_NAME = "USERS";
    private UserDataBase(){

    }

    public static final UserDataBase getInstance() {
        return USER_DATA_BASE;
    }

    public void save(User user){

        try {

            Statement statement = databaseConnector.connection.createStatement();
            statement.executeUpdate("INSERT INTO " + TABLE_NAME  + " VALUES('" + user.getId() + "', '" +
                    user.getUsername() + "', '" +
                    user.getPassword() + "', '" +
                    user.getEmail()    + "', '" +
                    user.getFullname() + "')");
            /**
             * "INSERT INTO " + TABLE_NAME + " + " VALUES("' + user.getId() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getFullname + "')"
            */

        } catch (SQLException e) {
            log.error(e + "");
        }
    }

    private User convertResultSetToUser(ResultSet resultSet){
        User user = new User();
        try{
            user.setId(resultSet.getString("ID"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setFullname(resultSet.getString("FULLNAME"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setUsername(resultSet.getString("USERNAME"));
            return user;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

    public User getUserById(String id){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE ID = '"+ id +"'" );
            if (resultSet.next()){
                return convertResultSetToUser(resultSet);
            }
            return null;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

    public User getUserByUsername(String username){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE USERNAME = '"+ username +"'" );
            if (resultSet.next()){
                return convertResultSetToUser(resultSet);
            }
            return null;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

    public User getUserByEmail(String email){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE EMAIL = '"+ email +"'" );
            if (resultSet.next()){
                return convertResultSetToUser(resultSet);
            }
            return null;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }
}
