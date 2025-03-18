package user.dal;

import user.model.UserDevice;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDeviceDao {
    protected ConnectionManager connectionManager;

    private static UserDeviceDao instance = null;
    protected UserDeviceDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserDeviceDao getInstance() {
        if (instance == null) {
            instance = new UserDeviceDao();
        }
        return instance;
    }

    public UserDevice create(UserDevice userDevice) {
        String sql = "INSERT INTO user_device (deviceID, userID, deviceType, internetSpeedMbps, preferredDeviceForEntertainment, dataPlanUsed) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userDevice.getDeviceID());
            stmt.setInt(2, userDevice.getUserID());
            stmt.setString(3, userDevice.getDeviceType());
            stmt.setBigDecimal(4, userDevice.getInternetSpeedMbps());
            stmt.setString(5, userDevice.getPreferredDeviceForEntertainment());
            stmt.setString(6, userDevice.getDataPlanUsed());
            stmt.executeUpdate();
            return userDevice;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDevice getUserDeviceById(int deviceID) {
        String sql = "SELECT * FROM user_device WHERE deviceID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDevice(
                        rs.getInt("deviceID"),
                        rs.getInt("userID"),
                        rs.getString("deviceType"),
                        rs.getBigDecimal("internetSpeedMbps"),
                        rs.getString("preferredDeviceForEntertainment"),
                        rs.getString("dataPlanUsed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDevice> getAllUserDevices() {
        List<UserDevice> devices = new ArrayList<>();
        String sql = "SELECT * FROM user_device";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                devices.add(new UserDevice(
                        rs.getInt("deviceID"),
                        rs.getInt("userID"),
                        rs.getString("deviceType"),
                        rs.getBigDecimal("internetSpeedMbps"),
                        rs.getString("preferredDeviceForEntertainment"),
                        rs.getString("dataPlanUsed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    public UserDevice updateInternetSpeed(UserDevice userDevice, BigDecimal newSpeed) {
        String sql = "UPDATE user_device SET internetSpeedMbps = ? WHERE deviceID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newSpeed);
            stmt.setInt(2, userDevice.getDeviceID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                userDevice.setInternetSpeedMbps(newSpeed);
                return userDevice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDevice delete(UserDevice userDevice) {
        String sql = "DELETE FROM user_device WHERE deviceID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userDevice.getDeviceID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return userDevice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
