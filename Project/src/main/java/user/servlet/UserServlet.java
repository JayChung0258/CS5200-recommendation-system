package user.servlet;

import user.dal.UserIdentityDao;
import user.model.UserIdentity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserIdentityDao userIdentityDao;

    @Override
    public void init() throws ServletException {
        userIdentityDao = UserIdentityDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserIdentity> users = userIdentityDao.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/Users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/users");
            return;
        }

        switch (action) {
            case "add":
                addUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.sendRedirect("/Project/users");
                break;
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String country = request.getParameter("country");
            UserIdentity newUser = new UserIdentity(age, gender, country);
            userIdentityDao.create(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/users");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            int age = Integer.parseInt(request.getParameter("age"));
            UserIdentity user = new UserIdentity(userID);
            userIdentityDao.updateUserAge(user, age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserIdentity user = new UserIdentity(userID);
            userIdentityDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/users");
    }
}