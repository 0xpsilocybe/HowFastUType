/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.Bartlomiej.Szostek.models;

/**
 *
 * @author Bartek
 */
public class TextModel {
    
    private int currentIndex;
    private String userInputText;
    private String templateText;
    
    public TextModel() {
        this.userInputText = null;
        this.templateText = null;
        this.currentIndex = 0;
    }

    public TextModel(String templateText) {
        this.templateText = templateText;
        this.currentIndex = 0;
    }
    
    public boolean addSignToText(char sign) {
        ++currentIndex;
        userInputText += sign;
        
        return (userInputText.charAt(currentIndex) 
                == templateText.charAt(currentIndex)) ? true
                                                      : false;
    }
}
