package user.dal;

import user.model.UserIdentity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserIdentityDao {
    protected ConnectionManager connectionManager;

    private static UserIdentityDao instance = null;
    protected UserIdentityDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserIdentityDao getInstance() {
        if (instance == null) {
            instance = new UserIdentityDao();
        }
        return instance;
    }

    public UserIdentity create(UserIdentity userIdentity) {
        String sql = "INSERT INTO user_identity (userID, age, gender, country) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userIdentity.getUserID());
            stmt.setInt(2, userIdentity.getAge());
            stmt.setString(3, userIdentity.getGender());
            stmt.setString(4, userIdentity.getCountry());
            stmt.executeUpdate();
            return userIdentity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserIdentity getUserById(int userID) {
        String sql = "SELECT * FROM user_identity WHERE userID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserIdentity(
                        rs.getInt("userID"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("country")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserIdentity> getAllUsers() {
        List<UserIdentity> users = new ArrayList<>();
        String sql = "SELECT * FROM user_identity";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new UserIdentity(
                        rs.getInt("userID"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("country")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserIdentity updateUserAge(UserIdentity user, int newAge) {
        String sql = "UPDATE user_identity SET age = ? WHERE userID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newAge);
            stmt.setInt(2, user.getUserID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                user.setAge(newAge);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserIdentity delete(UserIdentity user) {
        String sql = "DELETE FROM user_identity WHERE userID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
