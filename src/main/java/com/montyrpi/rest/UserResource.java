package com.montyrpi.rest;

import java.util.List;

import com.montyrpi.model.User;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("users")
public class UserResource {
	
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
}
