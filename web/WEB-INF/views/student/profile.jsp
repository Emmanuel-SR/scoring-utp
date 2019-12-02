<%
    User usr = (User) request.getSession().getAttribute("USER");
%>
<%@page import="dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../jspf/cmeta.jspf" %>
        <link href="<%=request.getContextPath()%>/RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Profile</title>
    </head>
    <body>
        <main class="flex-container flex-row">
            <nav class="side-bar">
                <ul class="side-menu">
                    <li class="side-logo">
                        <a href="${pageContext.request.contextPath}/student/" title="config">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/utp-logo.png" alt=""/>
                        </a>
                    </li>
                    <li class="side-item active-side-item">
                        <a href="<%=request.getContextPath()%>/student/profile" title="profile">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/profile-32.png" alt="">
                        </a>
                    </li>
                </ul>
                <ul class="side-menu">
                    <li class="side-item">
                        <a href="<%=request.getContextPath()%>/auth/sign-out" title="sign-out">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/account-logout-32.png" alt="">
                        </a>
                    </li>
                </ul>      
            </nav>

            <div class="side-content">
                <div class="nav-toggle">
                    <a href="#" id="btn-toggle" class="btn-toggle">
                        <img src="${pageContext.request.contextPath}/RESOURCES/imgs/menu-24.png" alt="">
                    </a>
                    <h4 class="header-link"><%= usr.getFullName()%></h4>
                </div>
                <div class="row-container">
                    <div class="profile-menu">
                        <ul id="toggle-menu" class="side-menu">
                            <li class="link-item active-link-item">
                                <a href="<%=request.getContextPath()%>/student/profile" title="profile">Profile</a>
                            </li>
                            <li class="link-item">
                                <a href="<%=request.getContextPath()%>/student/password" title="password">Reset password</a>
                            </li>
                        </ul>
                    </div>
                    <div class="profile-container">
                        <div class="inline-flex">
                            <img src="${pageContext.request.contextPath}/RESOURCES/imgs/student-32.png" alt="">
                            <h2><%= usr.getFullName()%></h2>
                            <a id="btn-edit-profile" href="#" title="edit"><img src="${pageContext.request.contextPath}/RESOURCES/imgs/edit-16.png" alt=""></a>
                        </div>
                        <div id="profile-content">
                            <div class="text-group">
                                <h3>Username</h3>
                                <p><%= usr.getUsername()%></p>
                            </div>
                            <div class="text-group">
                                <h3>Email</h3>
                                <p><%= usr.getEmail()%></p>
                            </div>
                        </div>
                        <div id="profile-edit">
                            <form class="frm-register" action="<%=request.getContextPath()%>/account/update?profile=student" method="POST">
                                <fieldset>
                                    <legend>Account Information:</legend>
                                    <div class="input-group">
                                        <label class="input-label" for="username">Username:</label>
                                        <input class="input-form" type="text" id="username" name="username"  value="<%= usr.getUsername()%>" placeholder="Enter username here." required=""/>
                                    </div>
                                    <div class="input-group">
                                        <label class="input-label" for="email">Institutional Email:</label>
                                        <input class="input-form" type="email" id="email" name="email"  value="<%= usr.getEmail()%>" placeholder="@utp.edu.pe" required=""/>
                                    </div>
                                    <div class="input-group">
                                        <label class="input-label" for="firstname">First Name:</label>
                                        <input class="input-form" type="text" id="firstname" name="firstname"  value="<%= usr.getFirstname()%>" placeholder="Enter your first name here." required=""/>
                                    </div>
                                    <div class="input-group">
                                        <label class="input-label" for="lastname">Last Name:</label>
                                        <input class="input-form" type="text" id="lastname" name="lastname"   value="<%= usr.getLastname()%>" placeholder="Enter your last name here." required=""/>
                                    </div>
                                    <div class="btn-group">
                                        <a href="#" id="btn-cancel" class="btn-cancel width-100-percent">Cancel</a>
                                        <input class="btn-form width-100-percent" type="submit" value="Save profile">
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/scripts.js" type="text/javascript"></script>
        <%@include file="../../jspf/cscript_alert.jspf" %>
        <script>
            window.addEventListener("load", (function () {

                var isDisplay = true;

                var toggle = function (e) {
                    e.preventDefault();
                    var content = document.getElementById("profile-content");
                    var edit = document.getElementById("profile-edit");
                    if (isDisplay) {
                        content.style.display = "none";
                        edit.style.display = "flex";
                    } else {
                        content.style.display = "block";
                        edit.style.display = "none";
                    }
                    isDisplay = !isDisplay;
                };
                document.getElementById("btn-cancel").addEventListener("click", toggle, true);
                document.getElementById("btn-edit-profile").addEventListener("click", toggle, true);
            }()), true);
        </script>
    </body>
</html>