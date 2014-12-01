package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Color;
import javax.swing.JFrame;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "This class provides simple UI for saying hello to user."
)
public class MainViewGui extends JFrame {
    
    /**
     * Constructor.
     */
    public MainViewGui() {
        super("How f@st U typ3?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        
        setSize(1024, 768);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
