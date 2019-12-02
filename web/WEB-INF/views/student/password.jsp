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
        <title>Reset Password</title>
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
                            <li class="link-item">
                                <a href="<%=request.getContextPath()%>/student/profile" title="profile">Profile</a>
                            </li>
                            <li class="link-item  active-link-item">
                                <a href="<%=request.getContextPath()%>/student/password" title="password">Reset password</a>
                            </li>
                        </ul>
                    </div>
                    <div class="profile-container">
                        <div class="">
                            <form id="frm-password" class="frm-register width-50-percent" action="<%=request.getContextPath()%>/account/reset-password?profile=student" method="POST">
                                <fieldset>
                                    <legend>Reset password:</legend>
                                    <div class="input-group">
                                        <label class="input-label" for="current_password">Current password:</label>
                                        <input class="input-form" type="password" id="current_password" name="current_password" placeholder="" autocomplete="off" required=""/>
                                    </div>
                                    <div class="input-group">
                                        <label class="input-label" for="new_password">New password:</label>
                                        <input class="input-form" type="password" id="new_password" name="new_password" placeholder="" autocomplete="off" required=""/>
                                    </div>
                                    <div class="input-group">
                                        <label class="input-label" for="confirm_password">Confirm password:</label>
                                        <input class="input-form" type="password" id="confirm_password" name="confirm_password"  placeholder="" autocomplete="off" required=""/>
                                    </div> 
                                    <div class="btn-group">
                                        <input class="btn-form width-100-percent" type="submit" value="Reset password">
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