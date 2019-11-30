<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <title>Sign-In</title>
    </head>
    <body>
        <form id="frm-signin" action="<%=request.getContextPath()%>/auth/sign-in" method="POST">
            <input type="text" placeholder="Username" name="username" title="" required>
            <input type="password" placeholder="Password" name="password" title="" required>
            <input type="submit" value="sign-in">
        </form>
    </body>
</html>