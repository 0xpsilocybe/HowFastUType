package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.awt.event.ActionListener;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "28/10/14",
        lastModifiedDate = "28/11/14",
        version = 1.1,
        description = "Abstract base for MVC controllers."
)
public abstract class ControllerBase implements ActionListener {
    /** Parent controller that passed control flow */
    private ControllerBase parentController;
   
    /** 
     * Gets control from an other controller
     * @param controller Controller that passes control.
     */
    public abstract void getControl(ControllerBase controller);
    
    /** 
     * Passes control to an other controler
     * @param controller Controller to pass control.
     */
    public final void passControl(ControllerBase controller) {
        controller.setParent(this);
        controller.getControl(this);
    }
    
    /** Gives back control to parent controller */
    public final void endControl() {
        passControl(parentController);
    }
    
    /** Returns parent controller
     * @return  Parent controller. */
    public final ControllerBase getParent() {
        return this.parentController;
    }
    
    /** Sets parent controller.
     * @param parent Parent controller. */
    public final void setParent(ControllerBase parent) {
        this.parentController = parent;
    }
    
    
    
}
