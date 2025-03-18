package user.dal;

import user.model.UserFinance;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserFinanceDao {
    protected ConnectionManager connectionManager;

    private static UserFinanceDao instance = null;
    protected UserFinanceDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserFinanceDao getInstance() {
        if (instance == null) {
            instance = new UserFinanceDao();
        }
        return instance;
    }

    public UserFinance create(UserFinance userFinance) {
        String sql = "INSERT INTO user_finance (financeID, userID, monthlyIncomeUSD, monthlyExpenditureUSD) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userFinance.getFinanceID());
            stmt.setInt(2, userFinance.getUserID());
            stmt.setBigDecimal(3, userFinance.getMonthlyIncomeUSD());
            stmt.setBigDecimal(4, userFinance.getMonthlyExpenditureUSD());
            stmt.executeUpdate();
            return userFinance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserFinance getUserFinanceById(int financeID) {
        String sql = "SELECT * FROM user_finance WHERE financeID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, financeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserFinance(
                        rs.getInt("financeID"),
                        rs.getInt("userID"),
                        rs.getBigDecimal("monthlyIncomeUSD"),
                        rs.getBigDecimal("monthlyExpenditureUSD")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserFinance> getAllUserFinances() {
        List<UserFinance> finances = new ArrayList<>();
        String sql = "SELECT * FROM user_finance";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                finances.add(new UserFinance(
                        rs.getInt("financeID"),
                        rs.getInt("userID"),
                        rs.getBigDecimal("monthlyIncomeUSD"),
                        rs.getBigDecimal("monthlyExpenditureUSD")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finances;
    }

    public UserFinance updateMonthlyIncome(UserFinance userFinance, BigDecimal newIncome) {
        String sql = "UPDATE user_finance SET monthlyIncomeUSD = ? WHERE financeID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newIncome);
            stmt.setInt(2, userFinance.getFinanceID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                userFinance.setMonthlyIncomeUSD(newIncome);
                return userFinance;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserFinance delete(UserFinance userFinance) {
        String sql = "DELETE FROM user_finance WHERE financeID = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userFinance.getFinanceID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return userFinance;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
