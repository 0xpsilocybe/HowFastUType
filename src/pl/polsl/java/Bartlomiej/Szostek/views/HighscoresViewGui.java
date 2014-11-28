package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.ControllerBase;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "Displays highscores table."
)
public class HighscoresViewGui extends ViewPanelBase {
    
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
        setName("How f@st U typ3? - Hall of Fame");
    }
    
    public HighscoresViewGui(String currentUser, List<String> users) {
        setName("How f@st U typ3? - Hall of Fame");
        setLayout(new FlowLayout());
        
        listOfUsers = new JComboBox<String>((String[]) users.toArray());
        currentUserName = new JLabel(currentUser);
        
        setVisible(true);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
