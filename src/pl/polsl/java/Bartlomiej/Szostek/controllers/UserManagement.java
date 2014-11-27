package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.util.List;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.InvalidUserNameException;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;
import pl.polsl.java.Bartlomiej.Szostek.models.User;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Interface for user data management." +
                      "Adding, deleting, adding scores."
)
public interface UserManagement {
    
    /**
     * Add user to database.
     * @param nickname User name.
     * @return True if succeded, false if not.
     */
     public boolean addUser(String nickname) throws InvalidUserNameException;
     
     /**
      * Delete user from database.
      * @param nickname User name.
      * @return True if succeded, false if not.
      */
     public boolean deleteUser(String nickname);
     
     /**
      * Clear user highscores table.
      * @param nickname User name.
      * @return True if succeded, false if not.
      */
     public boolean clearHighscores(String nickname);
     
     /**
      * Insert score for given user and game mode.
      * @param nickname User name.
      * @param mode Game mode.
      * @param score Score acquired
      * @return True if succeded, false if not.
      */
     public boolean addScore(String nickname, GameMode mode, Score score);
     
     /**
      * Get list of user names in current db.
      * @return List of user names.
      */
     public List<String> getUserNames();
     
     /**
      * Get given user with his highscores table from db.
      * @param nickname User name.
      * @return User data.
      */
     public User getUser(String nickname);
}   
