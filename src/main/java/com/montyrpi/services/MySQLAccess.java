package com.montyrpi.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.montyrpi.model.User;

public class MySQLAccess {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
	public User getUser(int userid) throws Exception {
		User user = new User();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/montyhall?"
                            + "user=root&password=12345");

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("SELECT * FROM `montyhall`.`users` where id=?");
            preparedStatement.setInt(1, userid);
            // Result set get the result of the SQL query
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
                String username = resultSet.getString("username");
                int id = resultSet.getInt("id");
                int total_wins = resultSet.getInt("total_wins");
                int total_loss = resultSet.getInt("total_loss");
                
                user.setId(id);
                user.setName(username);
                user.setTotal_wins(total_wins);
                user.setTotal_loss(total_loss);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return user;
    }

	public List<User> getUsers() throws Exception {
		List<User> users = new ArrayList<User>();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/montyhall?"
                            + "user=root&password=12345");

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("SELECT * FROM `montyhall`.`users`");
            // Result set get the result of the SQL query
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
            	User user = new User();
                String username = resultSet.getString("username");
                int id = resultSet.getInt("id");
                int total_wins = resultSet.getInt("total_wins");
                int total_loss = resultSet.getInt("total_loss");
                
                user.setId(id);
                user.setName(username);
                user.setTotal_wins(total_wins);
                user.setTotal_loss(total_loss);
                users.add(user);
               
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
		
		return users;
	}
	
	public User createUser(User user) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/montyhall?"
                            + "user=root&password=12345");

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("INSERT INTO `montyhall`.`users` (`username`, `total_wins`, `total_loss`) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getTotal_wins());
            preparedStatement.setInt(3, user.getTotal_loss());
            // Result set get the result of the SQL query
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
            	user.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return user;
    }
	
}
