package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.ModelBase;
import pl.polsl.java.Bartlomiej.Szostek.views.ViewPanelBase;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "28/10/14",
        lastModifiedDate = "28/11/14",
        version = 1.1,
        description = "Abstract base for MVC controllers."
)
public abstract class ControllerBase implements PropertyChangeListener {
    private ArrayList<ViewPanelBase> registeredViews;
    private ArrayList<ModelBase> registeredModels;

    /**
     * Creates new instance of controller base.
     */
    public ControllerBase() {
        registeredViews = new ArrayList<ViewPanelBase>();
        registeredModels = new ArrayList<ModelBase>();
    }

    /**
     * Adds new model to registred models for this controller.
     * @param model Model to add.
     */
    public void addModel(ModelBase model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    /**
     * Removes model from registred models of this controller.
     * @param model Model to remove.
     */
    public void removeModel(ModelBase model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }
    
    /**
     * Adds new view to registred views for this controller.
     * @param view View to add.
     */
    public void addView(ViewPanelBase view) {
        registeredViews.add(view);
    }

    /**
     * Removes view from registred views of this controller.
     * @param view View to remove.
     */    
    public void removeView(ViewPanelBase view) {
        registeredViews.remove(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        for (ViewPanelBase view: registeredViews) {
            view.modelPropertyChange(evt);
        }
    }

    /**
     * This is a convenience method that subclasses can call upon
     * to fire property changes back to the models. This method
     * uses reflection to inspect each of the model classes
     * to determine whether it is the owner of the property
     * in question. If it isn't, a NoSuchMethodException is thrown,
     * which the method ignores.
     *
     * @param propertyName = The name of the property.
     * @param newValue = An object that represents the new value
     * of the property.
     */
    protected void setModelProperty(String propertyName, Object newValue) {
        for (ModelBase model: registeredModels) {
            try {
                Method method = model.getClass().
                    getMethod("set"+propertyName, new Class[] {
                                                      newValue.getClass()
                                                  }
                             );
                method.invoke(model, newValue);
            } catch (Exception ex) {
                //  Handle exception.
            }
        }
    }
}
