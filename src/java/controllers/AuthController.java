package controllers;

import dtos.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AuthService;

@WebServlet(name = "auth", urlPatterns = {"/auth/*"})
public class AuthController extends HttpServlet {

    private final AuthService authService = new AuthService();

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> result = new HashMap<>();

        switch (action) {
            case "sign-in":
                User usr = authService.authenticate(username, password, result);
                if (usr == null) {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                    response.sendRedirect(request.getContextPath().concat("/sign-in.jsp"));
                } else {
                    request.getSession().setAttribute("USER", usr);
                    response.sendRedirect(request.getContextPath().concat("/student/"));
                }
                break;
            case "sign-out":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath().concat("/sign-in.jsp"));
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
