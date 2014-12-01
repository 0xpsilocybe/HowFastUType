package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = 
                "This model represents user input text during current game." +
                "It holds current index of the text, number of mistakes, etc."
)
public class TextModel {
     
    /**
     * Holds position of index in game text.
     */
    private int currentIndex = 0;
    
    /**
     * Represents text, that user will have to retype in current game.
     */
    private final String gameText;

    /**
     * Holds number of mistakes made by user
     */
    private int numberOfMistakes = 0;
    
    /**
     * Getter for gameText.
     * @return gameText
     */
    public String getText() {
        return gameText;
    }
    
    /**
     * Getter for numberOfMistakes.
     * @return numberOfMistakes
     */
    public int getNumberOfMistakes() {
        return numberOfMistakes;
    }
    
    /**
     * Gets length of current game text.
     * @return Current game text length.
     */
    public int getTextLength() {
        return gameText.length();
    }
    
    /**
     * Tells if we are at the end of curren game text.
     * @return False if there is still some text to play, true in opposite.
     */
    public boolean atTheEndOfText() {
        return (this.currentIndex == (this.getTextLength() - 1));
    }
    
    /**
     * Validates string according to the current game text.
     * @param text String to be validated.
     */
    public void validateString(String text) {
        for (int i = 0; (i < this.getTextLength()) && (i < text.length()); i++) {
            this.checkCharacter(text.charAt(i));
        }
        if(this.getTextLength() > text.length()) {
            numberOfMistakes += (this.getTextLength() - text.length());
        }
    }
    
    /**
     * Compares given character with character at current position in game text.
     * @param character Character to be compared with current position in text.
     * @return True if character are the same, false in opposite.
     */
    private boolean checkCharacter(char character) {
        boolean compare = ((character == (gameText.charAt(currentIndex))) ? true : false);
        if(compare == false) {
            ++numberOfMistakes;
        }
        
        ++currentIndex;
        
        return compare;
    }
     
    /**
     * Initiates model with current game text.
     * @param text Text for current game.
     */
    public TextModel(String text) {
        this.gameText = text;
    }
}
