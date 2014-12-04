package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.GameController;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "This view is obligated to provide high " +
                      "user-experience interface during the game."
)
public class GameViewGui extends ViewPanelBase {
    
    private GameController controller;
    
    private final JButton returnBtn = new JButton("Return");
    private final JButton startBtn = new JButton("Start game");
    private final JButton finishBtn = new JButton("Finish!");
    private final JLabel userNameLbl = new JLabel("User name");
    private final JLabel gameModeLbl = new JLabel("Game mode");
    private final JLabel wordLengthLbl = new JLabel("Max word length");
    private final JLabel wordLengthIndicatorLbl = new JLabel();
    private final JLabel wordsCountLbl = new JLabel("Max word count");
    private final JLabel wordsCountIndicatorLbl = new JLabel();
    private final JLabel infoLabel1 = new JLabel("Try to rewrite following text:");
    private final JLabel infoLabel2 = new JLabel("Type as fast as you can:");
    private final JSlider wordLengthSlider = new JSlider(3, 25, 7);
    private final JSlider wordsCountSlider = new JSlider(5, 50, 10);
    private final JComboBox<String> userNames = new JComboBox<String>();
    private final JComboBox<String> gameModes = new JComboBox<String>();
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    
    
    
    
    
    public GameViewGui(GameController controller) {
        this.controller = controller;
        
        initComponents();
        addListeners();
        
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
    }

    public void showText(String text) {
        
        
    }
    
    
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        for(String user : controller.getUsersList()) {
            userNames.addItem(user);
        }
        
        for(GameMode mode : controller.getGameModes()) {
            gameModes.addItem(mode.toString());
        }
        
        Font font = new Font("Serif", Font.PLAIN, 18);
        Color fontColor = new Color(102, 255, 51);
        Color bgColor = new Color(0, 0, 0);
        
        userNameLbl.setOpaque(true);
        userNameLbl.setFont(font);
        userNameLbl.setForeground(fontColor);
        userNameLbl.setBackground(bgColor);
        gameModeLbl.setOpaque(true);
        gameModeLbl.setFont(font);
        gameModeLbl.setForeground(fontColor);
        gameModeLbl.setBackground(bgColor);
        wordLengthLbl.setOpaque(true);
        wordLengthLbl.setFont(font);
        wordLengthLbl.setForeground(fontColor);
        wordLengthLbl.setBackground(bgColor);
        wordsCountLbl.setOpaque(true);
        wordsCountLbl.setFont(font);
        wordsCountLbl.setForeground(fontColor);
        wordsCountLbl.setBackground(bgColor);
        wordLengthIndicatorLbl.setOpaque(true);
        wordLengthIndicatorLbl.setFont(font);
        wordLengthIndicatorLbl.setForeground(fontColor);
        wordLengthIndicatorLbl.setBackground(bgColor);
        wordsCountIndicatorLbl.setOpaque(true);
        wordsCountIndicatorLbl.setFont(font);
        wordsCountIndicatorLbl.setForeground(fontColor);
        wordsCountIndicatorLbl.setBackground(bgColor);
        
        Binding binding = 
                Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, 
                        wordsCountSlider, ELProperty.create("${value}"), 
                        wordLengthIndicatorLbl, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, 
                wordLengthSlider, ELProperty.create("${value}"), wordsCountIndicatorLbl, 
                BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(width/3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wordLengthLbl)
                                    .addComponent(wordLengthSlider, 100, 100, 100)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(userNameLbl)
                                        .addGap(50)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(gameModeLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(gameModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(wordsCountLbl))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(startBtn)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(wordsCountIndicatorLbl)
                                                .addGap(50)
                                                .addComponent(wordsCountSlider, 100, 100, 100)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(wordLengthIndicatorLbl)))))))
                        .addGap(0, 148, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(height/4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNameLbl)
                    .addComponent(gameModeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gameModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wordLengthLbl)
                    .addComponent(wordsCountLbl))
                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wordLengthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wordsCountSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wordsCountIndicatorLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(startBtn)
                        .addGap(25, 25, 25)
                        .addComponent(returnBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wordLengthIndicatorLbl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bindingGroup.bind();
        wordsCountSlider.setValue(7);
        wordLengthSlider.setValue(10);
    }

    private void addListeners() {
        this.returnBtn.addActionListener((ActionEvent e) -> {
            controller.endControl();
        });
        this.startBtn.addActionListener((ActionEvent e) -> {
            try {
                controller.setMaxWordLength(wordLengthSlider.getValue());
                controller.setWordsCount(wordsCountSlider.getValue());
                controller.setUserName(String.valueOf(userNames.getSelectedItem()));
                controller.setGameMode(GameMode.valueOf(
                        String.valueOf(gameModes.getSelectedItem()))
            );
            controller.begin();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                            String.format("Game could not be started!%n%s",
                                    ex.getMessage()),
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                            );
            }
        });
    }
}
