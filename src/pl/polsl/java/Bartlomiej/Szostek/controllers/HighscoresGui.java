package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
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
    
    public final String ELEMENT_CURRENT_USER_NAME_PROP = "currentUserName";
    public final String ELEMENT_USER_DATA = "currentUserData";
    public final String ELEMENT_USERS_LIST = "listOfUsers";
    
    /** View for highscores tables. */
    private final HighscoresViewGui view;

    /**  Current user name. */
    private String currentUserName;
    
    /** Current user data. */
    private User currentUserData;
    
    /** Creates instance of highscores controller. */
    public HighscoresGui() {
        view = new HighscoresViewGui(this);
        
    }
    
    /**
     * Get current user name.
     * @return User name.
     */
    public final String getCurrentUserName() {
        return this.currentUserName;
    }
    
    /**
     * Set current user name.
     * @param nick User name.
     */
    public final void setCurrentUserName(String nick) {
        this.currentUserName = nick;
    }
    
    public void changeElementUserName(String nick) {
        setModelProperty(ELEMENT_CURRENT_USER_NAME_PROP, nick);
    }
    
    public void changeElementUserData(User user) {
        setModelProperty(ELEMENT_USER_DATA, user);
    }
    
    public void changeElementUsersList() {
        
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (ELEMENT_CURRENT_USER_NAME_PROP.equals(e.getActionCommand())) {
            
        } else if(ELEMENT_USER_DATA.equals(e.getActionCommand())) {
            
        }
    }
}
