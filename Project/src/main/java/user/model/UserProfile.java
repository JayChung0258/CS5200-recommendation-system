package user.model;

public class UserProfile {
    protected int profileID;
    protected int userID;
    protected String occupation;
    protected String maritalStatus;
    protected boolean parentalStatus;

    // Constructor with all fields
    public UserProfile(int profileID, int userID, String occupation, String maritalStatus, boolean parentalStatus) {
        this.profileID = profileID;
        this.userID = userID;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.parentalStatus = parentalStatus;
    }

    // Constructor without profileID (for insertion cases)
    public UserProfile(int userID, String occupation, String maritalStatus, boolean parentalStatus) {
        this.userID = userID;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.parentalStatus = parentalStatus;
    }

    // Constructor for lookup by profileID
    public UserProfile(int profileID) {
        this.profileID = profileID;
    }

    // Getters and Setters
    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isParentalStatus() {
        return parentalStatus;
    }

    public void setParentalStatus(boolean parentalStatus) {
        this.parentalStatus = parentalStatus;
    }
}
