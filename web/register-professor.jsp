<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <link href="RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Register</title>
    </head>
    <body class="banner banner-professor">
        <main class="flex-container flex-col flex-center">
            <form class="frm-register" action="<%=request.getContextPath()%>/account/register?profile=student" method="POST">
                <fieldset>
                    <legend>Account Information:</legend>
                    <div class="input-group">
                        <label class="input-label" for="username">Username:</label>
                        <input class="input-form" type="text" id="username" name="username"  placeholder="Enter username here." required=""/>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="password">Password:</label>
                        <input class="input-form" type="password" id="password" name="password"  placeholder="Enter password here." required=""/>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="email">Institutional Email:</label>
                        <input class="input-form" type="email" id="email" name="email"  placeholder="@utp.edu.pe" required=""/>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="firstname">First Name:</label>
                        <input class="input-form" type="text" id="firstname" name="firstname"  placeholder="Enter your first name here." required=""/>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="lastname">Last Name:</label>
                        <input class="input-form" type="text" id="lastname" name="lastname"  placeholder="Enter your last name here." required=""/>
                    </div>
                    <div class="input-group">
                        <span class="input-label" >Courses:</span>
                        <div id="multiselect_container">
                        </div>
                    </div>
                    <input class="btn-form" type="submit" value="Register Now">
                </fieldset>
            </form>
            <div class="footer-form">
                Already have an account? <a href="<%=request.getContextPath()%>/sign-in.jsp" class="link-alt">Sign in</a>
            </div>
        </main>
        <script src="RESOURCES/js/multiselect.js" type="text/javascript"></script>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
        <script type="text/javascript">
        </script>
    </body>
</html>