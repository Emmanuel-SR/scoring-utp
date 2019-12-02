package controllers;

import com.google.gson.Gson;
import dtos.Professor;
import dtos.ProfessorScoringDetail;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ProfessorService;

@WebServlet(name = "professor", urlPatterns = {"/professor/*"})
public class ProfessorController extends HttpServlet {

    private final ProfessorService service = new ProfessorService();

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
            case "":
                long professorId = Long.parseLong(request.getParameter("id"));
                Professor professor = service.findOne(professorId, result);
                request.setAttribute("professor", professor);
                request.getRequestDispatcher("/WEB-INF/views/professor/profile.jsp").forward(request, response);
                break;
            case "findByText":
                String text = request.getParameter("text");
                int limit = Integer.parseInt(request.getParameter("limit"));
                List<Professor> professors = service.findAllByText(text, limit, result);
                json = new Gson().toJson(professors);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            case "findAllScoringDetailsByText":
                text = request.getParameter("text");
                limit = Integer.parseInt(request.getParameter("limit"));
                List<ProfessorScoringDetail> details = service.findAllScoringDetailsByText(text, limit, result);
                json = new Gson().toJson(details);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            case "save":
                String utpCode = request.getParameter("utp_code");
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String faculty = request.getParameter("faculty");
                professor = new Professor(utpCode, firstName, lastName, faculty);

                if (service.create(professor, result) != null) {
                    request.getSession().setAttribute("success", professor.getFullName() + " added!");
                    response.sendRedirect(request.getContextPath().concat("/student"));
                    break;
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                }
                response.sendRedirect(request.getContextPath().concat("/professor/add"));
                break;
            case "add":
                String text_entered = request.getParameter("text");
                if (text_entered != null) {
                    request.setAttribute("text", text_entered);
                } else {
                    request.setAttribute("text", "");
                }
                request.getRequestDispatcher("/WEB-INF/views/professor/add-professor.jsp").forward(request, response);
                break;
            case "rate":
                professorId = Long.parseLong(request.getParameter("professor_id"));
                professor = service.findOne(professorId, result);
                request.setAttribute("professor", professor);
                request.getRequestDispatcher("/WEB-INF/views/professor/rate.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
