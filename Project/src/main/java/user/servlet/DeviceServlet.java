package user.servlet;

import user.dal.UserDeviceDao;
import user.model.UserDevice;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/devices")
public class DeviceServlet extends HttpServlet {
    private UserDeviceDao userDeviceDao;

    @Override
    public void init() throws ServletException {
        userDeviceDao = UserDeviceDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDevice> devices = userDeviceDao.getAllUserDevices();
        request.setAttribute("devices", devices);
        request.getRequestDispatcher("/Devices.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project/devices");
            return;
        }

        switch (action) {
            case "add":
                addDevice(request, response);
                break;
            case "update":
                updateDevice(request, response);
                break;
            case "delete":
                deleteDevice(request, response);
                break;
            default:
                response.sendRedirect("/Project/devices");
                break;
        }
    }

    private void addDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String deviceType = request.getParameter("deviceType");
            BigDecimal internetSpeed = new BigDecimal(request.getParameter("internetSpeed"));
            String preferredDevice = request.getParameter("preferredDevice");
            String dataPlan = request.getParameter("dataPlan");
            
            UserDevice newDevice = new UserDevice(userID, deviceType, internetSpeed, preferredDevice, dataPlan);
            userDeviceDao.create(newDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/devices");
    }

    private void updateDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int deviceID = Integer.parseInt(request.getParameter("deviceID"));
            BigDecimal newSpeed = new BigDecimal(request.getParameter("internetSpeed"));
            UserDevice device = new UserDevice(deviceID);
            userDeviceDao.updateInternetSpeed(device, newSpeed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/devices");
    }

    private void deleteDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int deviceID = Integer.parseInt(request.getParameter("deviceID"));
            UserDevice device = new UserDevice(deviceID);
            userDeviceDao.delete(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Project/devices");
    }
}