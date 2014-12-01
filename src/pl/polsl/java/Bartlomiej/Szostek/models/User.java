package pl.polsl.java.Bartlomiej.Szostek.models;

import java.util.ArrayList;
import java.util.List;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;


@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "23/11/14",
        lastModifiedDate = "23/11/14",
        version = 1.0,
        description = "This model represents user and his highscores table."
)
public class User {
    /**
     * String representation of user nickname.
     */
    private String userName;

    /**
     * List of scores of current user achieved in casual game mode.
     */
    private List<Score> casualScoreList = new ArrayList<Score>();
    
    /**
     * List of scores of current user achieved in marathon game mode.
     */
    private List<Score> marathonScoreList = new ArrayList<Score>();
    
    /**
     * List of scores of current user achieved in reaction game mode.
     */
    private List<Score> reactionScoreList = new ArrayList<Score>();
    
    /**
     * Creates user instatnion.
     * @param userName User nickname.
     */
    public User(String userName) {
        this.userName = userName;
    }
    
    /**
     * Get user name.
     * @return User name.
     */
    public final String getUserName() {
        return this.userName;
    }
      
    /**
     * Sets user name.
     * @param newUserName New user name.
     */
    public final void setUserName(String newUserName) {
        this.userName = newUserName;
    }    
    
    /**
     * Get scores from Casual game mode.
     * @return Casual game mode scores.
     */
    public final List<Score> getCasualScores() {
        return this.casualScoreList;
    }
    
    /**
     * Set Casual score list.
     * @param scores Score list.
     */
    public final void setCasualScores(List<Score> scores) {
        this.casualScoreList = scores;
    }
    
    /**
     * Get scores from Marathon game mode.
     * @return Casual game mode scores.
     */
    public final List<Score> getMarathonScores() {
        return this.marathonScoreList;
    }
        
    /**
     * Set Marathon score list.
     * @param scores Score list.
     */
    public final void setMarathonScores(List<Score> scores) {
        this.marathonScoreList = scores;
    }
    
    /**
     * Get scores from Reaction game mode.
     * @return Casual game mode scores.
     */
    public final List<Score> getReactionScores() {
        return this.reactionScoreList;
    }
    
    /**
     * Set Reaction score list.
     * @param scores Score list.
     */
    public final void setReactionScores(List<Score> scores) {
        this.reactionScoreList = scores;
    }
}
