package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.GameParameters;
import pl.polsl.java.Bartlomiej.Szostek.models.RandomTextGenerator;
import pl.polsl.java.Bartlomiej.Szostek.models.TextModel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Provides control for game flow."
)
public class GameController extends ControllerBase {
    
    /**
     * Text model for current game.
     */
    private TextModel textForThisGame = null;
    
    /**
     * Current game parameters.
     */
    private GameParameters gameParameters = new GameParameters();
    
    /**
     * Current game mode.
     */
    private GameMode gameMode;
    
    /** Initializes game variables */
    private void Initialize() {    
        switch(gameMode) {
            case CASUAL:
                RandomTextGenerator randomTextGenenator = 
                        new RandomTextGenerator(gameParameters.getMaxWordLength(),
                        gameParameters.getNumberOfWords());
                this.textForThisGame = new TextModel(randomTextGenenator.generateText());
                break;
            case MARATHON:
            case REACTION:
            case CASUAL_MULTI:
            case MARATHON_MULTI:
            case REACTION_MULTI:
        }        
    }

    /**
     * Begins the main loop for current game.
     * Changes the view and get user input.
     * 
     * @throws IOException
     */
    public void begin() throws IOException {
        Initialize();
        StringBuilder inputText = new StringBuilder();
        long timeStart = 0;
        long timeElapsed = 0;
        double accuracy = 0.0;
        
        timeStart = System.nanoTime();
        
        timeElapsed = (System.nanoTime() - timeStart) / 1000000;
        
        textForThisGame.validateString(inputText.toString());
        
        accuracy = 100 - ((textForThisGame.getNumberOfMistakes() * 100) 
                 / textForThisGame.getTextLength());
        
        UserXmlDB manager = UserXmlDB.getInstance();
        manager.addScore(gameParameters.getUserName(), 
                         gameMode, 
                         new Score( (int) timeElapsed, accuracy));
    }

    
    /**
     * Sets user name.
     * @param nick User name.
     */
    public void setUserName(String nick) {
        this.gameParameters.setUserName(nick);
    }
    
    /**
     * Sets max word length.
     * @param wordLength Max word length.
     */
    public void setMaxWordLength(int wordLength) {
        this.gameParameters.setMaxWordLength(wordLength);
    }
    
    /**
     * Sets words count.
     * @param wordCount Words count.
     */
    public void setWordsCount(int wordCount) {
        this.gameParameters.setNumberOfWords(wordCount);
    }
    
    @Override
    public void getControl(ControllerBase controller) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public String[] getUsersList() {
        UserXmlDB manager = UserXmlDB.getInstance();
        List<String> list = manager.getUserNames();
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        return arr;
    }

    public GameMode[] getGameModes() {
        return GameMode.values();
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
