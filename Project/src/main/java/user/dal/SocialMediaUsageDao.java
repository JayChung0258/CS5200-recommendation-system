package user.dal;

import user.model.SocialMediaUsage;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaUsageDao {
    protected ConnectionManager connectionManager;

    private static SocialMediaUsageDao instance = null;

    protected SocialMediaUsageDao() {
        connectionManager = new ConnectionManager();
    }

    public static SocialMediaUsageDao getInstance() {
        if (instance == null) {
            instance = new SocialMediaUsageDao();
        }
        return instance;
    }


    public SocialMediaUsage create(SocialMediaUsage socialMediaUsage) throws SQLException {
        String insertUsageBase = "INSERT INTO usage_base (userID, totalScreenTime) VALUES (?, ?);";
        String insertSocialMedia = "INSERT INTO social_media_usage (usageID, userID, dailySocialMediaTime, dailyMessagingTime, socialMediaPlatformsUsed, primaryPlatform, primarySocialMediaGoal, notificationsReceivedDaily, socialMediaFatigueLevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement usageStmt = conn.prepareStatement(insertUsageBase, Statement.RETURN_GENERATED_KEYS)) {


            usageStmt.setInt(1, socialMediaUsage.getUserID());
            usageStmt.setBigDecimal(2, socialMediaUsage.getTotalScreenTime());
            usageStmt.executeUpdate();


            try (ResultSet rs = usageStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    socialMediaUsage.setUsageID(rs.getInt(1)); 
                } else {
                    throw new SQLException("Failed to retrieve usageID.");
                }
            }
        }


        try (Connection conn = connectionManager.getConnection();
             PreparedStatement socialMediaStmt = conn.prepareStatement(insertSocialMedia)) {
            socialMediaStmt.setInt(1, socialMediaUsage.getUsageID());
            socialMediaStmt.setInt(2, socialMediaUsage.getUserID());
            socialMediaStmt.setBigDecimal(3, socialMediaUsage.getDailySocialMediaTime());
            socialMediaStmt.setBigDecimal(4, socialMediaUsage.getDailyMessagingTime());
            socialMediaStmt.setInt(5, socialMediaUsage.getSocialMediaPlatformsUsed());
            socialMediaStmt.setString(6, socialMediaUsage.getPrimaryPlatform());
            socialMediaStmt.setString(7, socialMediaUsage.getPrimarySocialMediaGoal());
            socialMediaStmt.setInt(8, socialMediaUsage.getNotificationsReceivedDaily());
            socialMediaStmt.setInt(9, socialMediaUsage.getSocialMediaFatigueLevel());
            socialMediaStmt.executeUpdate();
        }
        return socialMediaUsage;
    }


    public SocialMediaUsage getSocialMediaUsageById(int usageID) throws SQLException {
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, smu.dailySocialMediaTime, smu.dailyMessagingTime, smu.socialMediaPlatformsUsed, smu.primaryPlatform, smu.primarySocialMediaGoal, smu.notificationsReceivedDaily, smu.socialMediaFatigueLevel FROM usage_base ub INNER JOIN social_media_usage smu ON ub.usageID = smu.usageID WHERE ub.usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usageID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SocialMediaUsage(
                            rs.getInt("usageID"),
                            rs.getInt("userID"),
                            rs.getBigDecimal("totalScreenTime"),
                            rs.getBigDecimal("dailySocialMediaTime"),
                            rs.getBigDecimal("dailyMessagingTime"),
                            rs.getInt("socialMediaPlatformsUsed"),
                            rs.getString("primaryPlatform"),
                            rs.getString("primarySocialMediaGoal"),
                            rs.getInt("notificationsReceivedDaily"),
                            rs.getInt("socialMediaFatigueLevel")
                    );
                }
            }
        }
        return null;
    }


    public List<SocialMediaUsage> getAllSocialMediaUsageRecords() throws SQLException {
        List<SocialMediaUsage> usageList = new ArrayList<>();
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, smu.dailySocialMediaTime, smu.dailyMessagingTime, smu.socialMediaPlatformsUsed, smu.primaryPlatform, smu.primarySocialMediaGoal, smu.notificationsReceivedDaily, smu.socialMediaFatigueLevel FROM usage_base ub INNER JOIN social_media_usage smu ON ub.usageID = smu.usageID";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usageList.add(new SocialMediaUsage(
                        rs.getInt("usageID"),
                        rs.getInt("userID"),
                        rs.getBigDecimal("totalScreenTime"),
                        rs.getBigDecimal("dailySocialMediaTime"),
                        rs.getBigDecimal("dailyMessagingTime"),
                        rs.getInt("socialMediaPlatformsUsed"),
                        rs.getString("primaryPlatform"),
                        rs.getString("primarySocialMediaGoal"),
                        rs.getInt("notificationsReceivedDaily"),
                        rs.getInt("socialMediaFatigueLevel")
                ));
            }
        }
        return usageList;
    }


    public SocialMediaUsage updateDailySocialMediaTime(SocialMediaUsage socialMediaUsage, BigDecimal newTime) throws SQLException {
        String sql = "UPDATE social_media_usage SET dailySocialMediaTime = ? WHERE usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newTime);
            stmt.setInt(2, socialMediaUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                socialMediaUsage.setDailySocialMediaTime(newTime);
                return socialMediaUsage;
            }
        }
        return null;
    }


    public SocialMediaUsage delete(SocialMediaUsage socialMediaUsage) throws SQLException {
        String sql = "DELETE FROM usage_base WHERE usageID = ?";  // 確保 `ON DELETE CASCADE` 生效

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, socialMediaUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return socialMediaUsage;
            }
        }
        return null;
    }
}
