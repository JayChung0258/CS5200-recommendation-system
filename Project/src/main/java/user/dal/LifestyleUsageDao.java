package user.dal;

import user.model.LifestyleUsage;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LifestyleUsageDao {
    protected ConnectionManager connectionManager;

    private static LifestyleUsageDao instance = null;

    protected LifestyleUsageDao() {
        connectionManager = new ConnectionManager();
    }

    public static LifestyleUsageDao getInstance() {
        if (instance == null) {
            instance = new LifestyleUsageDao();
        }
        return instance;
    }

    /** ✅ Insert into `usage_base` first, then insert into `lifestyle_usage` */
    public LifestyleUsage create(LifestyleUsage lifestyleUsage) throws SQLException {
        String insertUsageBase = "INSERT INTO usage_base (userID, totalScreenTime) VALUES (?, ?);";
        String insertLifestyle = "INSERT INTO lifestyle_usage (usageID, averageSleepTime, physicalActivityTime, readingTime, workStudyTime, timeSpentInOnlineCommunities, timeOnEducationalPlatforms, newsConsumptionTime, adInteractionCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement usageStmt = conn.prepareStatement(insertUsageBase, Statement.RETURN_GENERATED_KEYS)) {
            
            usageStmt.setInt(1, lifestyleUsage.getUserID());
            usageStmt.setBigDecimal(2, lifestyleUsage.getTotalScreenTime());
            usageStmt.executeUpdate();

            try (ResultSet rs = usageStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    lifestyleUsage.setUsageID(rs.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve generated usageID.");
                }
            }
        }

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement lifestyleStmt = conn.prepareStatement(insertLifestyle)) {
            lifestyleStmt.setInt(1, lifestyleUsage.getUsageID());
            lifestyleStmt.setBigDecimal(2, lifestyleUsage.getAverageSleepTime());
            lifestyleStmt.setBigDecimal(3, lifestyleUsage.getPhysicalActivityTime());
            lifestyleStmt.setBigDecimal(4, lifestyleUsage.getReadingTime());
            lifestyleStmt.setBigDecimal(5, lifestyleUsage.getWorkStudyTime());
            lifestyleStmt.setBigDecimal(6, lifestyleUsage.getTimeSpentInOnlineCommunities());
            lifestyleStmt.setBigDecimal(7, lifestyleUsage.getTimeOnEducationalPlatforms());
            lifestyleStmt.setBigDecimal(8, lifestyleUsage.getNewsConsumptionTime());
            lifestyleStmt.setInt(9, lifestyleUsage.getAdInteractionCount());
            lifestyleStmt.executeUpdate();
        }
        return lifestyleUsage;
    }

    /** ✅ Fetch a single LifestyleUsage record with `INNER JOIN` */
    public LifestyleUsage getLifestyleUsageById(int usageID) throws SQLException {
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, lu.averageSleepTime, lu.physicalActivityTime, lu.readingTime, lu.workStudyTime, lu.timeSpentInOnlineCommunities, lu.timeOnEducationalPlatforms, lu.newsConsumptionTime, lu.adInteractionCount FROM usage_base ub INNER JOIN lifestyle_usage lu ON ub.usageID = lu.usageID WHERE ub.usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usageID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LifestyleUsage(
                            rs.getInt("usageID"),
                            rs.getInt("userID"),
                            rs.getBigDecimal("totalScreenTime"),
                            rs.getBigDecimal("averageSleepTime"),
                            rs.getBigDecimal("physicalActivityTime"),
                            rs.getBigDecimal("readingTime"),
                            rs.getBigDecimal("workStudyTime"),
                            rs.getBigDecimal("timeSpentInOnlineCommunities"),
                            rs.getBigDecimal("timeOnEducationalPlatforms"),
                            rs.getBigDecimal("newsConsumptionTime"),
                            rs.getInt("adInteractionCount")
                    );
                }
            }
        }
        return null;
    }

    /** ✅ Retrieve all LifestyleUsage records using `INNER JOIN` */
    public List<LifestyleUsage> getAllLifestyleUsageRecords() throws SQLException {
        List<LifestyleUsage> usageList = new ArrayList<>();
        String sql = "SELECT ub.usageID, ub.userID, ub.totalScreenTime, lu.averageSleepTime, lu.physicalActivityTime, lu.readingTime, lu.workStudyTime, lu.timeSpentInOnlineCommunities, lu.timeOnEducationalPlatforms, lu.newsConsumptionTime, lu.adInteractionCount FROM usage_base ub INNER JOIN lifestyle_usage lu ON ub.usageID = lu.usageID";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usageList.add(new LifestyleUsage(
                        rs.getInt("usageID"),
                        rs.getInt("userID"),
                        rs.getBigDecimal("totalScreenTime"),
                        rs.getBigDecimal("averageSleepTime"),
                        rs.getBigDecimal("physicalActivityTime"),
                        rs.getBigDecimal("readingTime"),
                        rs.getBigDecimal("workStudyTime"),
                        rs.getBigDecimal("timeSpentInOnlineCommunities"),
                        rs.getBigDecimal("timeOnEducationalPlatforms"),
                        rs.getBigDecimal("newsConsumptionTime"),
                        rs.getInt("adInteractionCount")
                ));
            }
        }
        return usageList;
    }

    /** ✅ Update `averageSleepTime` */
    public LifestyleUsage updateAverageSleepTime(LifestyleUsage lifestyleUsage, BigDecimal newSleepTime) throws SQLException {
        String sql = "UPDATE lifestyle_usage SET averageSleepTime = ? WHERE usageID = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newSleepTime);
            stmt.setInt(2, lifestyleUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                lifestyleUsage.setAverageSleepTime(newSleepTime);
                return lifestyleUsage;
            }
        }
        return null;
    }

    /** ✅ Delete from `usage_base`, ensuring cascade delete */
    public LifestyleUsage delete(LifestyleUsage lifestyleUsage) throws SQLException {
        String sql = "DELETE FROM usage_base WHERE usageID = ?";  // Ensures `ON DELETE CASCADE` affects `lifestyle_usage`

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lifestyleUsage.getUsageID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return lifestyleUsage;
            }
        }
        return null;
    }
}
