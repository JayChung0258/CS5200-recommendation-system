package user.dal;

import user.model.EntertainmentUsage;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntertainmentUsageDao {
    protected ConnectionManager connectionManager;

    private static EntertainmentUsageDao instance = null;

    protected EntertainmentUsageDao() {
        connectionManager = new ConnectionManager();
    }

    public static EntertainmentUsageDao getInstance() {
        if (instance == null) {
            instance = new EntertainmentUsageDao();
        }
        return instance;
    }

    /**
     * Inserts into `usage_base` first, retrieves `usageID`, then inserts into `entertainment_usage`.
     */
    public EntertainmentUsage create(EntertainmentUsage entertainmentUsage) throws SQLException {
        String insertUsageBase = "INSERT INTO usage_base (userID, totalScreenTime) VALUES (?, ?);";
        String insertEntertainment = "INSERT INTO entertainment_usage (usageID, userID, dailyEntertainmentTime, dailyVideoContentTime, dailyGamingTime, dailyMusicListeningTime, subscriptionPlatforms, preferredContentType, preferredEntertainmentPlatform) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement usageStmt = conn.prepareStatement(insertUsageBase, Statement.RETURN_GENERATED_KEYS)) {

            usageStmt.setInt(1, entertainmentUsage.getUserID());
            usageStmt.setBigDecimal(2, entertainmentUsage.getTotalScreenTime());
            usageStmt.executeUpdate();

            try (ResultSet rs = usageStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entertainmentUsage.setUsageID(rs.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve usageID.");
                }
            }
        }

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement entertainmentStmt = conn.prepareStatement(insertEntertainment)) {
            entertainmentStmt.setInt(1, entertainmentUsage.getUsageID());
            entertainmentStmt.setInt(2, entertainmentUsage.getUserID());
            entertainmentStmt.setBigDecimal(3, entertainmentUsage.getDailyEntertainmentTime());
            entertainmentStmt.setBigDecimal(4, entertainmentUsage.getDailyVideoContentTime());
            entertainmentStmt.setBigDecimal(5, entertainmentUsage.getDailyGamingTime());
            entertainmentStmt.setBigDecimal(6, entertainmentUsage.getDailyMusicListeningTime());
            entertainmentStmt.setInt(7, entertainmentUsage.getSubscriptionPlatforms());
            entertainmentStmt.setString(8, entertainmentUsage.getPreferredContentType());
            entertainmentStmt.setString(9, entertainmentUsage.getPreferredEntertainmentPlatform());
            entertainmentStmt.executeUpdate();
        }
        return entertainmentUsage;
    }

    /**
     * Retrieves `EntertainmentUsage` by `usageID`, including `totalScreenTime` from `usage_base`.
     */
    public EntertainmentUsage getEntertainmentUsageById(int usageID) throws SQLException {
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, eu.dailyEntertainmentTime, eu.dailyVideoContentTime, eu.dailyGamingTime, eu.dailyMusicListeningTime, eu.subscriptionPlatforms, eu.preferredContentType, eu.preferredEntertainmentPlatform FROM usage_base ub INNER JOIN entertainment_usage eu ON ub.usageID = eu.usageID WHERE ub.usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usageID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EntertainmentUsage(
                            rs.getInt("usageID"),
                            rs.getInt("userID"),
                            rs.getBigDecimal("totalScreenTime"),
                            rs.getBigDecimal("dailyEntertainmentTime"),
                            rs.getBigDecimal("dailyVideoContentTime"),
                            rs.getBigDecimal("dailyGamingTime"),
                            rs.getBigDecimal("dailyMusicListeningTime"),
                            rs.getInt("subscriptionPlatforms"),
                            rs.getString("preferredContentType"),
                            rs.getString("preferredEntertainmentPlatform")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all `EntertainmentUsage` records, including `totalScreenTime` from `usage_base`.
     */
    public List<EntertainmentUsage> getAllEntertainmentUsageRecords() throws SQLException {
        List<EntertainmentUsage> usageList = new ArrayList<>();
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, eu.dailyEntertainmentTime, eu.dailyVideoContentTime, eu.dailyGamingTime, eu.dailyMusicListeningTime, eu.subscriptionPlatforms, eu.preferredContentType, eu.preferredEntertainmentPlatform FROM usage_base ub INNER JOIN entertainment_usage eu ON ub.usageID = eu.usageID";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usageList.add(new EntertainmentUsage(
                        rs.getInt("usageID"),
                        rs.getInt("userID"),
                        rs.getBigDecimal("totalScreenTime"),
                        rs.getBigDecimal("dailyEntertainmentTime"),
                        rs.getBigDecimal("dailyVideoContentTime"),
                        rs.getBigDecimal("dailyGamingTime"),
                        rs.getBigDecimal("dailyMusicListeningTime"),
                        rs.getInt("subscriptionPlatforms"),
                        rs.getString("preferredContentType"),
                        rs.getString("preferredEntertainmentPlatform")
                ));
            }
        }
        return usageList;
    }

    /**
     * Updates `dailyEntertainmentTime` for a given `usageID`.
     */
    public EntertainmentUsage updateDailyEntertainmentTime(EntertainmentUsage entertainmentUsage, BigDecimal newTime) throws SQLException {
        String sql = "UPDATE entertainment_usage SET dailyEntertainmentTime = ? WHERE usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newTime);
            stmt.setInt(2, entertainmentUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                entertainmentUsage.setDailyEntertainmentTime(newTime);
                return entertainmentUsage;
            }
        }
        return null;
    }

    /**
     * Deletes `EntertainmentUsage`, ensuring `usage_base` is also deleted.
     */
    public EntertainmentUsage delete(EntertainmentUsage entertainmentUsage) throws SQLException {
        String sql = "DELETE FROM usage_base WHERE usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entertainmentUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return entertainmentUsage;
            }
        }
        return null;
    }
}
