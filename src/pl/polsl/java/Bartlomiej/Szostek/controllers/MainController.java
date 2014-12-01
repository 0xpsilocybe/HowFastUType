package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.GameParameters;
import pl.polsl.java.Bartlomiej.Szostek.models.InvalidUserNameException;
import pl.polsl.java.Bartlomiej.Szostek.views.GameViewGui;
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
    public final String ELEMENT_GAME_EVT = "Game";
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
        if(ELEMENT_GAME_EVT.equals(e.getActionCommand())) {
            GameController controller = new GameController();
            setView(new GameViewGui(controller));
            passControl(controller);
        } else if(ELEMENT_HIGHSCORES_EVT.equals(e.getActionCommand())) { 
            HighscoresGui controller = new HighscoresGui();
            setView(new HighscoresViewGui(controller));
            passControl(controller);
        }
    }

    @Override
    public void getControl(ControllerBase controller) {
        setView(new WelcomeScreenGui(this));
    }
}
