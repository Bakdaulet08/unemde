package com.example.newunemde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");


        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_FIRSTNAME + ", " + Const.USERS_LASTNAME + ", " + Const.USERS_LOGIN + ", " + Const.USERS_PASSWORD + ") VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());  // firstName
            prSt.setString(2, user.getLastName());   // lastName
            prSt.setString(3, user.getUserName());   // userName
            prSt.setString(4, user.getPassword());    // password (corrected to index 4)

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());


            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

}
