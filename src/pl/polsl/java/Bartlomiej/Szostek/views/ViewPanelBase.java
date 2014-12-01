package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
     * Width of the panel.
     */
    protected final int width = 1024;
    
    /**
     * Height of the panel.
     */
    protected final int height = 768;
    
    /**
     * Logo image from resources.
     */
    private BufferedImage logo;
    
    /**
     * Background image from resources.
     */
    private BufferedImage background;
    
    /**
     * Path to logo image.
     */
    private final File logoImg = new File("resources\\logo.jpg");
    
    /**
     * Path to background image.
     */
    private final File backgroundImg = new File("resources\\keyboard.jpg");
       
    @Override
    public void paintComponent(Graphics g) {
        int xPos = 0;
        int yPos = 0;
        Graphics2D g2d = (Graphics2D) g;
        
        try {
            logo = ImageIO.read(logoImg);
            background = ImageIO.read(backgroundImg);
        } catch(IOException e) {
            System.err.println("Error reading images");
        }
        
        yPos = (width - logo.getWidth()) / 2;
        g2d.drawImage(logo, yPos, xPos, this);

        xPos = (logo.getHeight() + height - background.getHeight()) / 2;
        yPos = (width - background.getWidth()) / 2;
        g2d.drawImage(background, yPos, xPos, this);
    }
}
