package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "student", urlPatterns = {"/student/*"})
public class StudentController extends HttpServlet {

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

        switch (action) {
            case "":
                request.getRequestDispatcher("/WEB-INF/views/student/index.jsp").forward(request, response);
                break;
            case "profile":
                request.getRequestDispatcher("/WEB-INF/views/student/profile.jsp").forward(request, response);
                break;
            case "password":
                request.getRequestDispatcher("/WEB-INF/views/student/password.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
