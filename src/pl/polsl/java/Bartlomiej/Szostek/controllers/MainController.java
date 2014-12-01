package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.views.HighscoresViewGui;
import pl.polsl.java.Bartlomiej.Szostek.views.MainViewGui;
import pl.polsl.java.Bartlomiej.Szostek.views.WelcomeScreenGui;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "28/10/14",
        lastModifiedDate = "28/11/14",
        version = 1.0,
        description = "Main controller of the application"
)
public class MainController extends ControllerBase {
      
    public final String ELEMENT_NEWUSER_EVT = "NewUser";
    public final String ELEMENT_EXISTINGUSER_EVT = "ExistingUser";
    public final String ELEMENT_HIGHSCORES_EVT = "Highscores";
    
    /**
     * Main window where we'll be injecting panels with 
     * views for different program states.
     */
    private final MainViewGui view;
    
    /**
     * Creates new instance of main controller.
     */
    public MainController() {
        view = new MainViewGui();
        setView(new WelcomeScreenGui(this));
    }
    
    /**
     * Get value of ELEMENT_NEWUSER_EVT.
     * @return ELEMENT_NEWUSER_EVT value.
     */
    public final String getNewUserActionCommandName() {
        return this.ELEMENT_NEWUSER_EVT;
    }
    
        /**
     * Get value of ELEMENT_EXISTINGUSER_EVT.
     * @return ELEMENT_EXISTINGUSER_EVT value.
     */
    public final String getExistingUserActionCommandName() {
        return this.ELEMENT_EXISTINGUSER_EVT;
    }
        
    /**
     * Get value of ELEMENT_HIGHSCORES_EVT.
     * @return ELEMENT_HIGHSCORES_EVT value.
     */
    public final String getHighscoresActionCommandName() {
        return this.ELEMENT_HIGHSCORES_EVT;
    }
    
    /**
     * Sets new content for our view.
     * @param newView New content for view.
     */
    public final void setView(JPanel newView) {
        setView(newView, null);
    }
    
    /**
     * Sets new content for our view.
     * @param newView New content for view.
     * @param newTitle New window title. If null, title stays the same.
     */
    public final void setView(JPanel newView, String newTitle) {
        this.view.setVisible(false);
        this.view.setContentPane(newView);
        if(newTitle != null) {
            setTitle(newTitle);
        }
        this.view.setVisible(true);
    }
    
    /**
     * Sets new title for window.
     * @param newTitle New title.
     */
    public final void setTitle(String newTitle) {
        this.view.setTitle(newTitle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ELEMENT_NEWUSER_EVT.equals(e.getActionCommand())) {
            
        } else if(ELEMENT_EXISTINGUSER_EVT.equals(e.getActionCommand())) {
            
        } else if(ELEMENT_HIGHSCORES_EVT.equals(e.getActionCommand())) { 
            setView(new HighscoresViewGui(new HighscoresGui()),
                    "How f@st U typ3? - Hall of Fame");
        }
    }
}
