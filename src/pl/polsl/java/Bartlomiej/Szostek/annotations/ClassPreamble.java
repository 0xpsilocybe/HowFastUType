package pl.polsl.java.Bartlomiej.Szostek.annotations;

import java.lang.annotation.*;

/**
 * Preamble of class.
 * 
 * @author Bartłomiej Szostek
 * @version 1.0
 */
@Documented
public @interface ClassPreamble {
    String author() default "Bartłomiej Szostek";
    String date();
    double version() default 1.0;
    String lastModifiedDate() default "N/A";
    String description();
}
