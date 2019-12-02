<%
    String question = (String) request.getSession().getAttribute("question");
    if (question == null) {
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <link href="RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Forgot Password</title>
    </head>
    <body class="">
        <main class="flex-container flex-col flex-center">
            <h2><%=question%></h2>
            <form action="<%=request.getContextPath()%>/account/answer">
                <input class="input-form" type="text" id="email" name="answer"  placeholder="Enter yout answer here." required=""/>
                <button class="btn-form">Enviar</button>
            </form>
        </main>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
    </body>
</html>