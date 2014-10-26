package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.models.RandomTextGenerator;
import pl.polsl.java.Bartlomiej.Szostek.views.MainView;

/**
 *
 * @author Bartłomiej Szostek
 */
public class UserInput {
    /**
     * Number of words in current game.
     */
    private int numberOfWords;
    
    /**
     * Maximum length of single word in current game.
     */
    private int maxWordLength;
    private final MainView mainView;
    
    /**
     * Initializes class instance, shows standard application UI 
     * and forwards parameters to parser.
     * 
     * @param args Arguments taken from user.
     */
    public UserInput(String args[]) {
        mainView = new MainView();
        mainView.displayHeader();
        parseParameters(args);
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
                   IllegalArgumentException, NumberFormatException
    {
        try {
            numberOfWords = Integer.parseInt(args[0]);
            maxWordLength = Integer.parseInt(args[1]);
        } 
        catch (ArrayIndexOutOfBoundsException ex1) {
            mainView.displayException("ArrayIndexOutOfBoundsException",
                                       ex1.getMessage(), 
                                       "Too few arguments. See help.");
        }
        catch (NumberFormatException ex2) {
            mainView.displayException("NumberFormatException",
                                      ex2.getMessage(),
                                      "Parameters should be integers.");
        } 
        catch (IllegalArgumentException ex3) {
            mainView.displayException("IllegalArgumentException",
                                      ex3.getMessage());
        }
    }
    
    /**
     * Starts application menu. Retrives user option 
     * and passes it to corresponding view or controller.
     */
    public void start() {
        Scanner scanUserInput = new Scanner(System.in);
        String regexOptions = "[1-3]";
        int userOption = 0;
        
        do {
            mainView.displayMenu();
            try {
                while(!scanUserInput.hasNext(regexOptions)) {
                    mainView.displayBadOptionWarning();
                    scanUserInput.nextLine();
                }
                
                userOption = scanUserInput.nextInt();
                switch (userOption) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        mainView.displayAuthorInfo();
                        System.in.read();
                        break;
                    case 3:
                        mainView.displayGoodbye();
                        System.in.read();
                        break;
                }
            } catch(InputMismatchException ex) {
                mainView.displayBadOptionWarning();
                scanUserInput.reset();
            } catch (IOException ex) {
                mainView.displayException("IOException", ex.getMessage());
            }
        } while(userOption != 3);
        
        scanUserInput.close();
    }
    
    /**
     * Initializes and starts new "How f@st U Typ3" game.
     */
    public void startGame() {
        RandomTextGenerator randomTextGenenator = new RandomTextGenerator(maxWordLength, numberOfWords);
        GameController newGame = new GameController(randomTextGenenator.generateText());
        newGame.begin();
    }
}
