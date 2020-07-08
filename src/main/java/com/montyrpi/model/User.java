package com.montyrpi.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String name;
	private int id;
	private int total_wins;
	private int total_loss;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_wins() {
		return total_wins;
	}
	public void setTotal_wins(int total_wins) {
		this.total_wins = total_wins;
	}
	public int getTotal_loss() {
		return total_loss;
	}
	public void setTotal_loss(int total_loss) {
		this.total_loss = total_loss;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", total_wins=" + total_wins + ", total_loss=" + total_loss + ", username=" + name + "]";
	}
	
}
