package user.model;

import java.math.BigDecimal;

public abstract class UsageBase {
    protected int usageID;
    protected int userID;
    protected BigDecimal totalScreenTime;

    public UsageBase(int usageID, int userID, BigDecimal totalScreenTime) {
        this.usageID = usageID;
        this.userID = userID;
        this.totalScreenTime = totalScreenTime;
    }

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
}
