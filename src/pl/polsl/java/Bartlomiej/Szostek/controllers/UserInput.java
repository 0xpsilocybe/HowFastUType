package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.GameParameters;
import pl.polsl.java.Bartlomiej.Szostek.models.NotSupportedGameModeException;
import pl.polsl.java.Bartlomiej.Szostek.views.MainView;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.InvalidUserNameException;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description =
                "Controller for the main window of the game. Controlls main menu and is" +
                "responsible for changing main views. After game starts, the controll is" +
                "passed to the GameController, after game the control is passed back."
)
public class UserInput {
    
    /**
     * View of main menu.
     */
    private final MainView view;
    
    /**
     * Current game mode.
     */
    private GameMode gameMode = GameMode.CASUAL;
    
    /**
     * Current game parameters.
     */
    private GameParameters gameParameters;
    
    /**
     * Initializes class instance, shows standard application UI 
     * and forwards parameters to parser.
     * 
     * @param args Arguments taken from user.
     */
    public UserInput(String args[]) {
        view = new MainView();
        view.displayHeader();
        try {
            parseParameters(args);
        } catch (ArrayIndexOutOfBoundsException ex) {
            view.displayException("ArrayIndexOutOfBoundsException", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            view.displayException("IllegalArgumentException", ex.getMessage());
        } catch (InvalidUserNameException ex) {
            view.displayException("InvalidUserNameException", ex.getMessage());
        }
    }
    
    /**
     * Takes given arguments and parses them to 
     * @see numberOfWords 
     * and
     * @see maxWordLength
     * 
     * @param args Arguments received to parse.
     * @throws ArrayIndexOutOfBoundsException
     * @throws IllegalArgumentException
     * @throws NumberFormatException 
     */
    private void parseParameters(String[] args)
            throws ArrayIndexOutOfBoundsException, 
                   IllegalArgumentException, NumberFormatException, InvalidUserNameException
    {
        try {
            gameParameters = new GameParameters(
                    args[0],
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2])
            );
        } 
        catch (ArrayIndexOutOfBoundsException ex1) {
            view.displayException("ArrayIndexOutOfBoundsException",
                                       ex1.getMessage(), 
                                       "Too few arguments. See help.");
            throw ex1;
        }
        catch (NumberFormatException ex2) {
            view.displayException("NumberFormatException",
                                      ex2.getMessage(),
                                      "Parameters should be integers.");
            throw ex2;
        } 
        catch (IllegalArgumentException ex3) {
            view.displayException("IllegalArgumentException",
                                      ex3.getMessage());
            throw ex3;
        }
    }
    
    /**
     * Starts application menu. Retrives user option 
     * and passes it to corresponding view or controller.
     */
    public void start() {
        Scanner scanUserInput = new Scanner(System.in);
        String regexOptions = "[1-4]";
        String regexGameModes = getRegexGameModesNumber(); 
        int userOption = 0;
        
        do {
            view.displayMenu();
            try {
                while(!scanUserInput.hasNext(regexOptions)) {
                    view.displayBadOptionWarning(regexOptions);
                    scanUserInput.nextLine();
                }
                
                userOption = scanUserInput.nextInt();
                switch (userOption) {
                    case 1:
                        view.displayGameModes();
                        while(!scanUserInput.hasNext(regexGameModes)) {
                            view.displayBadOptionWarning(regexGameModes);
                            scanUserInput.nextLine();
                        }
                        gameMode = gameMode.getModeFromInt(scanUserInput.nextInt()); 
                        startGame();
                        break;
                    case 2:
                        view.displayAuthorInfo();
                        System.in.read();
                        break;
                    case 3:
                        Highscores highscores = new Highscores(gameParameters.getUserName());
                        highscores.showHighscores();
                        break;
                    case 4:    
                        view.displayGoodbye();
                        System.in.read();
                        break;
                }
            } catch(InputMismatchException ex) {
                view.displayBadOptionWarning(regexOptions);
                scanUserInput.reset();
            } catch (IOException ex) {
                view.displayException("IOException", ex.getMessage());
            } catch (NotSupportedGameModeException e) {
                view.displayException(
                        "NotSupportedGameModeException",
                        e.getMessage());
            } 
        } while(userOption != 4);
        
        scanUserInput.close();
    }
    
    /**
     * Initializes and starts new "How f@st U Typ3" game.
     * 
     * @throws IOException
     */
    public void startGame() throws IOException {
        try {
            GameController newGame = new GameController(gameMode, gameParameters);
            newGame.begin();
        } catch (NotSupportedGameModeException e) {
            view.displayException(
                    "NotSupportedGameModeException",
                    e.getMessage(),
                    "Try choosing other game mode! We'll fix it soon!");
        }
    }
    
    /**
     * Gets regex containing values of game modes.
     * @return Regex with game modes numbers.
     */
    private String getRegexGameModesNumber() {
        StringBuilder sb = new StringBuilder();
        
        for(GameMode info : EnumSet.allOf(GameMode.class)) {
            sb.append(String.format("%d|", info.getMode()));
        }
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
}
