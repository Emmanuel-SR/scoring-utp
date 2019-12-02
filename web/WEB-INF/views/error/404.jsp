<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../jspf/cmeta.jspf" %>
        <link href="<%=request.getContextPath()%>/RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>404 - Not found</title>
    </head>
    <body>
        <main class="flex-container flex-col flex-center">
            <span class="http-error">404 - Not found</span>
            <h2>It looks like you´re lost...</h2>
        </main>
    </body>
</html>