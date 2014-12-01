package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;
import pl.polsl.java.Bartlomiej.Szostek.models.User;
import pl.polsl.java.Bartlomiej.Szostek.views.HighscoresViewGui;
        
@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Controls highscores viewing."
)
public class HighscoresGui extends ControllerBase {
    
    public final String ELEMENT_USER_DATA = "currentUserData";
    
    /** View for highscores tables. */
    private final HighscoresViewGui view;

    /** Current user data. */
    private User currentUserData;
    
    /** Creates instance of highscores controller. */
    public HighscoresGui() {
        view = new HighscoresViewGui(this);
        currentUserData = new User("");
    }
    
    public void changeElementUserData(String userName) {
        UserXmlDB manager = UserXmlDB.getInstance();
        currentUserData = manager.getUser(userName);
    }
    
    /**
     * Get users names from database.
     * @return Users names.
     */
    public final String[] getUsersList() {
        UserXmlDB manager = UserXmlDB.getInstance();
        List<String> list = manager.getUserNames();
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        return arr;
    }
    
    /**
     * Gets current user name.
     * @return Current user name.
     */
    public String getCurrentUserName() {
        return currentUserData.getUserName();
    }
    
    
    /** 
     * Clears highscores table of current user
     * @return True if succeded, false otherwise 
     */
    public boolean clearHighscores() {
        UserXmlDB manager = UserXmlDB.getInstance();
        return manager.clearHighscores(getCurrentUserName());
    }
    
    /**
     * Retruns user mode highscores accorfing to mode param.
     * @param mode Game mode.
     * @return List of highscores from given mode.
     */
    public final List<Score> getUserScores(GameMode mode) {
        switch(mode) {
            case CASUAL:
                return currentUserData.getCasualScores();
            case MARATHON:
                return currentUserData.getMarathonScores();
            case REACTION:
                return currentUserData.getReactionScores();
        }
        return new ArrayList<Score>();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void getControl(ControllerBase controller) {
    }
    
}
