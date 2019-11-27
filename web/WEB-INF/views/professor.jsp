<%@page import="dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../jspf/cmeta.jspf" %>
        <title>Professor</title>
    </head>
    <body>
        <%
            User usr = (User) request.getSession().getAttribute("USER");
        %>
        <h1>Welcome Professor! <%= usr.getFullName()%></h1>
        <a href="<%=request.getContextPath() + "/auth/sign-out"%>">Sing-out</a>
    </body>
</html>