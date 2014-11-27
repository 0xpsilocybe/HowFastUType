package pl.polsl.java.Bartlomiej.Szostek.views;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.UserInput;
        
@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "26/11/14",
        version = 1.1,
        description = "This is where the program starts."
)
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
