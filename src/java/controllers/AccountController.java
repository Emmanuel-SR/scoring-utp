package controllers;

import dtos.SecurityQuestion;
import dtos.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.SecurityQuestionService;
import services.UserService;

@WebServlet(name = "account", urlPatterns = {"/account/*"})
public class AccountController extends HttpServlet {

    private final UserService userService = new UserService();

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

        Map<String, String> result = new HashMap<>();

        String username;
        String password;
        String firstname;
        String lastname;
        String email;
        String answer;
        long securityQuestionId;

        User userSession;

        switch (action) {
            case "register":
                username = request.getParameter("username");
                password = request.getParameter("password");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                email = request.getParameter("email");
                answer = request.getParameter("answer");
                securityQuestionId = Long.parseLong(request.getParameter("security_question_id"));

                User usr = new User(username, password, firstname, lastname, email);

                usr.setSecurityQuestionId(securityQuestionId);
                usr.setAnswer(answer);

                if (userService.create(usr, result) != null) {
                    request.getSession().setAttribute("success", "Account created successfully.");
                    request.getRequestDispatcher("/auth/sign-in").forward(request, response);
                    break;
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                    response.sendRedirect(request.getContextPath().concat("/register-student.jsp"));
                    break;
                }
            case "update":
                userSession = (User) request.getSession().getAttribute("USER");

                username = request.getParameter("username");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                email = request.getParameter("email");

                usr = new User(userSession.getId(), username, null, firstname, lastname, email);

                if (userService.update(usr, result) != null) {
                    request.getSession().setAttribute("success", "Account information updated.");
                    usr.setPassword(userSession.getPassword());
                    request.getSession().setAttribute("USER", usr);
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                }
                response.sendRedirect(request.getContextPath().concat("/student/profile"));
                break;
            case "edit":
                request.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(request, response);
                break;
            case "reset-password":
                String newPassword = request.getParameter("new_password");
                String confirmPassword = request.getParameter("confirm_password");
                String currentPassword = request.getParameter("current_password");

                userSession = (User) request.getSession().getAttribute("USER");

                String hashedPassword = util.Hash.hashSHA256(currentPassword);

                if (!userSession.getPassword().equals(hashedPassword)) {
                    request.getSession().setAttribute("warning", "Current password incorrect.");
                    response.sendRedirect(request.getContextPath().concat("/student/password"));
                    break;
                }
                if (!newPassword.equals(confirmPassword)) {
                    request.getSession().setAttribute("warning", "Passwords are different.");
                    response.sendRedirect(request.getContextPath().concat("/student/password"));
                    break;
                }

                if (currentPassword.equals(newPassword)) {
                    request.getSession().setAttribute("warning", "New password must be different.");
                    response.sendRedirect(request.getContextPath().concat("/student/password"));
                    break;
                }

                userSession.setPassword(newPassword);
                if (userService.changePassword(userSession, result) != null) {
                    userSession.setPassword(util.Hash.hashSHA256(newPassword));
                    request.getSession().setAttribute("USER", userSession);
                    request.getSession().setAttribute("success", "Password changed.");
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                }
                response.sendRedirect(request.getContextPath().concat("/student/password"));
                break;
            case "recover-password":
                newPassword = request.getParameter("new_password");
                confirmPassword = request.getParameter("confirm_password");

                User userTemp = (User) request.getSession().getAttribute("user_tmp");

                if (newPassword.equals(confirmPassword)) {

                    userTemp.setPassword(newPassword);

                    if (userService.changePassword(userTemp, result) != null) {
                        request.getSession().setAttribute("success", "Password changed.");
                    } else {
                        request.getSession().setAttribute("warning", result.get("warning"));
                        request.getSession().setAttribute("error", result.get("error"));
                    }
                }
                request.getSession().removeAttribute("user_tmp");
                request.getSession().removeAttribute("question");
                response.sendRedirect(request.getContextPath().concat("/sign-in.jsp"));
                break;
            case "forgot-password":
                email = request.getParameter("email");
                usr = userService.findOneByEmail(email, result);
                if (usr != null) {
                    request.getSession().setAttribute("user_tmp", usr);

                    SecurityQuestionService questionService = new SecurityQuestionService();

                    SecurityQuestion question = questionService.findOne(usr.getSecurityQuestionId(), result);

                    request.getSession().setAttribute("question", question.getText());

                    response.sendRedirect(request.getContextPath().concat("/security-question.jsp"));
                } else {
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                    response.sendRedirect(request.getContextPath().concat("/forgot-password.jsp"));
                }
                break;
            case "answer":
                answer = request.getParameter("answer");
                if (userService.checkSecurityAnswer(answer, result)) {
                    response.sendRedirect(request.getContextPath().concat("/reset-password.jsp"));
                } else {
                    request.getSession().removeAttribute("user_tmp");
                    request.getSession().removeAttribute("question");
                    request.getSession().setAttribute("warning", result.get("warning"));
                    request.getSession().setAttribute("error", result.get("error"));
                    response.sendRedirect(request.getContextPath().concat("/forgot-password.jsp"));
                }
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                break;
        }

    }

}
