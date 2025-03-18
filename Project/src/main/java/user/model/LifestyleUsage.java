package user.model;

import java.math.BigDecimal;

public class LifestyleUsage extends UsageBase {
    protected BigDecimal averageSleepTime;
    protected BigDecimal physicalActivityTime;
    protected BigDecimal readingTime;
    protected BigDecimal workStudyTime;
    protected BigDecimal timeSpentInOnlineCommunities;
    protected BigDecimal timeOnEducationalPlatforms;
    protected BigDecimal newsConsumptionTime;
    protected int adInteractionCount;

    // Full Constructor
    public LifestyleUsage(int usageID, int userID, BigDecimal totalScreenTime,
                          BigDecimal averageSleepTime, BigDecimal physicalActivityTime,
                          BigDecimal readingTime, BigDecimal workStudyTime, BigDecimal timeSpentInOnlineCommunities,
                          BigDecimal timeOnEducationalPlatforms, BigDecimal newsConsumptionTime, int adInteractionCount) {
        super(usageID, userID, totalScreenTime);
        this.averageSleepTime = averageSleepTime;
        this.physicalActivityTime = physicalActivityTime;
        this.readingTime = readingTime;
        this.workStudyTime = workStudyTime;
        this.timeSpentInOnlineCommunities = timeSpentInOnlineCommunities;
        this.timeOnEducationalPlatforms = timeOnEducationalPlatforms;
        this.newsConsumptionTime = newsConsumptionTime;
        this.adInteractionCount = adInteractionCount;
    }

    // Constructor without usageID (for insertion)
    public LifestyleUsage(int userID, BigDecimal totalScreenTime, BigDecimal averageSleepTime, BigDecimal physicalActivityTime,
                          BigDecimal readingTime, BigDecimal workStudyTime, BigDecimal timeSpentInOnlineCommunities,
                          BigDecimal timeOnEducationalPlatforms, BigDecimal newsConsumptionTime, int adInteractionCount) {
        super(0, userID, totalScreenTime);
        this.averageSleepTime = averageSleepTime;
        this.physicalActivityTime = physicalActivityTime;
        this.readingTime = readingTime;
        this.workStudyTime = workStudyTime;
        this.timeSpentInOnlineCommunities = timeSpentInOnlineCommunities;
        this.timeOnEducationalPlatforms = timeOnEducationalPlatforms;
        this.newsConsumptionTime = newsConsumptionTime;
        this.adInteractionCount = adInteractionCount;
    }

    // Getters and Setters
    public BigDecimal getAverageSleepTime() { return averageSleepTime; }
    public void setAverageSleepTime(BigDecimal averageSleepTime) { this.averageSleepTime = averageSleepTime; }

    public BigDecimal getPhysicalActivityTime() { return physicalActivityTime; }
    public void setPhysicalActivityTime(BigDecimal physicalActivityTime) { this.physicalActivityTime = physicalActivityTime; }

    public BigDecimal getReadingTime() { return readingTime; }
    public void setReadingTime(BigDecimal readingTime) { this.readingTime = readingTime; }

    public BigDecimal getWorkStudyTime() { return workStudyTime; }
    public void setWorkStudyTime(BigDecimal workStudyTime) { this.workStudyTime = workStudyTime; }

    public BigDecimal getTimeSpentInOnlineCommunities() { return timeSpentInOnlineCommunities; }
    public void setTimeSpentInOnlineCommunities(BigDecimal timeSpentInOnlineCommunities) { this.timeSpentInOnlineCommunities = timeSpentInOnlineCommunities; }

    public BigDecimal getTimeOnEducationalPlatforms() { return timeOnEducationalPlatforms; }
    public void setTimeOnEducationalPlatforms(BigDecimal timeOnEducationalPlatforms) { this.timeOnEducationalPlatforms = timeOnEducationalPlatforms; }

    public BigDecimal getNewsConsumptionTime() { return newsConsumptionTime; }
    public void setNewsConsumptionTime(BigDecimal newsConsumptionTime) { this.newsConsumptionTime = newsConsumptionTime; }

    public int getAdInteractionCount() { return adInteractionCount; }
    public void setAdInteractionCount(int adInteractionCount) { this.adInteractionCount = adInteractionCount; }
}
