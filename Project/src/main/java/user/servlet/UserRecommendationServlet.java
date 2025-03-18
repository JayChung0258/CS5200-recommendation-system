package user.servlet;

import user.service.UserRecommendationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recommendations")
public class UserRecommendationServlet extends HttpServlet {
    private UserRecommendationService recommendationService;

    @Override
    public void init() throws ServletException {
        recommendationService = new UserRecommendationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdParam = request.getParameter("userID");
        if (userIdParam == null || userIdParam.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please enter a valid User ID.");
            request.getRequestDispatcher("/Recommendations.jsp").forward(request, response);
            return;
        }

        try {
            int userID = Integer.parseInt(userIdParam);
            List<String> recommendations = recommendationService.recommendProducts(userID);
            request.setAttribute("recommendations", recommendations);
            request.setAttribute("userID", userID);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID format.");
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error occurred.");
            e.printStackTrace();
        }

        request.getRequestDispatcher("/Recommendations.jsp").forward(request, response);
    }
}
