<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <link href="RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Sign-In</title>
    </head>
    <body class="banner banner-utp">
        <main class="flex-container flex-col flex-center">
            <form id="frm-signin" class="frm-signin" action="<%=request.getContextPath()%>/auth/sign-in" method="POST">
                <div class="frm-logo">
                    <img src="${pageContext.request.contextPath}/RESOURCES/imgs/utp-logo.png" alt="">
                </div>
                <div class="input-signin-group">
                    <img src="${pageContext.request.contextPath}/RESOURCES/imgs/user-icon-24.png"/>
                    <input class="input-form" type="text" placeholder="Username" name="username" title="" required>
                </div>
                <div class="input-signin-group">
                    <img src="${pageContext.request.contextPath}/RESOURCES/imgs/key-icon-24.png"/>
                    <input class="input-form" type="password" placeholder="Password" name="password" title="" required>
                </div>
                <input class="btn-form width-100-percent" type="submit" value="SIGN IN">
            </form>
            <div class="footer-signin">
                <a href="<%=request.getContextPath()%>/forgot-password.jsp" class="link">Forgot Password?</a>
                <a href="<%=request.getContextPath()%>/register-student.jsp" class="link">Don´t have an account?</a>
            </div>
        </main>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
    </body>
</html>