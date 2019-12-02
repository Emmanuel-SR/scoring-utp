<%@page import="dtos.Professor"%>
<%
    Professor professor = (Professor) request.getAttribute("professor");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../jspf/cmeta.jspf" %>
        <link href="<%=request.getContextPath()%>/RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title><%= professor.getUtpCode() + "-" + professor.getFullName()%></title>
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
            <div class="side-content">
                <div class="inline-flex">
                    <img src="${pageContext.request.contextPath}/RESOURCES/imgs/teacher-32.png" alt="">
                    <h2><%=professor.getFullName()%></h2>
                </div>
                <div class="flex-box">
                    <a class="input-form" href="<%=request.getContextPath()%>/professor/rate?professor_id=<%=professor.getId()%>">Rate this professor!</a>
                </div>
                <div id="profile-content">
                    <div class="text-group">
                        <h3>UTP CODE:</h3>
                        <p><%= professor.getUtpCode() %></p>
                    </div>
                    <div class="text-group">
                        <h3>Faculty:</h3>
                        <p><%= professor.getFaculty()%></p>
                    </div>
                    <div class="text-group">
                        <h3>First Name:</h3>
                        <p><%= professor.getFirstName()%></p>
                    </div>
                    <div class="text-group">
                        <h3>Last Name:</h3>
                        <p><%= professor.getLastName()%></p>
                    </div>
                </div>
            </div>
        </main>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/util.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/paginator.js" type="text/javascript"></script>
    </body>
</html>