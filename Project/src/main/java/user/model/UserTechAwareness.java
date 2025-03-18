package user.model;

public class UserTechAwareness {
    protected int techID;
    protected int userID;
    protected int techSavvinessLevel;
    protected String digitalWellBeingAwareness;

    // Constructor with all fields
    public UserTechAwareness(int techID, int userID, int techSavvinessLevel, String digitalWellBeingAwareness) {
        this.techID = techID;
        this.userID = userID;
        this.techSavvinessLevel = techSavvinessLevel;
        this.digitalWellBeingAwareness = digitalWellBeingAwareness;
    }

    // Constructor without techID (for insertion cases)
    public UserTechAwareness(int userID, int techSavvinessLevel, String digitalWellBeingAwareness) {
        this.userID = userID;
        this.techSavvinessLevel = techSavvinessLevel;
        this.digitalWellBeingAwareness = digitalWellBeingAwareness;
    }

    // Constructor for lookup by techID
    public UserTechAwareness(int techID) {
        this.techID = techID;
    }

    // Getters and Setters
    public int getTechID() {
        return techID;
    }

    public void setTechID(int techID) {
        this.techID = techID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTechSavvinessLevel() {
        return techSavvinessLevel;
    }

    public void setTechSavvinessLevel(int techSavvinessLevel) {
        this.techSavvinessLevel = techSavvinessLevel;
    }

    public String getDigitalWellBeingAwareness() {
        return digitalWellBeingAwareness;
    }

    public void setDigitalWellBeingAwareness(String digitalWellBeingAwareness) {
        this.digitalWellBeingAwareness = digitalWellBeingAwareness;
    }
}