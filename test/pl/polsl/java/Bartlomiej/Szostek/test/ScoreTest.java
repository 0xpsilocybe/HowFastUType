package pl.polsl.java.Bartlomiej.Szostek.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/11/14",
        lastModifiedDate = "24/11/14",
        version = 1.0,
        description = "Tests for score class."
)
public class ScoreTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private Score score = null;
    
    @Test
    public void testEquals() {
        // Arrange & Act
        score = new Score(10000, 100);
        
        // Assert
        assertEquals("Values differ by more than 0.01", 1000, score.getPoints(), 0.01);
    }
    
    @Test
    public void testIllegalArgumentExceptionTime() {
        // Arrange & Act & Assert
        exception.expect(IllegalArgumentException.class);
        score = new Score(-1, 0);
    }
    
    @Test
    public void testIllegalArgumentExceptionNegativeAccuracy() {
        // Arrange & Act & Assert
        exception.expect(IllegalArgumentException.class);
        score = new Score(1, -1);
    }
    
    @Test
    public void testIllegalArgumentExceptionAccuracyOverHundred() {
        // Arrange & Act & Assert
        exception.expect(IllegalArgumentException.class);
        score = new Score(-1, 101);
    }
}
