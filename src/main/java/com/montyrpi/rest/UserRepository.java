package com.montyrpi.rest;

import java.util.ArrayList;
import java.util.List;

import com.montyrpi.model.User;
import com.montyrpi.services.MySQLAccess;

public class UserRepository {
	List<User> users;
	
	public UserRepository() {
		MySQLAccess access = new MySQLAccess();
	}
	
	public List<User> getUsers() {
		MySQLAccess access = new MySQLAccess();
		try {
			return access.getUsers();
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
        }
		return null;
	}
	
	public User getUser(int id) {
		MySQLAccess access = new MySQLAccess();
		try {
			return access.getUser(id);
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
        }
		return null;
	}

	public User create(User a1) {
		MySQLAccess access = new MySQLAccess();
		try {
			return access.createUser(a1);
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
        }
		return null;
	}
}
