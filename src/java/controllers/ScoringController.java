package controllers;

import dtos.Scoring;
import dtos.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ScoringService;

@WebServlet(name = "scoring", urlPatterns = {"/scoring/*"})
public class ScoringController extends HttpServlet {

    private final ScoringService service = new ScoringService();

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
        User userSession = (User) request.getSession().getAttribute("USER");

        String json;

        Map<String, String> result = new HashMap<>();

        switch (action) {
            case "save":
                String course = request.getParameter("course");
                int clarity = Integer.parseInt(request.getParameter("clarity"));
                int help = Integer.parseInt(request.getParameter("help"));
                int facility = Integer.parseInt(request.getParameter("facility"));
                boolean assistance = Boolean.parseBoolean(request.getParameter("assistance"));
                boolean recommend = Boolean.parseBoolean(request.getParameter("recommend"));
                String comment = request.getParameter("comment");
                long professorId = Long.parseLong(request.getParameter("professor_id"));
                String studentScore = request.getParameter("student_score");

                Scoring scoring = new Scoring();
                scoring.setClarity(clarity);
                scoring.setHelp(help);
                scoring.setFacility(facility);
                scoring.setAssistance(assistance);
                scoring.setRecommed(recommend);
                scoring.setCourse(course);
                scoring.setComment(comment);
                scoring.setProfessorId(professorId);
                scoring.setUserId(userSession.getId());
                scoring.setStudentScore(studentScore);

                if (service.create(scoring, result) != null) {
                    request.getSession().setAttribute("success", "Scoring added!");
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                }
                response.sendRedirect(request.getContextPath().concat("/student/"));
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
