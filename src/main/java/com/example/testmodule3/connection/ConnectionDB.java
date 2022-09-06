package com.example.testmodule3.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConnectionDB {
        private static final String url ="jdbc:mysql://localhost:3306/testcase";
        private static final String user ="root";
        private static final String password ="123456";

        public static Connection getConnection(){
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,user,password);
                System.out.println("Successful");
            } catch (SQLException e) {
                System.out.println("Fail");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }
//        public static void main(String[] args) {
//            ConnectionDB connectionDB = new ConnectionDB();
//            connectionDB.getConnection();
//        }
    }
