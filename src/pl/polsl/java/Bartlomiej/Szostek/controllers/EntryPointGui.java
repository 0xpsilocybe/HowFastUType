package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.EventQueue;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.MainController;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "This is where the program starts."
)
public class EntryPointGui {
    
    /**
     * Entry point of application.
     * @param args Application arguments.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainController controller = new MainController();
        });
    }
}
