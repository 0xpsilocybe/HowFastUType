package pl.polsl.java.Bartlomiej.Szostek.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.GameParameters;
import pl.polsl.java.Bartlomiej.Szostek.models.InvalidUserNameException;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/11/14",
        lastModifiedDate = "24/11/14",
        version = 1.0,
        description = "Tests for text model class."
)
public class GameParametersTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private GameParameters parameters;
    
    @Test
    public void testInvalidUserNameTooLong() throws InvalidUserNameException {
        // Arrange
        String nick = "qwertyuiopa";
        
        // Act & Assert
        exception.expect(InvalidUserNameException.class);
        parameters = new GameParameters(nick, 10, 10);
    }
    
    @Test
    public void testInvalidUserNameTooShort() throws InvalidUserNameException {
        // Arrange
        String nick = "12";
        
        // Act & Assert
        exception.expect(InvalidUserNameException.class);
        parameters = new GameParameters(nick, 10, 10);
    }
    
    @Test
    public void testInvalidUserNameInvalidCharacter() throws InvalidUserNameException {
        // Arrange
        String nick = "ABC@";
        
        // Act & Assert
        exception.expect(InvalidUserNameException.class);
        parameters = new GameParameters(nick, 10, 10);
    }
    
    
    public void testValidUserName() throws InvalidUserNameException {
        // Arrange
        String nick = "Bartek";
        
        // Act
        parameters = new GameParameters(nick, 10, 10);
        
        // Assert
        assertEquals("Different nicknames.", nick, parameters.getUserName());
    }
}
