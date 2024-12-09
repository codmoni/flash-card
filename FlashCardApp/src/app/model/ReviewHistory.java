package app.model;

import java.sql.Date;

public class ReviewHistory {
	private int id;
	private int cardId;
	private Date reviewDate;
	private String feedback;
	private int reviewInterval;
	private double retentionScore;
	private int strike;
	
    public ReviewHistory(int id, int cardId, Date reviewDate, String feedback, int reviewInterval, double retentionScore, int strike) {
        this.id = id;
        this.cardId = cardId;
        this.reviewDate = reviewDate;
        this.feedback = feedback;
        this.reviewInterval = reviewInterval;
        this.retentionScore = retentionScore;
        this.strike = strike;
    }
    
    //Getters and Setters
    public int getId() {return id;}
    public int getCardId() {return cardId;}
    public Date getReviewDate() {return reviewDate;}
    public String getFeedback() {return feedback;}
    public double getRetentionScore() {return retentionScore;}
    public int getStrike() { return strike; }
    
}
