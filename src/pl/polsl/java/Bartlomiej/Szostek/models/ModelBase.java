package pl.polsl.java.Bartlomiej.Szostek.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "28/10/14",
        lastModifiedDate = "28/11/14",
        version = 1.0,
        description = "Abstract model base for model classes"
)
public abstract class ModelBase {
    
    /**
     * On property changed supporter.
     */
    protected PropertyChangeSupport propertyChangeSupport;

    /**
     * Creates new instance of model base.
     */
    public ModelBase()
    {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Adds new listener to current model.
     * @param listener Listener to add to current model.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes listener from current model.
     * @param listener Listener to remove from current model.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Should be launched when property is changed.
     * @param propertyName Name of model variable that has changed.
     * @param oldValue Old model variable state.
     * @param newValue New model variable state.
     */
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

}
