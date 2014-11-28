package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "Displays highscores table."
)
public class HighscoresViewGui extends JFrame {
    
    /**
     * Tree view of current users highscores.
     */
    private JTree highscores;

    /**
     * Label displaying current user name.
     */
    private JLabel currentUserName;
    
    
    /**
     * Dropdown list displaying 
     */
    private JComboBox<String> listOfUsers;  

    public HighscoresViewGui() {
        super("How f@st U typ3? - Hall of Fame");
    }
    
    public HighscoresViewGui(String currentUser, List<String> users) {
        super("How f@st U typ3? - Hall of Fame");
        setLayout(new FlowLayout());
        
        listOfUsers = new JComboBox<String>((String[]) users.toArray());
        currentUserName = new JLabel(currentUser);
        
        setVisible(true);
    }
    
    
    
    
}
