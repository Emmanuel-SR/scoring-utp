package filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private HttpServletRequest httpRequest = null;
    private HttpServletResponse httpResponse = null;

    private static final Set<String> LOGIN_REQUIRED_URLS = new HashSet<>(Arrays.asList("/home/",
            "/account/edit", "/student/", "/student/profile", "/professor","/professor/add", "/professor/save", "professor/findByText"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("USER") != null);

        String authURI = httpRequest.getContextPath() + "/auth/sign-in";

        boolean isAuthRequest = httpRequest.getRequestURI().equals(authURI);

        boolean isLoginPage = httpRequest.getRequestURI().endsWith("sign-in.jsp");

        if (isLoggedIn && (isAuthRequest || isLoginPage)) {
            httpResponse.sendRedirect(httpRequest.getContextPath().concat("/student/"));
        } else if (!isLoggedIn && isLoginRequired()) {
            httpResponse.sendRedirect(httpRequest.getContextPath().concat("/sign-in.jsp"));
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isLoginRequired() {
        return LOGIN_REQUIRED_URLS.stream().anyMatch((url)
                -> (httpRequest.getRequestURL().toString().contains(url)));
    }

}
