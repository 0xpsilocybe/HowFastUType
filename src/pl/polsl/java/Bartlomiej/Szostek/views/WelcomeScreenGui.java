package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.MainController;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "Welcome screen"
)
public class WelcomeScreenGui extends ViewPanelBase {

    /**
     * Controller of this view.
     */
    private MainController controller;
    
    
    /**
     * Button launching action that shows new user account creation.
     */
    private JButton newUserBtn;
    
    /**
     * Button launching action showing existing user list.
     */
    private JButton existingUserBtn;
    
    /**
     * Button leading to highscores view.
     */
    private JButton highscoresBtn;
    
    /**
     * Initializes welcome screen view.
     * @param controller Controller of this view.
     */
    public WelcomeScreenGui(MainController controller) {
        super();
       
        this.controller = controller;
        
        initComponents();
        addListeners();
        
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        repaint();
    }
    
    /**
     * Initializes window components.
     */
    private void initComponents() {
        newUserBtn = new JButton("<html><center><font size=+2 color=#000000><b>New user</b></font>");        
        existingUserBtn = new JButton("<html><center><font size=+2 color=#000000<b>Existing user</b></font>");   
        highscoresBtn = new JButton("<html><center><font size=+2 color=#000000><b>Hall of Fame</b></font>");
        
        int gap = width / 3;
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGap(gap)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(newUserBtn)
                        .addComponent(existingUserBtn)
                        .addComponent(highscoresBtn)
                      ) 
                    .addContainerGap(gap, gap))    
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250)
                .addComponent(newUserBtn)
                .addGap(25)
                .addComponent(existingUserBtn)
                .addGap(25)
                .addComponent(highscoresBtn))
        );
        add(newUserBtn);
        add(existingUserBtn);
        add(highscoresBtn);
    }
    
    /**
     * Initializes user interface events listeners.
     */
    private void addListeners() {
        this.newUserBtn.setActionCommand(controller.ELEMENT_NEWUSER_EVT);
        this.existingUserBtn.setActionCommand(controller.ELEMENT_EXISTINGUSER_EVT);
        this.highscoresBtn.setActionCommand(controller.ELEMENT_HIGHSCORES_EVT);
        
        this.newUserBtn.addActionListener(controller);
        this.existingUserBtn.addActionListener(controller);
        this.highscoresBtn.addActionListener(controller);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }    
}
