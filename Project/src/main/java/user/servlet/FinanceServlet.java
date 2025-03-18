package user.servlet;

import user.dal.UserFinanceDao;
import user.model.UserFinance;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/finance")
public class FinanceServlet extends HttpServlet {
    private UserFinanceDao financeDao;

    @Override
    public void init() throws ServletException {
        financeDao = UserFinanceDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserFinance> finances = financeDao.getAllUserFinances();
        request.setAttribute("finances", finances);
        request.getRequestDispatcher("/Finance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/finance");
            return;
        }
        try {
            switch (action) {
                case "add":
                    addFinance(request);
                    break;
                case "update":
                    updateFinance(request);
                    break;
                case "delete":
                    deleteFinance(request);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/finance");
    }

    private void addFinance(HttpServletRequest request) throws Exception {
        int userID = Integer.parseInt(request.getParameter("userID"));
        BigDecimal income = new BigDecimal(request.getParameter("monthlyIncome"));
        BigDecimal expense = new BigDecimal(request.getParameter("monthlyExpense"));
        financeDao.create(new UserFinance(userID, income, expense));
    }

    private void updateFinance(HttpServletRequest request) throws Exception {
        int financeID = Integer.parseInt(request.getParameter("financeID"));
        BigDecimal newIncome = new BigDecimal(request.getParameter("monthlyIncome"));
        UserFinance finance = new UserFinance(financeID);
        financeDao.updateMonthlyIncome(finance, newIncome);
    }

    private void deleteFinance(HttpServletRequest request) throws Exception {
        int financeID = Integer.parseInt(request.getParameter("financeID"));
        UserFinance finance = new UserFinance(financeID);
        financeDao.delete(finance);
    }
}