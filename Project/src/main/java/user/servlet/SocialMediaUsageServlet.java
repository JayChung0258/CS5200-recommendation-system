package user.servlet;

import user.dal.SocialMediaUsageDao;
import user.model.SocialMediaUsage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/socialmedia")
public class SocialMediaUsageServlet extends HttpServlet {
    private SocialMediaUsageDao socialMediaDao;

    @Override
    public void init() throws ServletException {
        socialMediaDao = SocialMediaUsageDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<SocialMediaUsage> socialMediaList = socialMediaDao.getAllSocialMediaUsageRecords();
            request.setAttribute("socialMediaList", socialMediaList);
            request.getRequestDispatcher("/Socialmedia.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving social media usage records", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/socialmedia");
            return;
        }

        try {
            switch (action) {
                case "add":
                    addSocialMediaUsage(request);
                    break;
                case "update":
                    updateSocialMediaUsage(request);
                    break;
                case "delete":
                    deleteSocialMediaUsage(request);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing action: " + action, e);
        }

        response.sendRedirect("/Project/socialmedia");
    }

    private void addSocialMediaUsage(HttpServletRequest request) throws SQLException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            BigDecimal totalScreenTime = new BigDecimal(request.getParameter("totalScreenTime"));
            BigDecimal dailySocialMediaTime = new BigDecimal(request.getParameter("dailySocialMediaTime"));
            BigDecimal dailyMessagingTime = new BigDecimal(request.getParameter("dailyMessagingTime"));
            int platformsUsed = Integer.parseInt(request.getParameter("platformsUsed"));
            String primaryPlatform = request.getParameter("primaryPlatform");
            String primaryGoal = request.getParameter("primaryGoal");
            int notifications = Integer.parseInt(request.getParameter("notifications"));
            int fatigueLevel = Integer.parseInt(request.getParameter("fatigueLevel"));

            SocialMediaUsage usage = new SocialMediaUsage(
                userID, totalScreenTime, dailySocialMediaTime, dailyMessagingTime, 
                platformsUsed, primaryPlatform, primaryGoal, notifications, fatigueLevel
            );
            socialMediaDao.create(usage);

        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }

    private void updateSocialMediaUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));
            BigDecimal newDailyTime = new BigDecimal(request.getParameter("dailySocialMediaTime"));

            SocialMediaUsage socialMediaUsage = socialMediaDao.getSocialMediaUsageById(usageID);
            if (socialMediaUsage != null) {
                socialMediaDao.updateDailySocialMediaTime(socialMediaUsage, newDailyTime);
            } else {
                throw new SQLException("No social media usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }

    private void deleteSocialMediaUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));

            SocialMediaUsage socialMediaUsage = socialMediaDao.getSocialMediaUsageById(usageID);
            if (socialMediaUsage != null) {
                socialMediaDao.delete(socialMediaUsage);
            } else {
                throw new SQLException("No social media usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }
}
