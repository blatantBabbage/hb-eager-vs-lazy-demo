package com.nitesh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";
        try {
            System.out.println("jdbc URL : " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection Successful!!!!");

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
