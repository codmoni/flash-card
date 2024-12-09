package app.model;

import java.sql.Timestamp;

public class Deck {
	private int id;
	private int userId;
	private String name;
	private Timestamp createdAt;
	
	public Deck(int id, int userId, String name, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
	}
	
	//Getters and Setters
	public int getId() {return id;}
	public int getUserId() {return userId;}
	public String getName() {return name;}
	public Timestamp getCreatedAt() {return createdAt;}
}
