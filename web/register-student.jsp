<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <link href="RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Register</title>
    </head>
    <body class="banner banner-student">
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
                        <label class="input-label" for="lastname">Security Question:</label>
                        <select id="security-question" class="input-form" name="security_question_id" required="">
                            <option>--choose--</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label class="input-label" for="answer">Answer:</label>
                        <input class="input-form" type="text" id="answer" name="answer"  placeholder="Enter the question answer here." required=""/>
                    </div>
                    <input class="btn-form width-100-percent" type="submit" value="Register Now">
                </fieldset>
            </form>
            <div class="footer-form">
                Already have an account? <a href="<%=request.getContextPath()%>/sign-in.jsp" class="link-alt">Sign in</a>
            </div>
        </main>
        <%@include file="WEB-INF/jspf/cscript_alert.jspf" %>
        <script>
            window.addEventListener("load", function (e) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        var questions = JSON.parse(this.responseText);
                        questions.forEach(function (e) {
                            var opt = document.createElement("option");
                            opt.value = e.id;
                            opt.innerHTML = e.text;
                            document.getElementById("security-question").appendChild(opt);
                        });
                    }
                };
                xhttp.open("GET", "<%=request.getContextPath()%>/security-question/all", false);
                xhttp.send();
            }, true);
        </script>
    </body>
</html>