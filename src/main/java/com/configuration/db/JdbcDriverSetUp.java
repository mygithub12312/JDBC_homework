package com.configuration.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDriverSetUp {

    String DBURL = "jdbc:mysql://localhost:3306/homework";
    String USERNAME = "root";
    String PASSWORD = "password123";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Before
    public void dbDriverSetUp() throws SQLException {
        try {
            connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectFromDB() {
        try {
            String query = "SELECT * FROM USER";
            resultSet = statement.executeQuery(query);
            Assertions.assertTrue(resultSet.getRow() > 0, "Table_name does not contain any records");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectFromDataBaseWithJoin() {
        try {
            String query = "SELECT * FROM user INNER JOIN scientist;";
            resultSet = statement.executeQuery(query);
            Assertions.assertTrue(resultSet.getRow() > 0, "Table_name does not contain any records");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
