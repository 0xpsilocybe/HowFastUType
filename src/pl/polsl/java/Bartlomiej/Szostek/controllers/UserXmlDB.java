package pl.polsl.java.Bartlomiej.Szostek.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;
import pl.polsl.java.Bartlomiej.Szostek.models.*;

@ClassPreamble (
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Manages user database. Adding, deleting, adding scores."
)
public class UserXmlDB implements UserManagement{

    /**
     * Only instance of this db connector.
     */
    private static UserXmlDB instance = null;
    
    /**
     * Hidden constructor for singleton.
     */
    protected UserXmlDB() {
    }
    
    /**
     * Access to instance of singleton.
     * @return Instance of singleton.
     */
    public static UserXmlDB getInstance() {
        if(instance == null) {
            instance = new UserXmlDB();
        }
        return instance;
    }
    
    /**
     * Link to database.
     */
    private final String database = "db\\gameData.xml";
    
    /**
     * Add user to the database.
     * @param nickname User name to be added.
     * @return True if succeded, false if not.
     * @throws InvalidUserNameException
     */
    @Override
    public boolean addUser(String nickname) throws InvalidUserNameException {
        if(validateUserName(nickname)) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(database);
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xpath = xPathFactory.newXPath();
                XPathExpression expr = xpath.compile("/root/users");
                
                Node users = (Node) expr.evaluate(doc, XPathConstants.NODE);
                Element usr = doc.createElement("user");
                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(nickname));
                usr.appendChild(name);
                users.appendChild(usr);
                
                expr = xpath.compile("/root/highscores");
                Node highscores = (Node) expr.evaluate(doc, XPathConstants.NODE);
                usr = doc.createElement("user");
                usr.setAttribute("nick", nickname);
                Element casual = doc.createElement("casual");
                Element marathon = doc.createElement("marathon");
                Element reaction = doc.createElement("reaction");
                usr.appendChild(casual);
                usr.appendChild(marathon);
                usr.appendChild(reaction);
                highscores.appendChild(usr);
                
                saveDocument(doc);
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (XPathExpressionException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        return false;
    }
    
