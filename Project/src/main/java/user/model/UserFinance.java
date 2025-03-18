package user.model;

import java.math.BigDecimal;

public class UserFinance {
    protected int financeID;
    protected int userID;
    protected BigDecimal monthlyIncomeUSD;
    protected BigDecimal monthlyExpenditureUSD;

    // Constructor with all fields
    public UserFinance(int financeID, int userID, BigDecimal monthlyIncomeUSD, BigDecimal monthlyExpenditureUSD) {
        this.financeID = financeID;
        this.userID = userID;
        this.monthlyIncomeUSD = monthlyIncomeUSD;
        this.monthlyExpenditureUSD = monthlyExpenditureUSD;
    }

    // Constructor without financeID (for insertion cases)
    public UserFinance(int userID, BigDecimal monthlyIncomeUSD, BigDecimal monthlyExpenditureUSD) {
        this.userID = userID;
        this.monthlyIncomeUSD = monthlyIncomeUSD;
        this.monthlyExpenditureUSD = monthlyExpenditureUSD;
    }

    // Constructor for lookup by financeID
    public UserFinance(int financeID) {
        this.financeID = financeID;
    }

    // Getters and Setters
    public int getFinanceID() {
        return financeID;
    }

    public void setFinanceID(int financeID) {
        this.financeID = financeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public BigDecimal getMonthlyIncomeUSD() {
        return monthlyIncomeUSD;
    }

    public void setMonthlyIncomeUSD(BigDecimal monthlyIncomeUSD) {
        this.monthlyIncomeUSD = monthlyIncomeUSD;
    }

    public BigDecimal getMonthlyExpenditureUSD() {
        return monthlyExpenditureUSD;
    }

    public void setMonthlyExpenditureUSD(BigDecimal monthlyExpenditureUSD) {
        this.monthlyExpenditureUSD = monthlyExpenditureUSD;
    }
}
