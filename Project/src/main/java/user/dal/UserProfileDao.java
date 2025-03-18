package user.dal;

import user.model.UserProfile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDao {
    protected ConnectionManager connectionManager;

    private static UserProfileDao instance = null;
    protected UserProfileDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserProfileDao getInstance() {
        if (instance == null) {
            instance = new UserProfileDao();
        }
        return instance;
    }

    public UserProfile create(UserProfile userProfile) {
        String sql = "INSERT INTO user_profile (profileID, userID, occupation, maritalStatus, parentalStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userProfile.getProfileID());
            stmt.setInt(2, userProfile.getUserID());
            stmt.setString(3, userProfile.getOccupation());
            stmt.setString(4, userProfile.getMaritalStatus());
            stmt.setBoolean(5, userProfile.isParentalStatus());
            stmt.executeUpdate();
            return userProfile;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserProfile getUserProfileById(int profileID) {
        String sql = "SELECT * FROM user_profile WHERE profileID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profileID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserProfile(
                        rs.getInt("profileID"),
                        rs.getInt("userID"),
                        rs.getString("occupation"),
                        rs.getString("maritalStatus"),
                        rs.getBoolean("parentalStatus")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM user_profile";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                profiles.add(new UserProfile(
                        rs.getInt("profileID"),
                        rs.getInt("userID"),
                        rs.getString("occupation"),
                        rs.getString("maritalStatus"),
                        rs.getBoolean("parentalStatus")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    public UserProfile updateOccupation(UserProfile userProfile, String newOccupation) {
        String sql = "UPDATE user_profile SET occupation = ? WHERE profileID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newOccupation);
            stmt.setInt(2, userProfile.getProfileID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                userProfile.setOccupation(newOccupation);
                return userProfile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserProfile delete(UserProfile userProfile) {
        String sql = "DELETE FROM user_profile WHERE profileID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userProfile.getProfileID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return userProfile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
