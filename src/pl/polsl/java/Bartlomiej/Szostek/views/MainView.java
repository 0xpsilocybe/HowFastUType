package pl.polsl.java.Bartlomiej.Szostek.views;

/**
 * This class provides simple UI for saying "hello" to user.
 * @author Bartek
 * @version 0.6 22 Oct 2014
 */
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
        System.out.println("Goodbye    - 3");
        System.out.format("%nYour option: ");
    }
    
    
    public void displayBadOptionWarning() {
        System.out.format("%nPlease select 1, 2 or 3.");
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
     * Displays user-friendly information about exception that occured.
     * This method has a hint parameter set default to null.
     * 
     * See more information at:
     * {@link #displayException(String, String, String), displayException }
     * @param exceptionName Name of the exception thrown.
     * @param message Message included in the thrown exception.
     */
    public void displayException(String exceptionName, String message) {
        displayException(exceptionName, message, null);
    }

    /**
     * Displays user-friendly information about exception that occured.
     * 
     * @param exceptionName
     * @param message
     * @param hint 
     */
    public void displayException(String exceptionName, String message, String hint ) {
        System.err.println("An exception occured!");
        System.err.format("Name: %s%n", exceptionName);
        System.err.format("Message: %s%n", message);
        if(hint != null) {
            System.err.format("Hint: %s%n", hint);
        }
    }
}
