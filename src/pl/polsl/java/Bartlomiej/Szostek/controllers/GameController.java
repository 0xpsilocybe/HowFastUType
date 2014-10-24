package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.TextModel;
import pl.polsl.java.Bartlomiej.Szostek.views.InGameView;

/**
 * Provides control for game flow.
 * @author Bartek
 */
public class GameController {
    
    /**
     * Text model for current game.
     */
    private TextModel textForThisGame = null;
    
    /**
     * View providing UI during game.
     */
    private InGameView gameView  = null;
    
    /**
     * Initializes instance of class with text for current game.
     * @param text Text for current game.
     */
    GameController(String text) {
        this.textForThisGame = new TextModel(text);
        gameView = new InGameView();
    }

    /**
     * Begins the main loop for current game.
     */
    void begin() {
        Scanner scanUserInput = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        char singleCharUserInput;
        gameView.displayText(textForThisGame.getText());
        
        while(inputText.length() <= textForThisGame.getTextLength()) {
            inputText.append(scanUserInput.next());
        }
        
        textForThisGame.validateString(inputText.toString());
        
        gameView.displayResult(textForThisGame.getNumberOfMistakes(),
                               textForThisGame.getTextLength());
    }
}
