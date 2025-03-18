package user.servlet;

import user.dal.LifestyleUsageDao;
import user.model.LifestyleUsage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/lifestyle")
public class LifestyleUsageServlet extends HttpServlet {
    private LifestyleUsageDao lifestyleDao;

    @Override
    public void init() throws ServletException {
        lifestyleDao = LifestyleUsageDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<LifestyleUsage> lifestyleList = lifestyleDao.getAllLifestyleUsageRecords();
            request.setAttribute("lifestyleList", lifestyleList);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error retrieving lifestyle data: " + e.getMessage());
        }
        request.getRequestDispatcher("/Lifestyle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("errorMessage", "Invalid action.");
            request.getRequestDispatcher("/Lifestyle.jsp").forward(request, response);
            return;
        }

        try {
            switch (action) {
                case "add":
                    addLifestyleUsage(request);
                    break;
                case "update":
                    updateLifestyleUsage(request);
                    break;
                case "delete":
                    deleteLifestyleUsage(request);
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while processing the request: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/lifestyle");
    }

    private void addLifestyleUsage(HttpServletRequest request) throws Exception {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            BigDecimal totalScreenTime = getBigDecimalParam(request, "totalScreenTime");
            BigDecimal averageSleepTime = getBigDecimalParam(request, "averageSleepTime");
            BigDecimal physicalActivityTime = getBigDecimalParam(request, "physicalActivityTime");
            BigDecimal readingTime = getBigDecimalParam(request, "readingTime");
            BigDecimal workStudyTime = getBigDecimalParam(request, "workStudyTime");
            BigDecimal timeInOnlineCommunities = getBigDecimalParam(request, "timeSpentInOnlineCommunities");
            BigDecimal timeOnEducationalPlatforms = getBigDecimalParam(request, "timeOnEducationalPlatforms");
            BigDecimal newsConsumptionTime = getBigDecimalParam(request, "newsConsumptionTime");
            int adInteractionCount = Integer.parseInt(request.getParameter("adInteractionCount"));

            lifestyleDao.create(new LifestyleUsage(
                    userID, totalScreenTime, averageSleepTime, physicalActivityTime, 
                    readingTime, workStudyTime, timeInOnlineCommunities, 
                    timeOnEducationalPlatforms, newsConsumptionTime, adInteractionCount));
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input format. Please ensure all fields are correctly filled.");
        }
    }

    private void updateLifestyleUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));
            BigDecimal newSleepTime = new BigDecimal(request.getParameter("averageSleepTime"));

            LifestyleUsage lifestyleUsage = lifestyleDao.getLifestyleUsageById(usageID);
            if (lifestyleUsage != null) {
                lifestyleDao.updateAverageSleepTime(lifestyleUsage, newSleepTime);
            } else {
                throw new SQLException("No lifestyle usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }

    private void deleteLifestyleUsage(HttpServletRequest request) throws SQLException {
        try {
            int usageID = Integer.parseInt(request.getParameter("usageID"));

            LifestyleUsage lifestyleUsage = lifestyleDao.getLifestyleUsageById(usageID);
            if (lifestyleUsage != null) {
                lifestyleDao.delete(lifestyleUsage);
            } else {
                throw new SQLException("No lifestyle usage found for usageID: " + usageID);
            }
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid numeric input", e);
        }
    }


    private BigDecimal getBigDecimalParam(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        return (paramValue != null && !paramValue.trim().isEmpty()) ? new BigDecimal(paramValue) : BigDecimal.ZERO;
    }
}
