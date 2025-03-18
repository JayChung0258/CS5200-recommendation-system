package user.dal;

import user.model.UserTechAwareness;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTechAwarenessDao {
    protected ConnectionManager connectionManager;

    private static UserTechAwarenessDao instance = null;
    protected UserTechAwarenessDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserTechAwarenessDao getInstance() {
        if (instance == null) {
            instance = new UserTechAwarenessDao();
        }
        return instance;
    }

    public UserTechAwareness create(UserTechAwareness userTechAwareness) {
        String sql = "INSERT INTO user_tech_awareness (techID, userID, techSavvinessLevel, digitalWellBeingAwareness) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userTechAwareness.getTechID());
            stmt.setInt(2, userTechAwareness.getUserID());
            stmt.setInt(3, userTechAwareness.getTechSavvinessLevel());
            stmt.setString(4, userTechAwareness.getDigitalWellBeingAwareness());
            stmt.executeUpdate();
            return userTechAwareness;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserTechAwareness getUserTechAwarenessById(int techID) {
        String sql = "SELECT * FROM user_tech_awareness WHERE techID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, techID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserTechAwareness(
                        rs.getInt("techID"),
                        rs.getInt("userID"),
                        rs.getInt("techSavvinessLevel"),
                        rs.getString("digitalWellBeingAwareness")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserTechAwareness> getAllUserTechAwareness() {
        List<UserTechAwareness> techAwarenessList = new ArrayList<>();
        String sql = "SELECT * FROM user_tech_awareness";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                techAwarenessList.add(new UserTechAwareness(
                        rs.getInt("techID"),
                        rs.getInt("userID"),
                        rs.getInt("techSavvinessLevel"),
                        rs.getString("digitalWellBeingAwareness")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return techAwarenessList;
    }

    public UserTechAwareness updateTechSavvinessLevel(UserTechAwareness userTechAwareness, int newLevel) {
        String sql = "UPDATE user_tech_awareness SET techSavvinessLevel = ? WHERE techID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newLevel);
            stmt.setInt(2, userTechAwareness.getTechID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                userTechAwareness.setTechSavvinessLevel(newLevel);
                return userTechAwareness;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserTechAwareness delete(UserTechAwareness userTechAwareness) {
        String sql = "DELETE FROM user_tech_awareness WHERE techID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userTechAwareness.getTechID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return userTechAwareness;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
