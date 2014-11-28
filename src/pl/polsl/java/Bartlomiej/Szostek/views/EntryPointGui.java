package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.EventQueue;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "This is where the program starts."
)
public class EntryPointGui {
        public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    new MainViewGui();
                });
	}
}
