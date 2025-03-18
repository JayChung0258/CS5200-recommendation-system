package user.model;

import java.math.BigDecimal;

public class EntertainmentUsage {
    protected int usageID;
    protected int userID;
    protected BigDecimal totalScreenTime;
    protected BigDecimal dailyEntertainmentTime;
    protected BigDecimal dailyVideoContentTime;
    protected BigDecimal dailyGamingTime;
    protected BigDecimal dailyMusicListeningTime;
    protected int subscriptionPlatforms;
    protected String preferredContentType;
    protected String preferredEntertainmentPlatform;

    // Constructor with all fields (for retrieval)
    public EntertainmentUsage(int usageID, int userID, BigDecimal totalScreenTime, BigDecimal dailyEntertainmentTime, 
                              BigDecimal dailyVideoContentTime, BigDecimal dailyGamingTime, BigDecimal dailyMusicListeningTime, 
                              int subscriptionPlatforms, String preferredContentType, String preferredEntertainmentPlatform) {
        this.usageID = usageID;
        this.userID = userID;
        this.totalScreenTime = totalScreenTime;
        this.dailyEntertainmentTime = dailyEntertainmentTime;
        this.dailyVideoContentTime = dailyVideoContentTime;
        this.dailyGamingTime = dailyGamingTime;
        this.dailyMusicListeningTime = dailyMusicListeningTime;
        this.subscriptionPlatforms = subscriptionPlatforms;
        this.preferredContentType = preferredContentType;
        this.preferredEntertainmentPlatform = preferredEntertainmentPlatform;
    }

    // Constructor without usageID (for insertion)
    public EntertainmentUsage(int userID, BigDecimal totalScreenTime, BigDecimal dailyEntertainmentTime, 
                              BigDecimal dailyVideoContentTime, BigDecimal dailyGamingTime, BigDecimal dailyMusicListeningTime, 
                              int subscriptionPlatforms, String preferredContentType, String preferredEntertainmentPlatform) {
        this.userID = userID;
        this.totalScreenTime = totalScreenTime;
        this.dailyEntertainmentTime = dailyEntertainmentTime;
        this.dailyVideoContentTime = dailyVideoContentTime;
        this.dailyGamingTime = dailyGamingTime;
        this.dailyMusicListeningTime = dailyMusicListeningTime;
        this.subscriptionPlatforms = subscriptionPlatforms;
        this.preferredContentType = preferredContentType;
        this.preferredEntertainmentPlatform = preferredEntertainmentPlatform;
    }

    // Constructor for lookup by usageID
    public EntertainmentUsage(int usageID) {
        this.usageID = usageID;
    }

    // Getters and Setters
    public int getUsageID() {
        return usageID;
    }

    public void setUsageID(int usageID) {
        this.usageID = usageID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public BigDecimal getTotalScreenTime() {
        return totalScreenTime;
    }

    public void setTotalScreenTime(BigDecimal totalScreenTime) {
        this.totalScreenTime = totalScreenTime;
    }

    public BigDecimal getDailyEntertainmentTime() {
        return dailyEntertainmentTime;
    }

    public void setDailyEntertainmentTime(BigDecimal dailyEntertainmentTime) {
        this.dailyEntertainmentTime = dailyEntertainmentTime;
    }

    public BigDecimal getDailyVideoContentTime() {
        return dailyVideoContentTime;
    }

    public void setDailyVideoContentTime(BigDecimal dailyVideoContentTime) {
        this.dailyVideoContentTime = dailyVideoContentTime;
    }

    public BigDecimal getDailyGamingTime() {
        return dailyGamingTime;
    }

    public void setDailyGamingTime(BigDecimal dailyGamingTime) {
        this.dailyGamingTime = dailyGamingTime;
    }

    public BigDecimal getDailyMusicListeningTime() {
        return dailyMusicListeningTime;
    }

    public void setDailyMusicListeningTime(BigDecimal dailyMusicListeningTime) {
        this.dailyMusicListeningTime = dailyMusicListeningTime;
    }

    public int getSubscriptionPlatforms() {
        return subscriptionPlatforms;
    }

    public void setSubscriptionPlatforms(int subscriptionPlatforms) {
        this.subscriptionPlatforms = subscriptionPlatforms;
    }

    public String getPreferredContentType() {
        return preferredContentType;
    }

    public void setPreferredContentType(String preferredContentType) {
        this.preferredContentType = preferredContentType;
    }

    public String getPreferredEntertainmentPlatform() {
        return preferredEntertainmentPlatform;
    }

    public void setPreferredEntertainmentPlatform(String preferredEntertainmentPlatform) {
        this.preferredEntertainmentPlatform = preferredEntertainmentPlatform;
    }
}
