package pl.polsl.java.Bartlomiej.Szostek.views;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "This view is obligated to provide high " +
                      "user-experience interface during the game."
)
public class GameView extends MainView {

    /**
     * Displays generated text for user.
     * @param text Text to show.
     */
    public void displayText(String text) {
        super.displayHeader();
        
        System.out.format("%n%s%n", text);
        System.out.println("=================================================");
        System.out.format("Type as fast as you can below and proceede with ENTER:%n%n");
    }

    /**
     * Displays info when some feature is not yet implemented.
     */
    public void displayNotImplemented() {
        System.out.format("%nSorry.%nThis game mode is not yet implemented.%n");
    }
    
    /**
     * Displays result of the game.
     * @param numberOfMistakes Number of mistakes made by user.
     * @param percentage  Accuracy percentage.
     * @param miliseconds Time took to finish the game.
     */
    public void displayResult(int numberOfMistakes, double percentage, long miliseconds) {
        System.out.format("%nGAME OVER!%n%n");
        if(numberOfMistakes == 0) {
            System.out.print("PERFECT!");
        } else if(percentage < 20) {
            System.out.print("ALMOST!");
        } else if(percentage < 40) {
            System.out.print("NOT BAD!");
        } else if(percentage < 60) {
            System.out.print("BEGININNGS ARE HARD!");
        } else if(percentage < 80) {
            System.out.print("LMAO!");
        } else if(percentage == 100) {
            System.out.print("LEARN TO TYPE!");
        } 
        
        System.out.format("%nYou misstyped %d characters.%n", numberOfMistakes);
        System.out.format("That gives you %f%% accuracy.%n", percentage);
        System.out.format("It took you %d miliseconds.", miliseconds);
    }
    
}
