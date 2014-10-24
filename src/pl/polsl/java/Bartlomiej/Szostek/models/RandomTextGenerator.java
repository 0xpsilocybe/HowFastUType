package pl.polsl.java.Bartlomiej.Szostek.models;

import java.lang.StringBuilder;
import java.util.Random;

/**
 *
 * @author Bartek
 */
public class RandomText {
    private final int maxWordLen;
    private final int numOfWords;

    public RandomText() {
        this.maxWordLen = 10;
        this.numOfWords = 40;
    }

    public RandomText(int maxWordLength, int numberOfWords) {
        this.maxWordLen = maxWordLength;
        this.numOfWords = numberOfWords;
    }
    
    public String GenerateText() {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < numOfWords; i++) {
            textBuilder.append(getRandomWord(random, i == (numOfWords - 1)));
        }
        
        return textBuilder.toString();
    }
    
    private String getRandomWord(Random r, boolean last) {
        char sign;
        StringBuilder word = new StringBuilder(maxWordLen + 2);
        int len = r.nextInt(maxWordLen) + 1;
        
        for (int i = 0; i < len; i++) {     
            if ((i == 0) && (r.nextInt(100) > 80)) {
                sign = (char) (r.nextInt(26) + 'a');
            }
            else {
                sign = (char) (r.nextInt(26) + 'A');
            }
            word.append(sign);
        }
        
        sign = (char) r.nextInt(100);
        if (last == true) {
            word.append('.');
        }
        else if (sign > 10) {
            word.append("! ");
        }
        else if (sign > 20) {
            word.append("? ");
        }
        else if (sign > 40) {
            word.append(". ");
        }
        else if (sign > 50) {
            word.append(", ");
        }
        else {
            word.append(' ');
        }
        
        return word.toString();
    }
}
