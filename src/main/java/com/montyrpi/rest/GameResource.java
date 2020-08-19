package com.montyrpi.rest;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.montyrpi.model.SessionData;
import com.montyrpi.model.User;
import com.montyrpi.services.MySQLAccess;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("game")
public class GameResource {
	
	@Context HttpServletRequest servletRequest;
	
	@GET
	@Path("start")
	public SessionData gameStart() {
		System.out.println("gameStart called..");
		MySQLAccess access = new MySQLAccess();
		HttpSession mySession = servletRequest.getSession();
		String session_id = mySession.getId();
		SessionData session = access.getSessionData(session_id);
		
		return session;
	}
	
	@GET
	@Path("end")
	public SessionData gameEnd() {
		System.out.println("gameEnd called..");
		MySQLAccess access = new MySQLAccess();
		HttpSession mySession = servletRequest.getSession();
		String session_id = mySession.getId();
		SessionData session = access.getSessionData(session_id);

		return session;
	}

}
