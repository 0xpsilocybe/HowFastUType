package pl.polsl.java.Bartlomiej.Szostek.views;

/**
 * This view is obligated to provide high 
 * user-experience interface during the game.
 * 
 * Bartłomiej Szostek
 */
public class InGameView extends MainView {

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
     * Displays result of the game.
     * @param numberOfMistakes Number of mistakes made by user.
     * @param textLength Length of text of past game.
     * @param miliseconds Time took to finish the game.
     */
    public void displayResult(int numberOfMistakes, int textLength, long miliseconds) {
        int percentage = (numberOfMistakes * 100) / textLength;
        
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
        
        percentage = 100 - percentage;
        System.out.format("%nYou misstyped %d characters.%n", numberOfMistakes);
        System.out.format("That gives you %d%% accuracy.%n", percentage);
        System.out.format("It took you %d miliseconds.", miliseconds);
    }
    
}
