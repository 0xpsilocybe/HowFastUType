package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Occurs when user inputs invalid user name."
)
public class InvalidUserNameException extends Exception{
    public InvalidUserNameException() { super(); }
    public InvalidUserNameException(String message) { super(message); }
    public InvalidUserNameException(String message, Throwable cause) { super(message, cause); }
    public InvalidUserNameException(Throwable cause) { super(cause); }
}
