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
    protected PropertyChangeSupport propertyChangeSupport;

    public ModelBase()
    {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

}
