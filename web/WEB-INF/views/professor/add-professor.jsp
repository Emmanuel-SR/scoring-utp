<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../jspf/cmeta.jspf" %>
        <link href="<%=request.getContextPath()%>/RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Add a course</title>
    </head>
    <body>
        <main class="flex-container flex-row">
            <nav class="side-bar">
                <ul class="side-menu">
                    <li class="side-logo">
                        <a href="<%=request.getContextPath()%>/student/" title="config">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/utp-logo.png" alt=""/>
                        </a>
                    </li>
                    <li class="side-item">
                        <a href="<%=request.getContextPath()%>/student/profile" title="profile">
                            <img id="img-profile" src="${pageContext.request.contextPath}/RESOURCES/imgs/profile-alt-32.png" alt="">
                        </a>
                    </li>
                </ul>
                <ul>
                    <li class="side-item">
                        <a href="<%=request.getContextPath()%>/auth/sign-out" title="sign-out">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/account-logout-32.png" alt="">
                        </a>
                    </li>
                </ul>      
            </nav>
            <div class="side-content flex-box flex-col flex-center">
                <h2>What professor do you want to add?</h2>
                <form class="frm-register" action="<%=request.getContextPath()%>/professor/save" method="POST">
                    <fieldset>
                        <legend>Professor Information:</legend>
                        <div class="input-group">
                            <label class="input-label" for="utp_code">Utp code:</label>
                            <input class="input-form" type="text" id="utp_code" name="utp_code"  placeholder="Enter professor first name here." required=""/>
                        </div>
                        <div class="input-group">
                            <label class="input-label" for="firstname">First Name:</label>
                            <input class="input-form" type="text" id="firstname" name="first_name"  placeholder="Enter professor first name here." required=""/>
                        </div>
                        <div class="input-group">
                            <label class="input-label" for="lastname">Last Name:</label>
                            <input class="input-form" type="text" id="lastname" name="last_name"  placeholder="Enter professor last name here." required=""/>
                        </div>
                        <div class="input-group">
                            <label class="input-label" for="faculty">Faculty/Dep:</label>
                            <input class="input-form" type="text" id="faculty" name="faculty"  placeholder="Enter professor faculty here."/>
                        </div>
                        <div class="flex-box flex-row">
                            <a href="<%=request.getContextPath()%>/student/" id="btn-cancel" class="btn-cancel">Cancel</a>
                            <input class="btn-form width-100-percent" type="submit" value="Add">
                        </div>
                    </fieldset>
                </form>
                <h4 id="title_similiar" style="display:none">Are you looking for this?</h4>
                <ul id="similar_professors">
                </ul>
            </div>
        </main>
        <%@include file="../../jspf/cscript_alert.jspf" %>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/util.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/paginator.js" type="text/javascript"></script>
        <script>

            function ajaxSimiliar(text) {
                var ul = document.getElementById("similar_professors");
                var h4 = document.getElementById("title_similiar");
                ul.innerHTML = "";
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        var professors = JSON.parse(this.responseText);
                        if (professors.length > 0) {
                            h4.style.display = "block";
                            h4.innerHTML = professors.length > 1 ? "Are you looking for these?" : "Are you looking for this?";
                            professors.forEach(function (e) {
                                var li = document.createElement("li");
                                var a = document.createElement("a");
                                a.href = "<%=request.getContextPath()%>/professor/?id=" + e["id"];
                                a.innerHTML = e.firstName +" "+ e.lastName;
                                a.style.textDecoration = "underline";
                                li.appendChild(a);
                                ul.appendChild(li);
                            });
                        } else {
                            h4.style.display = "none";
                        }
                    }
                };
                xhttp.open("GET", "<%=request.getContextPath()%>/professor/findByText?limit=10&text=" + text, true);
                if (text.length > 3) {
                    xhttp.send();
                } else {
                    h4.style.display = "none";
                }
            }
            window.addEventListener("load", function () {

                document.getElementById("firstname").addEventListener("input", debounce(function () {
                    ajaxSimiliar(this.value);
                }, 750), true);
                document.getElementById("lastname").addEventListener("input", debounce(function () {
                    ajaxSimiliar(this.value);
                }, 750), true);
            }, true);
        </script>
    </body>
</html>