package pl.polsl.java.Bartlomiej.Szostek.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.controllers.HighscoresGui;

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
    private DefaultMutableTreeNode customNode;
    
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
        scrollbar = new JScrollPane();
        listOfUsers = new JComboBox<String>(new DefaultComboBoxModel<String>());
        for(String user : controller.getUsersList()) {
            listOfUsers.addItem(user);
        }
        
        root = new DefaultMutableTreeNode();
        customNode = new DefaultMutableTreeNode("Custom");
        marathonNode = new DefaultMutableTreeNode("Marathon");
        reactionNode = new DefaultMutableTreeNode("Reaction");
        
        setRoot(String.valueOf(listOfUsers.getSelectedItem()), false);
        treeModel = new DefaultTreeModel(root);
        highscoresTree = new JTree(treeModel);
        
        backBtn.setText("Return");
        headerLbl.setText("<html><center><font size=+2 color=#FF0000><b>Hall of Fame</b></font>");
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
                        .addComponent(scrollbar, 450, 450, 450)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
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
        root.add(customNode);
        root.add(marathonNode);
        root.add(reactionNode);
        
        if(reload) {
            treeModel.reload();
        }
    }
    
    /** Adds listeners to UI elements.
     */
    private void addListeners() {
        this.backBtn.setActionCommand("backBtn");
        this.listOfUsers.setActionCommand("listOfUsers");
        
        
        
        this.backBtn.addActionListener(controller);
        this.listOfUsers.addItemListener((ItemEvent e) -> {
            controller.changeElementUserName(String.valueOf(listOfUsers.getSelectedItem()));
            
            
        });
    }
    
    private void backButtonActionPerformed(ActionEvent evt) {
        
    }

    
    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

       if (evt.getPropertyName().equals(
                  controller.ELEMENT_CURRENT_USER_NAME_PROP)) {
           String newStringValue = evt.getNewValue().toString();
           setRoot(newStringValue, true);
       } else if (evt.getPropertyName().equals(
                  controller.ELEMENT_USER_DATA)) {
           
       }
    }
        
        
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }    
}
