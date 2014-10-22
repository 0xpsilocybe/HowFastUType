package pl.polsl.java.Bartlomiej.Szostek.views;

/**
 *
 * @author Bartek
 * @version 0.5 22 Oct 2014
 */
public class MainView {

    private final String[] args;
    
    public MainView(String[] argumentsArray) {
        this.args = argumentsArray;
    }
    
    public void displayMenu() {
        System.out.println("**********************");
        System.out.println("*  How f@st U Typ3?  *");
        System.out.println("**********************");
    }
}
