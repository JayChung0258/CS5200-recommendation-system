package user.model;

import java.math.BigDecimal;

public class UserDevice {
    protected int deviceID;
    protected int userID;
    protected String deviceType;
    protected BigDecimal internetSpeedMbps;
    protected String preferredDeviceForEntertainment;
    protected String dataPlanUsed;

    // Constructor with all fields
    public UserDevice(int deviceID, int userID, String deviceType, BigDecimal internetSpeedMbps,
                      String preferredDeviceForEntertainment, String dataPlanUsed) {
        this.deviceID = deviceID;
        this.userID = userID;
        this.deviceType = deviceType;
        this.internetSpeedMbps = internetSpeedMbps;
        this.preferredDeviceForEntertainment = preferredDeviceForEntertainment;
        this.dataPlanUsed = dataPlanUsed;
    }

    // Constructor without deviceID (for insertion cases)
    public UserDevice(int userID, String deviceType, BigDecimal internetSpeedMbps,
                      String preferredDeviceForEntertainment, String dataPlanUsed) {
        this.userID = userID;
        this.deviceType = deviceType;
        this.internetSpeedMbps = internetSpeedMbps;
        this.preferredDeviceForEntertainment = preferredDeviceForEntertainment;
        this.dataPlanUsed = dataPlanUsed;
    }

    // Constructor for lookup by deviceID
    public UserDevice(int deviceID) {
        this.deviceID = deviceID;
    }

    // Getters and Setters
    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public BigDecimal getInternetSpeedMbps() {
        return internetSpeedMbps;
    }

    public void setInternetSpeedMbps(BigDecimal internetSpeedMbps) {
        this.internetSpeedMbps = internetSpeedMbps;
    }

    public String getPreferredDeviceForEntertainment() {
        return preferredDeviceForEntertainment;
    }

    public void setPreferredDeviceForEntertainment(String preferredDeviceForEntertainment) {
        this.preferredDeviceForEntertainment = preferredDeviceForEntertainment;
    }

    public String getDataPlanUsed() {
        return dataPlanUsed;
    }

    public void setDataPlanUsed(String dataPlanUsed) {
        this.dataPlanUsed = dataPlanUsed;
    }
}
