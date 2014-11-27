package pl.polsl.java.Bartlomiej.Szostek.test;

import pl.polsl.java.Bartlomiej.Szostek.models.*;
import org.junit.*;
import static org.junit.Assert.*;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "15/11/14",
        lastModifiedDate = "24/11/14",
        version = 1.0,
        description = "Tests for random text generator class."
)
public class RandomTextGeneratorTest {
    RandomTextGenerator randomTextGenerator;
    
    @Test
    public void testGenerateTextDefault() {
        // Arrange
        randomTextGenerator = new RandomTextGenerator();
        
        // Act
        String text = randomTextGenerator.generateText();
        
        // Assert
        assertTrue("Text length to big!", text.length() < 550);
    }
    
    @Test
    public void testGenerateTextParametrized() {
        // Arrange
        randomTextGenerator = new RandomTextGenerator(5, 10);
        
        // Act
        String text = randomTextGenerator.generateText();
        
        // Assert
        assertTrue("Text length to big!", text.length() < (5+2)*10);
    }
    
    @Test
    public void testGenerateTextWordsCount() {
        //Arrange
        randomTextGenerator = new RandomTextGenerator(5, 10);
        
        // Act
        String text = randomTextGenerator.generateText();
        String[] splited = text.split(" ");
        
        // Assert
        assertEquals("Different number of words!", 10, splited.length);
    }
}
