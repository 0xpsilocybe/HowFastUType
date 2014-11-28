package pl.polsl.java.Bartlomiej.Szostek.views;

import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "28/10/14",
        lastModifiedDate = "28/11/14",
        version = 1.0,
        description = "Abstract base for MVC views created using JPanel."
)
public abstract class ViewPanelBase extends JPanel {

    /**
     * Called by the controller when it needs to pass along a property change 
     * from a model.
     *
     * @param event The property change event from the model
     */
    public abstract void modelPropertyChange(PropertyChangeEvent event);
    
}
