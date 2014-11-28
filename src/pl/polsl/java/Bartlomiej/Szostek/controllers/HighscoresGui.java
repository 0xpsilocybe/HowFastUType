package pl.polsl.java.Bartlomiej.Szostek.controllers;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.views.HighscoresViewGui;
        
@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Controls highscores viewing."
)
public class HighscoresGui extends ControllerBase {
    /**
     * View for highscores tables.
     */
    private final HighscoresViewGui view;
    
    /**
     * List of users and their highscores table.
     */
    private String currentUserName;

    /**
     * Creates instance of highscores controller
     */
    public HighscoresGui() {
        view = new HighscoresViewGui(null, null);
    }

    /**
     * Creates instance of highscores controller.
     * @param currentUserName Current user name.
     */
    public HighscoresGui(String currentUserName) {
        view = new HighscoresViewGui(currentUserName, null);
        
        this.currentUserName = currentUserName;
    }
    
    /**
     * Shows highscores.
     */
    public void showHighscores() {
        
    }
    
    /**
     * Set current user name.
     * @param nick User name.
     */
    public final void setCurrentUserName(String nick) {
        this.currentUserName = nick;
    }
}
