package pl.polsl.java.Bartlomiej.Szostek.models;

import pl.polsl.java.Bartlomiej.Szostek.annotations.ClassPreamble;

@ClassPreamble(
        author = "Bartłomiej Szostek",
        date = "24/10/14",
        lastModifiedDate = "23/11/14",
        version = 1.1,
        description = "Model that represents game mode choosen by the player."
)
public enum GameMode {
    CASUAL(1, "Casual"),
    MARATHON(2, "Marathon"),
    REACTION(3, "Reaction"),
    CASUAL_MULTI(101, "Multi Casual"),
    MARATHON_MULTI(102, "Multi Marathon"),
    REACTION_MULTI(103, "Multi Reaction");
    
    /**
     * Current game mode.
     */
    private final int mode;
    
    /**
     * Text representation of current game mode.
     */
    private final String textRepresentation;
    
    /**
     * Holds information about multiplayer mode.
     */
    private final boolean isMulti;
    
    /**
     * Initializes current game mode.
     * @param mode Explicitly describes game mode.
     */
    GameMode(int mode, String textRepresentation) {
        this.mode = mode;
        this.textRepresentation = textRepresentation;
        if(mode >= 100){
            isMulti = true;
        }
        else {
            isMulti = false;
        }
    }
    
    /**
     * Get current game mode.
     * @return Current game mode.
     */
    public final int getMode() {
        return mode;
    }
    
    
    public final GameMode getModeFromInt(int mode) throws NotSupportedGameModeException {
        switch(mode) {
            case 1:
                return GameMode.CASUAL;
            case 2:
                return GameMode.MARATHON;
            case 3:
                return GameMode.REACTION;
            case 101:
                return GameMode.CASUAL_MULTI;
            case 102:
                return GameMode.MARATHON_MULTI;
            case 103:
                return GameMode.REACTION_MULTI;
            default:
                throw new NotSupportedGameModeException("There isn't game mode with given number.");
        }
    }
    
    /**
     * Check if curren game mode is multiplayer mode.
     * @return True if current mode is multi, else false.
     */
    public boolean isMultiMode() {
        return isMulti;
    }
    
    /**
     * Get text representation of current game mode.
     * @return Name of current game mode.
     */
    @Override public String toString() {
        return textRepresentation;
    }
}
