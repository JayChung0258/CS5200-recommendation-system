package user.servlet;

import user.dal.UserTechAwarenessDao;
import user.model.UserTechAwareness;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tech")
public class TechAwarenessServlet extends HttpServlet {
    private UserTechAwarenessDao techDao;

    @Override
    public void init() throws ServletException {
        techDao = UserTechAwarenessDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserTechAwareness> techAwarenessList = techDao.getAllUserTechAwareness();
        request.setAttribute("techAwarenessList", techAwarenessList);
        request.getRequestDispatcher("/Tech.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/tech");
            return;
        }
        try {
            switch (action) {
                case "add":
                    addTechAwareness(request);
                    break;
                case "update":
                    updateTechAwareness(request);
                    break;
                case "delete":
                    deleteTechAwareness(request);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/tech");
    }

    private void addTechAwareness(HttpServletRequest request) throws Exception {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int level = Integer.parseInt(request.getParameter("techLevel"));
        String awareness = request.getParameter("digitalAwareness");
        techDao.create(new UserTechAwareness(userID, level, awareness));
    }

    private void updateTechAwareness(HttpServletRequest request) throws Exception {
        int techID = Integer.parseInt(request.getParameter("techID"));
        int newLevel = Integer.parseInt(request.getParameter("techLevel"));
        UserTechAwareness techAwareness = new UserTechAwareness(techID);
        techDao.updateTechSavvinessLevel(techAwareness, newLevel);
    }

    private void deleteTechAwareness(HttpServletRequest request) throws Exception {
        int techID = Integer.parseInt(request.getParameter("techID"));
        UserTechAwareness techAwareness = new UserTechAwareness(techID);
        techDao.delete(techAwareness);
    }
}