package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.UserXmlDB;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.0,
        description = "Contains parameters for game controller."
)
public class GameParameters {
    
    /**
     * Current user name.
     */
    private String userName;
    
    /**
     * Maximum length of single word in current game.
     */
    private int maxWordLength;
    
    /**
     * Number of words in current game.
     */
    private int numberOfWords;

    /** Default contructor */
    public GameParameters() {
    }

    /**
     * Initializes game parameters with given values.
     * @param nick User nickname.
     * @param maxWordLength Max single word length.
     * @param numberOfWords Number of words in game.
     * @throws InvalidUserNameException
     */
    public GameParameters(String nick, int maxWordLength, int numberOfWords) 
            throws InvalidUserNameException {
        UserXmlDB manager = UserXmlDB.getInstance();
        if(manager.validateUserName(nick)) {
            this.userName = nick;
            manager.addUser(nick);
        }
        else {
            this.userName = "NoName";
        }
        this.maxWordLength = maxWordLength;
        this.numberOfWords = numberOfWords;
    }
    
    /**
     * Get current user name.
     * @return User name.
     */
    public final String getUserName() {
        return this.userName;
    }
    
    /**
     * Sets new user name.
     * @param newUserName User name.
     */
    public final void setUserName(String newUserName) {
        this.userName = newUserName;
    }
    
    /**
     * Returns max word length;
     * @return Max word length.
     */
    public final int getMaxWordLength() {
        return this.maxWordLength;
    }
    
    /**
     * Sets new max word length;
     * @param newMaxLength Max word length.
     */
    public final void setMaxWordLength(int newMaxLength) {
        this.maxWordLength = newMaxLength;
    }    
    
    /**
     * Return number of words.
     * @return Number of words.
     */
    public final int getNumberOfWords() {
        return this.numberOfWords;
    }
        
    /**
     * Sets new number of words.
     * @param newNumberOfWords Number of words.
     */
    public final void setNumberOfWords(int newNumberOfWords) {
        this.numberOfWords = newNumberOfWords;
    }
}
