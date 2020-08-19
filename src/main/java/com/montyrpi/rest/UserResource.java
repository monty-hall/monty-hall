package com.montyrpi.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.montyrpi.model.BackResponse;
import com.montyrpi.model.FrontCall;
import com.montyrpi.model.SessionData;
import com.montyrpi.model.User;
import com.montyrpi.programs.Monty;
import com.montyrpi.programs.MontyHall;
import com.montyrpi.services.MySQLAccess;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("users")
public class UserResource {
	
	@Context HttpServletRequest servletRequest; 
	
	UserRepository repo = new UserRepository();
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		System.out.println("getUsers called..");
		return repo.getUsers();
	}
	
	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int id) {
		System.out.println("getUser called..");
		return repo.getUser(id);
	}
	
	@POST
	@Path("newuser")
	@Produces(MediaType.APPLICATION_JSON)
	public User newUser(User a1) {
		System.out.println(a1);
		return repo.create(a1);
	}

	@GET
	@Path("new_session")
	public String newSession() {
		System.out.println("newSession called..");
		MySQLAccess access = new MySQLAccess();
		HttpSession mySession = servletRequest.getSession();
		String session_id = mySession.getId();
		access.createSessionData(session_id, false);
		return session_id;
	}
	
	@GET
	@Path("get_session")
	@Produces(MediaType.APPLICATION_JSON)
	public SessionData getSession() {
		System.out.println("getSession called..");
		MySQLAccess access = new MySQLAccess();
		HttpSession mySession = servletRequest.getSession();
		String session_id = mySession.getId();
		return access.getSessionData(session_id);
	}
	
	@POST
	@Path("update_session")
	public BackResponse updateSession(FrontCall call) {
		System.out.println("updateSession called..");
		MySQLAccess access = new MySQLAccess();
		HttpSession mySession = servletRequest.getSession();
		String session_id = mySession.getId();
		BackResponse response = new BackResponse();
		if (call.command.contentEquals("start")) {
			System.out.println("starting game...");
			// generate session data by calling Monty classes
			Monty myMonty = MontyHall.getMonty(call.doors, call.door_1, call.prize, call.mode);
			int winning_door = 1; // REPLACE THIS
			int m_door = 2; // REPLACE THIS
			// make a session object
			SessionData sessionData = new SessionData();
			sessionData.session_id = session_id;
			sessionData.mode = call.mode;
			sessionData.doors = call.doors;	
			sessionData.winning_door = winning_door;
			sessionData.m_door = m_door;
			sessionData.door_1 = call.door_1;
			// update entry in table
			access.updateSessionData(sessionData);
			// return backresponse
			response.session_id = session_id;
			response.mode = call.mode;
			response.winning_door = winning_door;
			response.m_door = m_door;
		}
		else if (call.command.contentEquals("end")) {
			System.out.println("ending game...");
			// further generate session data by calling Monty classes
			// make a session object with updated info
			SessionData sessionData = access.getSessionData(call.session_id);
			sessionData.door_2 = call.door_2;
			// update entry in table
			access.updateSessionData(sessionData);
			// return backresponse
			response.session_id = session_id; // if in end mode, just return session_id
		}
		return response;
	}
}
