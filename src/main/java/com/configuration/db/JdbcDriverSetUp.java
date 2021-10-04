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
            Assertions.assertEquals("test_user1\n"
                                    + "test_user2\n"
                                    + "test_user3", "test_user1\n"
                                                    + "test_user2\n"
                                                    + "test_user3", "Select is not match");

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
            Assertions.assertEquals("test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t1\talbert\teinstein\n"
                                    + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t1\talbert\teinstein\n"
                                    + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t1\talbert\teinstein\n"
                                    + "test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t2\tisaac\tnewton\n"
                                    + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t2\tisaac\tnewton\n"
                                    + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t2\tisaac\tnewton\n"
                                    + "test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t3\tmarie\tcurie\n"
                                    + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t3\tmarie\tcurie\n"
                                    + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t3\tmarie\tcurie", "test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t1\talbert\teinstein\n"
                                                                                                                       + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t1\talbert\teinstein\n"
                                                                                                                       + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t1\talbert\teinstein\n"
                                                                                                                       + "test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t2\tisaac\tnewton\n"
                                                                                                                       + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t2\tisaac\tnewton\n"
                                                                                                                       + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t2\tisaac\tnewton\n"
                                                                                                                       + "test_user3\ttest_user3@mail.com\t12345678\t2021-09-08 21:07:36\t3\tmarie\tcurie\n"
                                                                                                                       + "test_user2\ttest_user2@mail.com\t1234567\t2021-09-08 21:07:19\t3\tmarie\tcurie\n"
                                                                                                                       + "test_user1\ttest_user1@mail.com\t123456\t2021-09-08 21:06:51\t3\tmarie\tcurie", "Select is not match");
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
