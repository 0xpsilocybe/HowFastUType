package pl.polsl.java.Bartlomiej.Szostek.views;

import java.io.IOException;
import java.util.EnumSet;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "15/11/14",
        version = 1.2,
        description = "This class provides simple UI for saying hello to user."
)
public class MainView {
    
    /**
     * Displays top header of application.
     */
    public void displayHeader() {
        System.out.format("%n%n%n%n%n%n%n%n%n%n%n");
        System.out.println("**********************");
        System.out.println("*  How f@st U Typ3?  *");
        System.out.println("**********************");
        System.out.println();
    }
    
    /**
     * Display main menu of application.
     */
    public void displayMenu() {
        displayHeader();
        System.out.println("Select option:");
        System.out.println("Start game - 1");
        System.out.println("Author     - 2");
        System.out.println("Highscores - 3");
        System.out.println("Goodbye    - 4");
        System.out.format("%nYour option: ");
    }
    
    /**
     * Displays warning, when user types in bad option argument.
     * @param options Options to choose by user.
     */
    public void displayBadOptionWarning(String options) {
        System.out.format("%nPlease select from %s.", options);
        System.out.format("%nChoose again: ");
    }
    
    /**
     * Displays information about author.
     */
    public void displayAuthorInfo() {
        displayHeader();
        System.out.println("Bartłomiej Szostek");
        System.out.println("Silesian University of Technology");
        System.out.format("%n%nNo rights reserved ;)");
    }
    
    /**
     * Gently goodbyes user.
     */
    public void displayGoodbye() {
        displayHeader();
        System.out.println("Thanks for playing!");
        System.out.println("See you soon!");
    }
    
    /**
     * Shows all available game modes.
     */
    public void displayGameModes() {
        System.out.println("Choose the game you would like to play!");
        for(GameMode info : EnumSet.allOf(GameMode.class)) {
            System.out.format("%d) %s%n", info.getMode(), info);
        }
        System.out.format("%n%nYour choice: ");
    }
    
    /**
     * Displays user-friendly information about exception that occured.
     * This method has a hint parameter set default to null.
     * 
     * See more information at:
     * {@link #displayException(String, String, String) displayException }
     * @param exceptionName Name of the exception thrown.
     * @param message Message included in the thrown exception.
     */
    public void displayException(String exceptionName, String message) {
        displayException(exceptionName, message, null);
    }
    
    /**
     * Displays user-friendly information about exception that occured.
     * 
     * @param exceptionName Type name of the exception.
     * @param message Message from exception.
     * @param hint How to not invoke the exception again.
     */
    public void displayException(String exceptionName, String message, String hint ) {
        System.err.println("An exception occured!");
        System.err.format("Name: %s%n", exceptionName);
        System.err.format("Message: %s%n", message);
        if(hint != null) {
            System.err.format("Hint: %s%n", hint);
        }
        try {
            System.err.print("Press key to continue. . .");
            System.in.read();
        } catch(IOException e){
            System.err.format("%nIOException occured - invalid input%n");
        }
    }
}
