package controllers;

import com.google.gson.Gson;
import dtos.SecurityQuestion;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.SecurityQuestionService;

@WebServlet(name = "security-question", urlPatterns = {"/security-question/*"})
public class SecurityQuestionController extends HttpServlet {

    private final SecurityQuestionService service = new SecurityQuestionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = "";

        if (request.getPathInfo() != null) {
            action = request.getPathInfo().substring(1);
        }

        String json;

        Map<String, String> result = new HashMap<>();

        switch (action) {
            case "all":
                List<SecurityQuestion> questions = service.findAll(result);
                json = new Gson().toJson(questions);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
