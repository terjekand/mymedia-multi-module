package database.dao;


import database.entities.User;
import lombok.extern.slf4j.Slf4j;

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
            statement.executeUpdate("INSERT INTO " + TABLE_NAME  + "VALUES('" + user.getId() + "', '" +
                    user.getUsername() + "', '" +
                    user.getPassword() + "', '" +
                    user.getEmail()    + "', '" +
                    user.getFullname() + "')");
            /**
             *
             * "INSERT INTO " + TABLE_NAME + " + "VALUES("' + user.getId() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getFullname + "')"
             *
             */

        } catch (SQLException e) {
            log.error(e + "");
        }
    }
}
