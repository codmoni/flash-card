package app.model;

import java.sql.Timestamp;

public class Card {
	private int id;
	private int deckId;
	private String question;
	private String answer;
	private String additionalInfo;
	private Timestamp createdAt;
	
    public Card(int id, int deckId, String question, String answer, String additionalInfo, Timestamp createdAt) {
        this.id = id;
        this.deckId = deckId;
        this.question = question;
        this.answer = answer;
        this.additionalInfo = additionalInfo;
        this.createdAt = createdAt;
    }
    
    //Getters and Setters
    public int getId() {return id;}
    public int getDeckId() {return deckId;}
    public String getQuestion() {return question;}
    public String getAnswer() {return answer;}
    public String getAdditionalInfo() {return additionalInfo;}
    public Timestamp getCreatedAt() {return createdAt;}
}
