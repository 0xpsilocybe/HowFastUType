package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.TextModel;
import pl.polsl.java.Bartlomiej.Szostek.views.InGameView;

/**
 * Provides control for game flow.
 * @author Bartłomiej Szostek
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
     * Changes the view and get user input.
     * 
     * @throws IOException
     */
    void begin() throws IOException {
        Scanner scanUserInput = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        long timeStart = 0;
        long timeElapsed = 0;
        
        gameView.displayText(textForThisGame.getText());
        
        timeStart = System.nanoTime();
        while(inputText.length() < textForThisGame.getTextLength()) {
            inputText.append(scanUserInput.nextLine());
        }
        timeElapsed = (System.nanoTime() - timeStart) / 1000000;
        
        textForThisGame.validateString(inputText.toString());
        
        gameView.displayResult(textForThisGame.getNumberOfMistakes(),
                               textForThisGame.getTextLength(),
                               timeElapsed);
        System.in.read();
    }
}
