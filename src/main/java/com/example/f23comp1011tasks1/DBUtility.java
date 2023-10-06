package com.example.f23comp1011tasks1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    /**
     * This method receives a task object and saves it to the DB
     * @param task
     * @return
     * @throws SQLException
     */
    public static String saveTaskToDB(Task task) throws SQLException {
        String responseMsg = "broken";
        ResultSet resultSet = null;

        String sql = "INSERT INTO tasks (title, description, creationDate, dueDate, severity, status, email) VALUES " +
                     "  (?,?,?,?,?,?,?);";

        //try () ... is called try with resources
        //anything inside the () will be automatically closed
        try(
                Connection conn = DriverManager.getConnection(connectURL, dbUser, password);
                PreparedStatement ps = conn.prepareStatement(sql, new String[] {"taskID"});
        )
        {
            ps.setString(1,task.getTitle());
            ps.setString(2,task.getDescription());
            ps.setDate(3, Date.valueOf(task.getCreationDate()));
            ps.setDate(4, Date.valueOf(task.getDueDate()));
            ps.setInt(5,task.getSeverity());
            ps.setString(6,task.getStatus().toString());
            ps.setString(7,task.getUser().getEmail());

            ps.executeUpdate();

            int taskID = -1;

            //get the new taskID assigned by the DB
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next())
                taskID = resultSet.getInt(1);

            responseMsg = "Task " +taskID + " Added";
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

    /**
     * This method will return a list of users from the database
     */
    public static ArrayList<User> getUsersFromDB(){
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        //connect to the database - we use a try...with resources block to ensure
        //the connection, statement and resultSet are automatically closed
        try(
                Connection conn = DriverManager.getConnection(connectURL,dbUser, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while (resultSet.next())
            {
                String email = resultSet.getString("email");
                String userName = resultSet.getString("userName");
                users.add(new User(email, userName));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return users;
    }

    public static ArrayList<Task> getTasksFromDB(){
        ArrayList<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM tasks";

        //connect to the database - we use a try...with resources block to ensure
        //the connection, statement and resultSet are automatically closed
        try(
                Connection conn = DriverManager.getConnection(connectURL,dbUser, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                int taskID = resultSet.getInt("taskID");
                String title = resultSet.getString("title");

                Task newTask = new Task(taskID, title, description, creationDate, dueDate, severity, status, user);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tasks;
    }
}
