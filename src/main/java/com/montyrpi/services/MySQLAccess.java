package com.montyrpi.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.montyrpi.model.SessionData;
import com.montyrpi.model.User;

public class MySQLAccess {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    private Connection getConnection() { 	
        try {
        	// This will load the MySQL driver, each DB has its own driver
        	Class.forName("com.mysql.jdbc.Driver");
        	// Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/montyhall?"
                            + "user=root&password=12345");
        }
        catch (Exception e) {
        	
        }
        return connect;
    }
	
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
            connect = this.getConnection();

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("SELECT * FROM `montyhall`.`users` where id=?");
            preparedStatement.setInt(1, userid);
            // Result set get the result of the SQL query
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getString(2);
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
        	connect = this.getConnection();

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
        	connect = this.getConnection();

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
	
	public SessionData getSessionData(String session_id) {
		SessionData sessionData = new SessionData();
        try {
        	connect = this.getConnection();

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("SELECT * FROM `montyhall`.`session_data` where session_id=?");
            preparedStatement.setString(1, session_id);
            // Result set get the result of the SQL query
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getString(2);
                sessionData.session_id = resultSet.getString("session_id");
                sessionData.sandboxAvailable = resultSet.getBoolean("sandboxAvailable");
                sessionData.mode = resultSet.getString("mode");
                sessionData.doors = resultSet.getInt("doors");
                sessionData.winning_door = resultSet.getInt("winning_door");
                sessionData.door_1 = resultSet.getInt("door_1");
                sessionData.m_door = resultSet.getInt("m_door");
                sessionData.door_1 = resultSet.getInt("door_2");
            }

        } catch (Exception e) {
        	
        } finally {
            close();
        }
        return sessionData;
    }
	
	public void createSessionData(String session_id, boolean sandboxAvailable) {
        try {
        	connect = this.getConnection();

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("INSERT INTO `montyhall`.`session_data` (`session_id`, `sandboxAvailable`) VALUES (?, ?)");
            preparedStatement.setString(1, session_id);
            preparedStatement.setBoolean(2, sandboxAvailable);
            // Result set get the result of the SQL query
            preparedStatement.executeUpdate();
        } catch (Exception e) {

        } finally {
            close();
        }
    }
	
	public void updateSessionData(SessionData session) {
		 try {
	        	connect = this.getConnection();

	            // Statements allow to issue SQL queries to the database
	            preparedStatement = connect.prepareStatement("UPDATE `montyhall`.`session_data` " +
	            		"SET " +
	            		"`sandboxAvailable` = ?, " +
	            		"`monty_type` = ?, " +
	            		"`winning_type` = ?, " +
	            		"`losing_type` = ?, " +
	            		"`doors` = ?, " +
	            		"`winning_door` = ?, " +
	            		"`door_1` = ?, " +
	            		"`m_door` = ?, " +
	            		"`door_2` = ? " +
	            		"WHERE `session_id` = ?");
	            preparedStatement.setBoolean(1, session.sandboxAvailable);
	            preparedStatement.setString(2, session.mode);
	            preparedStatement.setString(3, session.win_mode);
	            preparedStatement.setString(4, session.lose_mode);
	            preparedStatement.setInt(5, session.doors);
	            preparedStatement.setInt(6, session.winning_door);
	            preparedStatement.setInt(7, session.door_1);
	            preparedStatement.setInt(8, session.m_door);
	            preparedStatement.setInt(9, session.door_2);
	            preparedStatement.setString(10, session.session_id);
	            // Result set get the result of the SQL query
	            preparedStatement.executeUpdate();
	            
	        } catch (Exception e) {
	        	System.out.println("updateSessionData error.." + e.getMessage());
	        } finally {
	            close();
	        }
	}
	
}
