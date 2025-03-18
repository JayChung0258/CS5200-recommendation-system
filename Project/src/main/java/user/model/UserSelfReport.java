package user.model;

public class UserSelfReport {
    protected int selfReportID;
    protected int userID;
    protected int sleepQuality;
    protected int socialIsolationFeeling;

    // Constructor with all fields
    public UserSelfReport(int selfReportID, int userID, int sleepQuality, int socialIsolationFeeling) {
        this.selfReportID = selfReportID;
        this.userID = userID;
        this.sleepQuality = sleepQuality;
        this.socialIsolationFeeling = socialIsolationFeeling;
    }

    // Constructor without selfReportID (for insertion cases)
    public UserSelfReport(int userID, int sleepQuality, int socialIsolationFeeling) {
        this.userID = userID;
        this.sleepQuality = sleepQuality;
        this.socialIsolationFeeling = socialIsolationFeeling;
    }

    // Constructor for lookup by selfReportID
    public UserSelfReport(int selfReportID) {
        this.selfReportID = selfReportID;
    }

    // Getters and Setters
    public int getSelfReportID() {
        return selfReportID;
    }

    public void setSelfReportID(int selfReportID) {
        this.selfReportID = selfReportID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public int getSocialIsolationFeeling() {
        return socialIsolationFeeling;
    }

    public void setSocialIsolationFeeling(int socialIsolationFeeling) {
        this.socialIsolationFeeling = socialIsolationFeeling;
    }
}
