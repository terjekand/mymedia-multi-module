package database.dao;


import database.entities.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            user.setBio(resultSet.getString("BIO"));
            user.setPhoneNumber(resultSet.getString("PHONENUMBER"));
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

    public String getUserIdByUsername(String username){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE USERNAME = '"+ username +"'" );
            if (resultSet.next()){
                return resultSet.getString("ID");
            }
            return null;
        }catch (SQLException e){
            log.error(e + "");
            return null;
        }
    }

    public List<User> searchUser(String req){
        try{
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME  + " WHERE USERNAME like '%"+ req +"%' OR fullname like '%"+ req +"%'" );
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                users.add(convertResultSetToUser(resultSet));
            }
            return users;
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
