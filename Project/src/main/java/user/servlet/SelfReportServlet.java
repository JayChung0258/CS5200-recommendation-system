package user.servlet;

import user.dal.UserSelfReportDao;
import user.model.UserSelfReport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selfreport")
public class SelfReportServlet extends HttpServlet {
    private UserSelfReportDao selfReportDao;

    @Override
    public void init() throws ServletException {
        selfReportDao = UserSelfReportDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserSelfReport> selfReports = selfReportDao.getAllUserSelfReports();
        request.setAttribute("selfReports", selfReports);
        request.getRequestDispatcher("/Selfreport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/selfreport");
            return;
        }
        try {
            switch (action) {
                case "add":
                    addSelfReport(request);
                    break;
                case "update":
                    updateSelfReport(request);
                    break;
                case "delete":
                    deleteSelfReport(request);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/selfreport");
    }

    private void addSelfReport(HttpServletRequest request) throws Exception {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int sleepQuality = Integer.parseInt(request.getParameter("sleepQuality"));
        int socialIsolationFeeling = Integer.parseInt(request.getParameter("socialIsolationFeeling"));
        selfReportDao.create(new UserSelfReport(userID, sleepQuality, socialIsolationFeeling));
    }

    private void updateSelfReport(HttpServletRequest request) throws Exception {
        int selfReportID = Integer.parseInt(request.getParameter("selfReportID"));
        int newSleepQuality = Integer.parseInt(request.getParameter("sleepQuality"));
        UserSelfReport selfReport = new UserSelfReport(selfReportID);
        selfReportDao.updateSleepQuality(selfReport, newSleepQuality);
    }

    private void deleteSelfReport(HttpServletRequest request) throws Exception {
        int selfReportID = Integer.parseInt(request.getParameter("selfReportID"));
        UserSelfReport selfReport = new UserSelfReport(selfReportID);
        selfReportDao.delete(selfReport);
    }
}