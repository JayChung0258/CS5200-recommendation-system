package user.model;

import java.math.BigDecimal;

public class SocialMediaUsage extends UsageBase {
    private BigDecimal dailySocialMediaTime;
    private BigDecimal dailyMessagingTime;
    private int socialMediaPlatformsUsed;
    private String primaryPlatform;
    private String primarySocialMediaGoal;
    private int notificationsReceivedDaily;
    private int socialMediaFatigueLevel;

    // Full constructor (with usageID)
    public SocialMediaUsage(int usageID, int userID, BigDecimal totalScreenTime, BigDecimal dailySocialMediaTime, 
                            BigDecimal dailyMessagingTime, int socialMediaPlatformsUsed, String primaryPlatform, 
                            String primarySocialMediaGoal, int notificationsReceivedDaily, int socialMediaFatigueLevel) {
        super(usageID, userID, totalScreenTime);
        this.dailySocialMediaTime = dailySocialMediaTime;
        this.dailyMessagingTime = dailyMessagingTime;
        this.socialMediaPlatformsUsed = socialMediaPlatformsUsed;
        this.primaryPlatform = primaryPlatform;
        this.primarySocialMediaGoal = primarySocialMediaGoal;
        this.notificationsReceivedDaily = notificationsReceivedDaily;
        this.socialMediaFatigueLevel = socialMediaFatigueLevel;
    }

    // Constructor without usageID (for new insertions)
    public SocialMediaUsage(int userID, BigDecimal totalScreenTime, BigDecimal dailySocialMediaTime, 
                            BigDecimal dailyMessagingTime, int socialMediaPlatformsUsed, String primaryPlatform, 
                            String primarySocialMediaGoal, int notificationsReceivedDaily, int socialMediaFatigueLevel) {
        super(0, userID, totalScreenTime); 
        this.dailySocialMediaTime = dailySocialMediaTime;
        this.dailyMessagingTime = dailyMessagingTime;
        this.socialMediaPlatformsUsed = socialMediaPlatformsUsed;
        this.primaryPlatform = primaryPlatform;
        this.primarySocialMediaGoal = primarySocialMediaGoal;
        this.notificationsReceivedDaily = notificationsReceivedDaily;
        this.socialMediaFatigueLevel = socialMediaFatigueLevel;
    }

    // Constructor for lookup by usageID
    public SocialMediaUsage(int usageID) {
        super(usageID, 0, BigDecimal.ZERO); 
    }

    // Getters and Setters
    public BigDecimal getDailySocialMediaTime() {
        return dailySocialMediaTime;
    }

    public void setDailySocialMediaTime(BigDecimal dailySocialMediaTime) {
        this.dailySocialMediaTime = dailySocialMediaTime;
    }

    public BigDecimal getDailyMessagingTime() {
        return dailyMessagingTime;
    }

    public void setDailyMessagingTime(BigDecimal dailyMessagingTime) {
        this.dailyMessagingTime = dailyMessagingTime;
    }

    public int getSocialMediaPlatformsUsed() {
        return socialMediaPlatformsUsed;
    }

    public void setSocialMediaPlatformsUsed(int socialMediaPlatformsUsed) {
        this.socialMediaPlatformsUsed = socialMediaPlatformsUsed;
    }

    public String getPrimaryPlatform() {
        return primaryPlatform;
    }

    public void setPrimaryPlatform(String primaryPlatform) {
        this.primaryPlatform = primaryPlatform;
    }

    public String getPrimarySocialMediaGoal() {
        return primarySocialMediaGoal;
    }

    public void setPrimarySocialMediaGoal(String primarySocialMediaGoal) {
        this.primarySocialMediaGoal = primarySocialMediaGoal;
    }

    public int getNotificationsReceivedDaily() {
        return notificationsReceivedDaily;
    }

    public void setNotificationsReceivedDaily(int notificationsReceivedDaily) {
        this.notificationsReceivedDaily = notificationsReceivedDaily;
    }

    public int getSocialMediaFatigueLevel() {
        return socialMediaFatigueLevel;
    }

    public void setSocialMediaFatigueLevel(int socialMediaFatigueLevel) {
        this.socialMediaFatigueLevel = socialMediaFatigueLevel;
    }
}
