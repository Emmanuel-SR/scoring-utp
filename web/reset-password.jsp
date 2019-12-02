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
            <form id="frm-password" class="frm-register width-50-percent" action="<%=request.getContextPath()%>/account/recover-password" method="POST">
                <fieldset>
                    <legend>Reset password:</legend>
                    <div class="input-group">
                        <label class="input-label" for="new_password">New password:</label>
                        <input class="input-form" type="password" id="new_password" name="new_password" placeholder="Enter username here." autocomplete="off" required=""/>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="confirm_password">Confirm password:</label>
                        <input class="input-form" type="password" id="confirm_password" name="confirm_password"  placeholder="Enter username here." autocomplete="off" required=""/>
                    </div> 
                    <div class="btn-group">
                         <a href="<%=request.getContextPath()%>/sign-in.jsp" id="btn-cancel" class="btn-cancel width-100-percent">Cancel</a>
                        <input class="btn-form width-100-percent" type="submit" value="Reset password">
                    </div>
                </fieldset>
            </form>
        </main>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
             <script>
            document.getElementById("frm-password").addEventListener("submit", function (e) {
                if (document.getElementById("new_password").value !== document.getElementById("confirm_password").value) {
                    e.preventDefault();
                    alert("Passwords don't match.");
                    return false;
                }
            }, true);
        </script>
    </body>
</html>