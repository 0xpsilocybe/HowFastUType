package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Occurs when call to game mode that is not yet present."
)
public class NotSupportedGameModeException extends Exception {
    public NotSupportedGameModeException() { super(); }
    public NotSupportedGameModeException(String message) { super(message); }
    public NotSupportedGameModeException(String message, Throwable cause) { super(message, cause); }
    public NotSupportedGameModeException(Throwable cause) { super(cause); }
}
