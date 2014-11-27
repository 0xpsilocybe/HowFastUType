package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.User;
import pl.polsl.java.Bartlomiej.Szostek.views.HighscoresView;
        
@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Controls highscores viewing."
)
public class Highscores {
    /**
     * View for highscores tables.
     */
    private HighscoresView view = new HighscoresView();
    
    /**
     * List of users and their highscores table.
     */
    private String currentUserName;

    /**
     * Creates instance of highscores controller
     */
    public Highscores() {
    }

    /**
     * Creates instance of highscores controller.
     * @param currentUserName Current user name.
     */
    public Highscores(String currentUserName) {
        this.currentUserName = currentUserName;
    }
    
    /**
     * Shows highscores.
     */
    public void showHighscores() {
        Scanner scanUserInput = new Scanner(System.in);
        String regexOptions = "[1-3]";
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
                    case 1: {
                        UserXmlDB manager = UserXmlDB.getInstance();
                        User user = manager.getUser(currentUserName);
                        view.displayHighscores(user);
                        System.in.read();
                        break;
                    }
                    case 2: {
                        UserXmlDB manager = UserXmlDB.getInstance();
                        view.displayUsersList(manager.getUserNames());
                        
                        String nick;
                        while(!scanUserInput.hasNext()) {
                            scanUserInput.nextLine();
                        }
                        nick = scanUserInput.next();
                        User user = manager.getUser(nick);
                        view.displayHighscores(user);
                        System.in.read();
                        break;
                    }
                }
            } catch(InputMismatchException ex) {
                view.displayBadOptionWarning(regexOptions);
                scanUserInput.reset();
            } catch (IOException ex) {
                view.displayException(
                        "IOException", 
                        ex.getMessage());
            }
        } while(userOption != 3);
    }
    
    /**
     * Set current user name.
     * @param nick User name.
     */
    public final void setCurrentUserName(String nick) {
        this.currentUserName = nick;
    }
}
