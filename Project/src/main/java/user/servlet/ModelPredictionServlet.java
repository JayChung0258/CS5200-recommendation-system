package user.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ModelPredictionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // 1. 获取输入参数
        String income = request.getParameter("income");
        String entertainment = request.getParameter("entertainment");
        String social = request.getParameter("social");
        String video = request.getParameter("video");
        String message = request.getParameter("message");
        String gaming = request.getParameter("gaming");
        String communities = request.getParameter("communities");
        String expenditure = request.getParameter("expenditure");
        String screen = request.getParameter("screen");

        // 2. 构造 JSON 字符串
        String jsonInput = String.format("""
            {
                "Monthly Income (USD)": %s,
                "Daily Entertainment Time (hrs)": %s,
                "Daily Social Media Time (hrs)": %s,
                "Daily Video Content Time (hrs)": %s,
                "Daily Messaging Time (hrs)": %s,
                "Daily Gaming Time (hrs)": %s,
                "Time Spent in Online Communities (hrs)": %s,
                "Monthly Expenditure on Entertainment (USD)": %s,
                "Screen Time (hrs)": %s
            }
            """, income, entertainment, social, video, message, gaming, communities, expenditure, screen);

        // 3. 调用 FastAPI 服务
        URL url = new URL("http://localhost:8000/predict");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonInput.getBytes("utf-8"));
        }

        // 4. 读取预测结果
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
        }

        response.getWriter().write(result.toString());
    }
}
