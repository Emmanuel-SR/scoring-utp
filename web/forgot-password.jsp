<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <link href="RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Forgot Password</title>
    </head>
    <body class="banner banner-utp">
        <main class="flex-container flex-col flex-center">
            <h2>what is your email address?</h2>
            <form class="email-box" action="<%=request.getContextPath()%>/account/forgot-password">
                <input class="input-form" type="email" id="email" name="email"  placeholder="Enter yout email here." required=""/>
                <button class="btn-form">Enviar</button>
            </form>
        </main>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
    </body>
</html>