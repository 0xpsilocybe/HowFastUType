package pl.polsl.java.Bartlomiej.Szostek.views;

import pl.polsl.java.Bartlomiej.Szostek.controllers.UserInput;
        
/**
 * This is where the program starts.
 * 
 * @author Bartłomiej Szostek
 * @version 1.0 26 Oct 2014
 */
public class EntryPoint {

    /**
     * Entry point of the application.
     * 
     * @param args the command line arguments:
     * 1. First parameter indicates the number of words in new game.
     * 2. Second parameter gives application the information about max
     *    length of single word, that it can generate.
     */
    public static void main(String[] args) {
        try {
            UserInput newGame = new UserInput(args);
            newGame.start();
        } catch(Exception e) {
            /* Exceptions are handled inside */
        }
    }
}
