package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.HighscoresGui;
import pl.polsl.java.Bartlomiej.Szostek.models.GameMode;
import pl.polsl.java.Bartlomiej.Szostek.models.Score;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "27/10/14",
        lastModifiedDate = "27/11/14",
        version = 1.0,
        description = "Displays highscores table."
)
public class HighscoresViewGui extends ViewPanelBase {
    
    /** Controller for this view. */
    private HighscoresGui controller;
    
    /** Label displaying header. */
    private JLabel headerLbl;
    
    /** Root node of highscores tree. */
    private DefaultMutableTreeNode root;
    
    /** Custom scores node. */
    private DefaultMutableTreeNode casualNode;
    
    /** Marathon scores node. */
    private DefaultMutableTreeNode marathonNode;
    
    /** Reaction scores node. */
    private DefaultMutableTreeNode reactionNode;
    
    /** Tree model */
    protected DefaultTreeModel treeModel;
    
    /** Tree view of current users highscores. */
    private JTree highscoresTree;

    /** Scrollbar for highscores tree. */
    private JScrollPane scrollbar;
    
    /** Button to clear current user highscores table */
    private JButton clearHighscoresBtn;
    
    /** Button to go back to main view. */
    private JButton backBtn;
    
    /** Dropdown list displaying  */
    private JComboBox<String> listOfUsers;
    
    
    /**
     * Initializes highscores view.
     * @param controller Controller for this view.
     */
    public HighscoresViewGui(HighscoresGui controller) {
        this.controller = controller;
       
        initComponents();
        addListeners();
        
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
    }
    
    /** Initializes UI elements.
     */
    private void initComponents() {
        headerLbl = new JLabel();
        scrollbar = new JScrollPane();
        backBtn = new JButton();
        clearHighscoresBtn = new JButton();
        scrollbar = new JScrollPane();
        listOfUsers = new JComboBox<String>();
        for(String user : controller.getUsersList()) {
            listOfUsers.addItem(user);
        }
        
        root = new DefaultMutableTreeNode("NULL");
        casualNode = new DefaultMutableTreeNode("Custom");
        marathonNode = new DefaultMutableTreeNode("Marathon");
        reactionNode = new DefaultMutableTreeNode("Reaction");
        treeModel = new DefaultTreeModel(root);
        highscoresTree = new JTree(treeModel);
        
        backBtn.setText("Return");
        clearHighscoresBtn.setText("<html><center><font color=#FF0000>Clear highscores");
        headerLbl.setText("<html><center><font size=+2 color=#FF0000><b>Hall of Fame");
        scrollbar.setViewportView(highscoresTree);
        
        setLayout(new FlowLayout());
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap((width-100)/2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(backBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(headerLbl, 150, 150, 150)
                                    .addComponent(listOfUsers, 150, 150, 150))
                                .addGap(0, 10, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap((width-450)/2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)   
                            .addComponent(scrollbar, 450, 450, 450)
                            .addComponent(clearHighscoresBtn, 200, 200, 200)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(150)
                .addComponent(headerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(listOfUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollbar, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)    
                .addComponent(clearHighscoresBtn)
                .addGap(20)
                .addComponent(backBtn)
                .addContainerGap())
        );
    }
    
    /** Delete all nodes with setting new root.
     * @param newRoot New root name.
     * @param reload If true, reload tree model.
     */
    public void setRoot(String newRoot, boolean reload) {
        root.removeAllChildren();
        root.setUserObject(newRoot);
        
        root.add(casualNode);
        for(Score score : controller.getUserScores(GameMode.CASUAL)) {
            addScore(score, casualNode);
        }
        root.add(marathonNode);
        for(Score score : controller.getUserScores(GameMode.MARATHON)) {
            addScore(score, marathonNode);
        }
        root.add(reactionNode);
        for(Score score : controller.getUserScores(GameMode.REACTION)) {
            addScore(score, reactionNode);
        }
        
        if(reload) {
            treeModel.reload();
        }
    }
    
    /** Adds single score to defined node */
    private void addScore(Score score, DefaultMutableTreeNode parent) {
        
        DefaultMutableTreeNode scoreNode = new DefaultMutableTreeNode(
                String.format("Score %d", parent.getChildCount())
        );
        scoreNode.add(new DefaultMutableTreeNode(
                String.format("Points: %d", score.getPoints()))
        );
        scoreNode.add(new DefaultMutableTreeNode(
                String.format("Accuracy: %f", score.getAccuracy()))
        );
        scoreNode.add(new DefaultMutableTreeNode(
                String.format("Time: %d ms", score.getTime()))
        );
        
        treeModel.insertNodeInto(scoreNode, parent, 
                parent.getChildCount());
    }
    
    /** Adds listeners to UI elements.
     */
    private void addListeners() {
        this.clearHighscoresBtn.addActionListener((ActionEvent e) -> {
            Object[] options = {"Yea, sure.", "NO NO NO!!"};
            
            int choice = JOptionPane.showOptionDialog(this,
                            String.format("Are you sure you want to clear %s highscores?",
                                          controller.getCurrentUserName()),
                            "Highscores",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            options,
                            options[1]
            );
            
            if(choice == 0) {
                if(controller.clearHighscores()) {
                    setRoot(controller.getCurrentUserName(), true);
                    JOptionPane.showMessageDialog(this,
                            "Highscores table cleared.",
                            "Highscores",
                            JOptionPane.INFORMATION_MESSAGE
                            );
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Highscores table NOT cleared.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                            );
                }
            }
        });
        this.backBtn.addActionListener((ActionEvent e) -> {
            controller.endControl();
        });
        this.listOfUsers.addActionListener((ActionEvent e) -> {
            String choosenName = String.valueOf(listOfUsers.getSelectedItem());
            controller.changeElementUserData(choosenName);
            setRoot(choosenName, true);
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }    
}
