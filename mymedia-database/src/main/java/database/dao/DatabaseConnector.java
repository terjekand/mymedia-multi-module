package database.dao;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DatabaseConnector {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "kiss";

    private static final DatabaseConnector databaseConnector = new DatabaseConnector();

    private DatabaseConnector() {
        connect();
    }

    public static final DatabaseConnector getInstance(){
        return databaseConnector;
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void connect(){
        try{
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



}
