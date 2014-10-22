package pl.polsl.java.Bartlomiej.Szostek.views;

import pl.polsl.java.Bartlomiej.Szostek.views.MainView;
        
/**
 *
 * @author Bartek
 * @version 0.1 22 Oct 2014
 * 
 */
public class EntryPoint {

    /**
     * Entry point of the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView viewManager = new MainView(args);
        viewManager.displayMenu();
        
    }
    
}
