package database.dao;

import database.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DataAdapter {
    //jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "kiss";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void connect(){
        try{
//            connection = DriverManager.getConnection(DATABASE_URL);
            connection=DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            log.info("Connected to PostgreSQL database!");
        } catch (SQLException e){
            log.error(e + "");
        }
    }

    public  void disconnect(){
        try{
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
            if(connection != null){
                connection.close();
            }
            log.info("Disconnected from PostgreSQL database!");
        } catch (SQLException e){
            log.error(e + "");
        }
    }

    public void saveUser(User user){

        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO USERS " + "VALUES ('admin', 'admin', 'admin', 'admin', 'admin')");
        } catch (SQLException e) {
            log.error(e + "");
        }
    }

}
