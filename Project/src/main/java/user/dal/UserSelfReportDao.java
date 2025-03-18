package user.dal;

import user.model.UserSelfReport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSelfReportDao {
    protected ConnectionManager connectionManager;

    private static UserSelfReportDao instance = null;
    protected UserSelfReportDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserSelfReportDao getInstance() {
        if (instance == null) {
            instance = new UserSelfReportDao();
        }
        return instance;
    }

    public UserSelfReport create(UserSelfReport userSelfReport) {
        String sql = "INSERT INTO user_self_report (selfReportID, userID, sleepQuality, socialIsolationFeeling) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userSelfReport.getSelfReportID());
            stmt.setInt(2, userSelfReport.getUserID());
            stmt.setInt(3, userSelfReport.getSleepQuality());
            stmt.setInt(4, userSelfReport.getSocialIsolationFeeling());
            stmt.executeUpdate();
            return userSelfReport;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserSelfReport getUserSelfReportById(int selfReportID) {
        String sql = "SELECT * FROM user_self_report WHERE selfReportID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, selfReportID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserSelfReport(
                        rs.getInt("selfReportID"),
                        rs.getInt("userID"),
                        rs.getInt("sleepQuality"),
                        rs.getInt("socialIsolationFeeling")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserSelfReport> getAllUserSelfReports() {
        List<UserSelfReport> selfReports = new ArrayList<>();
        String sql = "SELECT * FROM user_self_report";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                selfReports.add(new UserSelfReport(
                        rs.getInt("selfReportID"),
                        rs.getInt("userID"),
                        rs.getInt("sleepQuality"),
                        rs.getInt("socialIsolationFeeling")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selfReports;
    }

    public UserSelfReport updateSleepQuality(UserSelfReport userSelfReport, int newSleepQuality) {
        String sql = "UPDATE user_self_report SET sleepQuality = ? WHERE selfReportID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newSleepQuality);
            stmt.setInt(2, userSelfReport.getSelfReportID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                userSelfReport.setSleepQuality(newSleepQuality);
                return userSelfReport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserSelfReport delete(UserSelfReport userSelfReport) {
        String sql = "DELETE FROM user_self_report WHERE selfReportID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userSelfReport.getSelfReportID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return userSelfReport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
