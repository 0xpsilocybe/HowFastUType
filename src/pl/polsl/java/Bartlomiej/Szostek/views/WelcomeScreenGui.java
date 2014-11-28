package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "Welcome screen"
)
public class WelcomeScreenGui extends JPanel {

    /**
     * Width of the panel.
     */
    private int width;
    
    /**
     * Height of the panel.
     */
    private int height;
    
    /**
     * Logo image from resources.
     */
    private BufferedImage logo;
    
    /**
     * Background image from resources.
     */
    private BufferedImage background;
    
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
     * @param width Width of the current panel.
     * @param height Height of the current panel.
     */
    public WelcomeScreenGui(int width, int height) {
        super();
       
        this.width = width;
        this.height = height;
        
        File logoImg = new File("resources\\logo.jpg");
        File backgroundImg = new File("resources\\keyboard.jpg");
        
        try {
            logo = ImageIO.read(logoImg);
            background = ImageIO.read(backgroundImg);
        } catch(IOException e) {
            System.err.println("Error reading images");
        }
        
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
        
        setPreferredSize(new Dimension(width, height));
    }
    
        @Override
	public void paintComponent(Graphics g) {
		int xPos = 0;
                int yPos = 0;
                Graphics2D g2d = (Graphics2D) g;
                
                yPos = (width - logo.getWidth()) / 2;
		g2d.drawImage(logo, yPos, xPos, this);
                
                xPos = (logo.getHeight() + height - background.getHeight()) / 2;
                yPos = (width - background.getWidth()) / 2;
                g2d.drawImage(background, yPos, xPos, this);
	}    
}
