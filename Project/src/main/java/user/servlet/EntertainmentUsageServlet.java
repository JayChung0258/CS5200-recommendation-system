package user.servlet;

import user.dal.EntertainmentUsageDao;
import user.model.EntertainmentUsage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/entertainment")
public class EntertainmentUsageServlet extends HttpServlet {
    private EntertainmentUsageDao entertainmentDao;

    @Override
    public void init() throws ServletException {
        entertainmentDao = EntertainmentUsageDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<EntertainmentUsage> entertainmentList = entertainmentDao.getAllEntertainmentUsageRecords();
            request.setAttribute("entertainmentList", entertainmentList);
            request.getRequestDispatcher("/Entertainment.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving entertainment usage records", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/entertainment");
            return;
        }

        try {
            switch (action) {
                case "add":
                    addEntertainmentUsage(request);
                    break;
                case "update":
                    updateEntertainmentUsage(request);
                    break;
                case "delete":
                    deleteEntertainmentUsage(request);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing action: " + action, e);
        }

        response.sendRedirect(request.getContextPath() + "/entertainment");
    }

    private void addEntertainmentUsage(HttpServletRequest request) throws SQLException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            BigDecimal totalScreenTime = new BigDecimal(request.getParameter("totalScreenTime"));
            BigDecimal dailyEntertainmentTime = new BigDecimal(request.getParameter("dailyEntertainmentTime"));
            BigDecimal dailyVideoTime = new BigDecimal(request.getParameter("dailyVideoContentTime"));
            BigDecimal dailyGamingTime = new BigDecimal(request.getParameter("dailyGamingTime"));
            BigDecimal dailyMusicTime = new BigDecimal(request.getParameter("dailyMusicListeningTime"));
            int subscriptionPlatforms = Integer.parseInt(request.getParameter("subscriptionPlatforms"));
            String preferredContent = request.getParameter("preferredContentType");
            String preferredPlatform = request.getParameter("preferredEntertainmentPlatform");

            EntertainmentUsage usage = new EntertainmentUsage(
                userID, totalScreenTime, dailyEntertainmentTime, dailyVideoTime, 
                dailyGamingTime, dailyMusicTime, subscriptionPlatforms, 
                preferredContent, preferredPlatform
            );
            entertainmentDao.create(usage);

        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }

    private void updateEntertainmentUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));
            BigDecimal newDailyTime = new BigDecimal(request.getParameter("dailyEntertainmentTime"));

            EntertainmentUsage entertainmentUsage = entertainmentDao.getEntertainmentUsageById(usageID);
            if (entertainmentUsage != null) {
                entertainmentDao.updateDailyEntertainmentTime(entertainmentUsage, newDailyTime);
            } else {
                throw new SQLException("No entertainment usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }

    private void deleteEntertainmentUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));

            EntertainmentUsage entertainmentUsage = entertainmentDao.getEntertainmentUsageById(usageID);
            if (entertainmentUsage != null) {
                entertainmentDao.delete(entertainmentUsage);
            } else {
                throw new SQLException("No entertainment usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }
}
