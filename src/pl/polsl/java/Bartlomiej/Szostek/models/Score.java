package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "23/11/14",
        lastModifiedDate = "23/11/14",
        version = 1.0,
        description = "This model represents single score."
)
public class Score {
    /**
     * Points gained in this game.
     */
    private final int points;
    
    /**
     * The time in witch the game was completed.
     */
    private final int timeInMiliseconds;
    
    /**
     * Accuracy achieved in game.
     */
    private final double accuracy;

    /**
     * Creates single score instance.
     * @param timeInMiliseconds
     * @param accuracy 
     * @throws IllegalArgumentException
     */
    public Score(int timeInMiliseconds, double accuracy) 
            throws IllegalArgumentException{
        if(timeInMiliseconds < 0) {
            throw new IllegalArgumentException("Time should be positive.");
        }
        if(accuracy < 0 || accuracy > 100) {
            throw new IllegalArgumentException("Accuracy should be from 0 to 100");
        }
        this.points = (int) (100000 * accuracy) / timeInMiliseconds;
        this.timeInMiliseconds = timeInMiliseconds;
        this.accuracy = accuracy;
    }
    
    /**
     * Get points.
     * @return Points.
     */
    public final int getPoints() {
        return this.points;
    }
    
    /**
     * Get time.
     * @return Time.
     */
    public final int getTime() {
        return this.timeInMiliseconds;
    }
    
    /**
     * Get accuracy.
     * @return Accuracy.
     */
    public final double getAccuracy() {
        return this.accuracy;
    }
    
    @Override
    public String toString() {
        return String.format("    Points: %d    Time: %dms    Accuracy: %f",
                                getPoints(), getTime(), getAccuracy());
    }
}
