package database.dao;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DatabaseConnector {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_URL_2 = "jdbc:postgresql://ec2-54-228-252-67.eu-west-1.compute.amazonaws.com/daomqsvc7vpadv?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USERNAME = "postgres";
    private static final String USERNAME_2 = "yfgdtluqpgoxua";
    private static final String PASSWORD = "kiss";
    private static final String PASSWORD_2 = "00ca48ca93c00b8d1cbaabfadbe20d0c7cd3f2130889235c44fd481130d3ea27";

    private static final DatabaseConnector databaseConnector = new DatabaseConnector();
    private DatabaseMetaData databaseMetaData;
    private DatabaseConnector() {
        connect2();
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            log.error("" + e);
        }
        try {
            statement = connection.createStatement();
            ResultSet rs = databaseMetaData.getTables(null, "PUBLIC", "USERS", null);
            if(!rs.next())
            {
                statement.execute("create table users(id VARCHAR not null primary key, username varchar(32) not null, password varchar not null, email varchar(32) not null, fullname varchar(32) not null, bio VARCHAR, phoneNumber VARCHAR, followers VARCHAR, following VARCHAR )");
            }
        } catch (SQLException e) {
            log.error("" + e);
        }

        try {
            ResultSet rs = databaseMetaData.getTables(null, "PUBLIC", "POSTS", null);
            if(!rs.next())
            {
                statement.execute("create table posts(id VARCHAR not null primary key , userid varchar not null, userName varchar not null, text varchar, likers varchar, post_date timestamp)");
            }
        } catch (SQLException e) {
            log.error("" + e);
        }
        statement = null;
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

    public void connect2(){
        try{
            connection=DriverManager.getConnection(DATABASE_URL_2, USERNAME_2, PASSWORD_2);
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