    /**
     * Delete user from the database. Deletes also all highscores of current user.
     * @param nickname User name to delete.
     * @return True if succeded, false if not.
     */
    @Override
    public boolean deleteUser(String nickname) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(database);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile(
                    String.format("/root/users/user[./name = '%s']", nickname)
            );
            Node userInstance = (Node) expr.evaluate(doc, XPathConstants.NODE);
            if(userInstance != null) {
                userInstance.getParentNode().removeChild(userInstance);

                expr = xpath.compile(
                        String.format("/root/highscores/user[@nick=\"%s\"]", nickname)
                );
                Node usrHighscores = (Node) expr.evaluate(doc, XPathConstants.NODE);
                usrHighscores.getParentNode().removeChild(usrHighscores);

                saveDocument(doc);
            }
        } catch (ParserConfigurationException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } catch (SAXException ex3) {
            ex3.printStackTrace();
        } catch (XPathExpressionException ex4) {
            ex4.printStackTrace();
        }
        return true;
    }
    
    /**
     * Clears given user highscores table.
     * @param nickname User name to delete.
     * @return True if succeded, false if not.
     */
    @Override
    public boolean clearHighscores(String nickname) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(database);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile(
                    String.format("/root/highscores/user[@nick=\"%s\"]/*/score", nickname)
            );
            
            NodeList usrHighscores = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            if(usrHighscores != null) {
                for(int i = 0; i < usrHighscores.getLength(); i++) {
                    Element el = (Element) usrHighscores.item(i);
                    el.getParentNode().removeChild(el);
                }
            }
            saveDocument(doc);
        } catch (ParserConfigurationException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } catch (SAXException ex3) {
            ex3.printStackTrace();
        } catch (XPathExpressionException ex4) {
            ex4.printStackTrace();
        }
        return true;
    }
         
    /**
      * Insert score for given user and game mode.
      * @param nickname User name.
      * @param mode Game mode.
      * @param score Score acquired
      * @return True if succeded, false if not.
      */
    @Override
    public boolean addScore(String nickname, GameMode mode, Score score) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(database);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile(
                    String.format("/root/highscores/user[@nick=\"%s\"]/%s", 
                            nickname,
                            mode.toString().toLowerCase())
            );
            
            Node usrHighscores = (Node) expr.evaluate(doc, XPathConstants.NODE);
            Element scr = doc.createElement("score");
            Element pts = doc.createElement("points");
            pts.appendChild(doc.createTextNode(Integer.toString(score.getPoints())));
            Element tm  = doc.createElement("time");
            tm.appendChild(doc.createTextNode(Integer.toString(score.getTime())));
            Element acc = doc.createElement("accuracy");
            acc.appendChild(doc.createTextNode(Double.toString(score.getAccuracy())));
            
            scr.appendChild(pts);
            scr.appendChild(tm);
            scr.appendChild(acc);
            usrHighscores.appendChild(scr);
            
            saveDocument(doc);
        } catch (ParserConfigurationException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } catch (SAXException ex3) {
            ex3.printStackTrace();
        } catch (XPathExpressionException ex4) {
            ex4.printStackTrace();
        }
        return true;
    }
    /**
     * Get user names from current db.
     * @return List of user names. 
     */
    @Override
    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<String>();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(database);
            
            NodeList users = doc.getElementsByTagName("users").item(0).getChildNodes();
            if(users != null) {
                for(int i = 0; i < users.getLength(); i++) {
                    if( users.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) users.item(i);
                        userNames.add(
                                el.getElementsByTagName("name").item(0).getTextContent()
                        );
                    }
                }
            }
        } catch (ParserConfigurationException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } catch (SAXException ex3) {
            ex3.printStackTrace();
        }
        return userNames;
    }
    
    /**
     * Validates if user name is in correct form and if it doesn't exist in db.
     * @param nickname User name to delete.
     * @return True if valid, false if not.
     * @throws InvalidUserNameException
     */
    public boolean validateUserName(String nickname) 
            throws InvalidUserNameException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]*$");
        Matcher matcher = pattern.matcher(nickname);
        
        if(!matcher.matches()) {
            throw new InvalidUserNameException(
                    "Nickname should contain only alfanumerics and underscores");
        }
        if(nickname.length() < 3 || nickname.length() > 10) {
            throw new InvalidUserNameException("Nickname should be 3 to 10 characters long");
        }
        if(getUserNames().contains(nickname)) {
            throw new InvalidUserNameException("Nickname already exists");
        }
        
        return true;
    }

    @Override
    public User getUser(String nickname) {
        User userData = new User(nickname);
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // XML parser
            UsersHandler handler = new UsersHandler(nickname);
            parser.parse(database, handler);
            
            if(handler.isUserFound()) {
                userData.setCasualScores(handler.getCasualScores());
                userData.setMarathonScores(handler.getMarathonScores());
                userData.setReactionScores(handler.getReactionScores());
            } else {
                userData = null;
            }
        } catch(SAXException e1) {
            System.err.println("XML parser exception - probably XML file was corrupted.");
        } catch (ParserConfigurationException e2) { 
            System.err.println(e2.getMessage());
        } catch (IOException e3) {
            System.err.println(e3.getMessage());
        }
        
        return userData;
    }
    
    /**
     * Saves modified document.
     * @param doc Document to save.
     */
    private void saveDocument(Document doc) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(new DOMSource(doc), new StreamResult(database));
        } catch(TransformerException ex1) {
            ex1.printStackTrace();
        } catch(TransformerFactoryConfigurationError ex2) {
            ex2.printStackTrace();
        }
    }
    
    /**
     * Class for getting user data with given nickname.
     */
    private class UsersHandler extends DefaultHandler {
        private final String nickname;
        private boolean userFound = false;
        private final List<Score> casualScores = new ArrayList<Score>();
        private final List<Score> marathonScores = new ArrayList<Score>();
        private final List<Score> reactionScores = new ArrayList<Score>();

        private int tm;
        private int pts;
        private double acc;

        private boolean users = false;
        private boolean user = false;
        private boolean userName = false;
        private boolean highscores = false;
        private boolean userHighscores = false;
        private boolean userCasualHighscores = false;
        private boolean userMarathonHighscores = false;
        private boolean userReactionHighscores = false;
        private boolean points = false;
        private boolean time = false;
        private boolean accuracy = false;

        public UsersHandler(String nickname) {
            this.nickname = nickname;
        }
        
        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if(qName.equalsIgnoreCase("USERS")) {
                users = true;
            }
            if(qName.equalsIgnoreCase("USER") && users) {
                user = true;
            }
            if(qName.equalsIgnoreCase("NAME") && user) {
                userName = true;
            }
            if(qName.equalsIgnoreCase("HIGHSCORES")) {
                highscores = true;
            }
            if(qName.equalsIgnoreCase("USER") && highscores 
                    && attributes.getValue(0).compareTo(nickname) == 0) {
                userHighscores = true;
            }
            if(qName.equalsIgnoreCase("CASUAL")) {
                userCasualHighscores = true;
            }
            if(qName.equalsIgnoreCase("MARATHON")) {
                userMarathonHighscores = true;
            }
            if(qName.equalsIgnoreCase("REACTION")) {
                userReactionHighscores = true;
            }
            if(qName.equalsIgnoreCase("POINTS")) {
                points = true;
            }
            if(qName.equalsIgnoreCase("TIME")) {
                time = true;
            }
            if(qName.equalsIgnoreCase("ACCURACY")) {
                accuracy = true;
            }
        }

        @Override
        public void endElement(String uri, String localName,
            String qName) {
            if(qName.equalsIgnoreCase("USERS")) {
                users = false;
            }
            if(qName.equalsIgnoreCase("USER")) {
                user = false;
            }
            if(qName.equalsIgnoreCase("HIGHSCORES")) {
                highscores = false;
            }
            if(qName.equalsIgnoreCase("USER") && highscores) {
                userHighscores = false;
            }
            if(qName.equalsIgnoreCase("CASUAL") && userHighscores) {
                userCasualHighscores = false;
            }
            if(qName.equalsIgnoreCase("MARATHON") && userHighscores) {
                userMarathonHighscores = false;
            }
            if(qName.equalsIgnoreCase("REACTION") && userHighscores) {
                userReactionHighscores = false;
            }
        }

        @Override
        public void characters(char ch[], int start, int length){
            String value = new String(ch, start, length);
            if(userName) {
                if(value.compareTo(nickname) == 0) {
                    userFound = true;
                }
                userName = false;
            }
            if(points) {
                pts = Integer.parseInt(value);
                points = false;
            }
            if(time) {
                tm = Integer.parseInt(value);
                time = false;
            }
            if(accuracy) {
                acc = Double.parseDouble(value);
                accuracy = false;
                
                if(userCasualHighscores) {
                    casualScores.add(new Score(tm, acc));
                }
                if(userMarathonHighscores) {
                    marathonScores.add(new Score(tm, acc));
                }
                if(userReactionHighscores) {
                    reactionScores.add(new Score(tm, acc));
                }
            }
        }

        /**
         * Check if user name has been found.
         * @return True if user found, false if not.
         */
        public boolean isUserFound() {
            return this.userFound;
        }

        /**
         * Gets parsed Casual scores list.
         * @return Casual scores list.
         */
        public List<Score> getCasualScores() {
            return this.casualScores;
        }

        /**
         * Gets parsed Marathon scores list.
         * @return Marathon scores list.
         */
        public List<Score> getMarathonScores() {
            return this.marathonScores;
        }

        /**
         * Gets parsed Reaction scores list.
         * @return Reaction scores list.
         */
        public List<Score> getReactionScores() {
            return this.reactionScores;
        }
    } 
}