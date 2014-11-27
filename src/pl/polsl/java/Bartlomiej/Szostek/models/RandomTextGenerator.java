package pl.polsl.java.Bartlomiej.Szostek.models;

import java.util.Random;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Gives functionality to generate new random text with specific parameters."
)
public class RandomTextGenerator {
    /**
     * Maximum length of single word - this is random.
     */
    private final int maxWordLen;
    
    /**
     * Precise number of words in generated text.
     */
    private final int numOfWords;

    /**
     * Initializes instance of class with default values.
     */
    public RandomTextGenerator() {
        this.maxWordLen = 10;
        this.numOfWords = 40;
    }

    /**
     * Initializes instance of class with values given through parameters.
     * @param maxWordLength Maximum length of single word - this is random.
     * @param numberOfWords Precise number of words in generated text.
     */
    public RandomTextGenerator(int maxWordLength, int numberOfWords) {
        this.maxWordLen = maxWordLength;
        this.numOfWords = numberOfWords;
    }
    
    /**
     * Generates text with random length of words and random letters in single word.
     * @return Randomly generated text.
     */
    public String generateText() {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < numOfWords; i++) {
            textBuilder.append(getRandomWord(random, i == (numOfWords - 1)));
        }
        
        return textBuilder.toString();
    }
    
    /**
     * Generates single random word, with random capital first letter
     * and with random ending like dot, colon or question mark.
     * @param r Initialized Random class instance.
     * @param last Defines if current word is last in text.
     * @return Random word.
     */
    private String getRandomWord(Random r, boolean last) {
        char sign;
        StringBuilder word = new StringBuilder(maxWordLen + 2);
        int len = r.nextInt(maxWordLen) + 1;
        
        for (int i = 0; i < len; i++) {     
            if ((i == 0) && (r.nextInt(100) > 20)) {
                sign = (char) (r.nextInt(26) + 'A');
            }
            else {
                sign = (char) (r.nextInt(26) + 'a');
            }
            word.append(sign);
        }
        
        sign = (char) r.nextInt(100);
        if (last == true) {
            word.append('.');
        }
        else if (sign < 10) {
            word.append("! ");
        }
        else if (sign < 20) {
            word.append("? ");
        }
        else if (sign < 40) {
            word.append(". ");
        }
        else if (sign < 60) {
            word.append(", ");
        }
        else {
            word.append(' ');
        }
        
        return word.toString();
    }
}
