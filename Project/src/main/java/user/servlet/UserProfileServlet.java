package user.servlet;

import user.dal.UserProfileDao;
import user.model.UserProfile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profiles")
public class UserProfileServlet extends HttpServlet {
    private UserProfileDao userProfileDao;

    @Override
    public void init() throws ServletException {
        userProfileDao = UserProfileDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UserProfile> profileList = userProfileDao.getAllUserProfiles();
            request.setAttribute("profileList", profileList);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error retrieving profile data.");
            e.printStackTrace();
        }
        request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/profiles");
            return;
        }
        try {
            switch (action) {
                case "add":
                    addUserProfile(request);
                    break;
                case "update":
                    updateUserProfile(request);
                    break;
                case "delete":
                    deleteUserProfile(request);
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid action.");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while processing the request.");
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/profiles");
    }

    private void addUserProfile(HttpServletRequest request) throws Exception {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String occupation = request.getParameter("occupation");
        String maritalStatus = request.getParameter("maritalStatus");
        boolean parentalStatus = Boolean.parseBoolean(request.getParameter("parentalStatus"));

        userProfileDao.create(new UserProfile(userID, occupation, maritalStatus, parentalStatus));
    }

    private void updateUserProfile(HttpServletRequest request) throws Exception {
        int profileID = Integer.parseInt(request.getParameter("profileID"));
        String newOccupation = request.getParameter("occupation");

        UserProfile userProfile = userProfileDao.getUserProfileById(profileID);
        if (userProfile != null) {
            userProfileDao.updateOccupation(userProfile, newOccupation);
        } else {
            throw new Exception("No user profile found for profileID: " + profileID);
        }
    }

    private void deleteUserProfile(HttpServletRequest request) throws Exception {
        int profileID = Integer.parseInt(request.getParameter("profileID"));

        UserProfile userProfile = userProfileDao.getUserProfileById(profileID);
        if (userProfile != null) {
            userProfileDao.delete(userProfile);
        } else {
            throw new Exception("No user profile found for profileID: " + profileID);
        }
    }
}
