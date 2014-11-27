    package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.lang.annotation.*;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.GameParameters;
import pl.polsl.java.Bartlomiej.Szostek.models.RandomTextGenerator;
import pl.polsl.java.Bartlomiej.Szostek.models.TextModel;
import pl.polsl.java.Bartlomiej.Szostek.views.GameView;
import pl.polsl.java.Bartlomiej.Szostek.models.NotSupportedGameModeException;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Provides control for game flow."
)
public class GameController {
    
    /**
     * Text model for current game.
     */
    private TextModel textForThisGame = null;
    
    /**
     * View providing UI during game.
     */
    private GameView view  = null;
    
    /**
     * Current game parameters.
     */
    private final GameParameters gameParameters;
    
    /**
     * Current game mode.
     */
    private final GameMode gameMode;
    
    /**
     * Initializes instance of class with text for current game.
     * @param gameMode Determinates game mode choosen by user.
     * @param parameters Parameters of current game.
     * @throws NotSupportedGameModeException
     */ 
    public GameController(GameMode gameMode, GameParameters parameters) 
            throws NotSupportedGameModeException {
        view = new GameView();
        this.gameParameters = parameters;
        this.gameMode = gameMode;
        
        switch(gameMode) {
            case CASUAL:
                RandomTextGenerator randomTextGenenator = 
                        new RandomTextGenerator(parameters.getMaxWordLength(), parameters.getNumberOfWords());
                this.textForThisGame = new TextModel(randomTextGenenator.generateText());
                break;
            case MARATHON:
            case REACTION:
            case CASUAL_MULTI:
            case MARATHON_MULTI:
            case REACTION_MULTI:
            default:
                throw new NotSupportedGameModeException("This game mode is not yet supported!");
        }        
    }

    /**
     * Begins the main loop for current game.
     * Changes the view and get user input.
     * 
     * @throws IOException
     */
    public void begin() throws IOException {
        Scanner scanUserInput = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        long timeStart = 0;
        long timeElapsed = 0;
        double accuracy = 0.0;
        
        view.displayText(textForThisGame.getText());
        
        timeStart = System.nanoTime();
        while(inputText.length() < textForThisGame.getTextLength()) {
            inputText.append(scanUserInput.nextLine());
        }
        timeElapsed = (System.nanoTime() - timeStart) / 1000000;
        
        textForThisGame.validateString(inputText.toString());
        
        accuracy = 100 - ((textForThisGame.getNumberOfMistakes() * 100) 
                 / textForThisGame.getTextLength());
        view.displayResult(textForThisGame.getNumberOfMistakes(),
                               accuracy,
                               timeElapsed);
        UserXmlDB manager = UserXmlDB.getInstance();
        manager.addScore(gameParameters.getUserName(), 
                         gameMode, 
                         new Score( (int) timeElapsed, accuracy));
        System.in.read();
    }
}
