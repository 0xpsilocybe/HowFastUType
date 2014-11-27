package pl.polsl.java.Bartlomiej.Szostek.views;

import java.util.List;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;
import pl.polsl.java.Bartlomiej.Szostek.models.User;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "23/11/14",
        lastModifiedDate = "23/11/14",
        version = 1.0,
        description = "Displays highscores table."
)

public class HighscoresView extends MainView {
    
    
    @Override
    public void displayMenu() {
        displayHeader();
        System.out.println("Select option:");
        System.out.println("Get current user scores - 1");
        System.out.println("Show other user scores  - 2");
        System.out.println("Go back                 - 3");
        System.out.format("%nYour option: ");
    }

    public void displayHighscores(User user) {
        displayHeader();
        System.out.format("%nUser %s leaderboard%n%n", user.getUserName());
        System.out.format("CASUAL%n");
        for(Score scr : user.getCasualScores()) {
            System.out.format("%s%n", scr);
        }
        
        System.out.format("%n%nMARATHON%n");
        for(Score scr : user.getMarathonScores()) {
            System.out.format("%s%n", scr);
        }
        
        System.out.format("%n%nREACTION%n");
        for(Score scr : user.getReactionScores()) {
            System.out.format("%s%n", scr);
        }
    }

    public void displayUsersList(List<String> userNames) {
        displayHeader();
        System.out.format("%nUsers in leaderboard:%n");
        for(String user : userNames) {
            System.out.format("%n%15s", user);
        }
        System.out.format("%nType nick from list: ");
    }
}
