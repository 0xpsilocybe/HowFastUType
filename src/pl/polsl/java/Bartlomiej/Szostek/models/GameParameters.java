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
    private final String userName;
    
    /**
     * Maximum length of single word in current game.
     */
    private final int maxWordLength;
    
    /**
     * Number of words in current game.
     */
    private final int numberOfWords;

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
     * Get curren user name.
     * @return User name.
     */
    public final String getUserName() {
        return this.userName;
    }
    
    /**
     * Returns max word length;
     * @return Max word length.
     */
    public final int getMaxWordLength() {
        return this.maxWordLength;
    }
    
    /**
     * Return number of words.
     * @return Number of words.
     */
    public final int getNumberOfWords() {
        return this.numberOfWords;
    }
}
