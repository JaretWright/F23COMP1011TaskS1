package com.example.f23comp1011tasks1;

import java.sql.*;

public class DBUtility {
    private static String dbUser = "student";
    private static String password = "student";

    /**
     * jdbc:mysql -> the database driver
     * 127.0.0.1 -> IP address of the database server
     * 3306 -> port number of the database server
     * COMP1011Friday -> name of the database
     */
    private static String connectURL = "jdbc:mysql://127.0.0.1:3306/COMP1011Friday";

    /**
     * This method will save a user to the database
     */
    public static String saveUserToDB(User user) throws SQLException {
        String responseMsg = "broken";

        String sql = "INSERT INTO users VALUES (?,?)";

        //try () ... is called try with resources
        //anything inside the () will be automatically closed
        try(
                Connection conn = DriverManager.getConnection(connectURL, dbUser, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getUserName());

            ps.executeUpdate();

            responseMsg = "User Added";
        }
        //if the email address was already in the DB, a SQL Integrity Exception is thrown
        //and the new user would not be added
        catch(SQLIntegrityConstraintViolationException e)
        {
            responseMsg = "User already defined in DB";
        }
        catch(Exception e)
        {
            responseMsg = e.getMessage();
        }

        return responseMsg;
    }
}
