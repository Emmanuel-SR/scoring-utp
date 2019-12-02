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
        <title>Rate</title>
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
                <h2><%=professor.getFullName()%>!</h2>
                <form class="frm-register" action="<%=request.getContextPath()%>/scoring/save" method="POST">
                    <fieldset>
                        <legend>Score Items:</legend>
                        <input type="hidden" name="professor_id" value="<%=professor.getId()%>">
                        <div class="rating-group">
                            <label class="input-label" for="courses">Curso:</label>
                            <input type="text" class="input-form" id="course" name="course" placeholder="Enter course here." required="">
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Claridad:</label>
                            <input type="radio" value="1" name="clarity">
                            <input type="radio" value="2" name="clarity">
                            <input type="radio" value="3" name="clarity">
                            <input type="radio" value="4" name="clarity">
                            <input type="radio" value="5" name="clarity">
                            <span id="clarity-mark"></span>
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Ayuda:</label>
                            <input type="radio" value="1" name="help">
                            <input type="radio" value="2" name="help">
                            <input type="radio" value="3" name="help">
                            <input type="radio" value="4" name="help">
                            <input type="radio" value="5" name="help">
                            <span id="help-mark"></span>
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Facilidad:</label>
                            <input type="radio" value="1" name="facility">
                            <input type="radio" value="2" name="facility">
                            <input type="radio" value="3" name="facility">
                            <input type="radio" value="4" name="facility">
                            <input type="radio" value="5" name="facility">
                            <span id="facility-mark"></span>
                        </div>
                        <div class="rating-group">
                            <label class="input-label">¿Lo recomiendas?</label>
                            <span> si  </span> <input type="radio" value="true" name="recommend">
                            <span>no  </span> <input type="radio" value="false" name="recommend">
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Asistencia</label>
                            <span>Obligatoria </span> <input type="radio" value="true" name="assistance">
                            <span>No obligatoria </span><input type="radio" value="false" name="assistance">
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Comentarios</label>
                            <textarea name="comment" cols="30" rows="3" maxlength="500"></textarea>
                        </div>
                        <div class="rating-group">
                            <label class="input-label">Calificación que obtuviste en la clase</label>
                            <select name="student_score" class="grade">
                                <option selected="" value="">--choose--</option>
                                <option value="10.0">10</option>
                                <option value="9.5">9.5</option>
                                <option value="9.0">9.0</option>
                                <option value="8.5">8.5</option>
                                <option value="8.0">8.0</option>
                                <option value="7.5">7.5</option>
                                <option value="7.0">7.0</option>
                                <option value="6.5">6.5</option>
                                <option value="6.0">6.0</option>
                                <option value="5.5">5.5</option>
                                <option value="5.0">5.0</option>
                                <option value="4.5">4.5</option>
                                <option value="4.0">4.0</option>
                                <option value="3.5">3.5</option>
                                <option value="3.0">3.0</option>
                                <option value="2.5">2.5</option>
                                <option value="2.0">2.0</option>
                                <option value="1.5">1.5</option>
                                <option value="1.0">1.0</option>
                                <option value="0.5">0.5</option>
                                <option value="0">0</option>
                                <option value="21">Dada de baja</option>
                                <option value="22">Incompleta</option>
                                <option value="23">Aún no sé</option>
                                <option value="24">Prefiero no decir</option>
                                <option value="25">Clase sin calificación</option>
                            </select>
                        </div>
                        <div class="flex-box flex-row">
                            <a href="<%=request.getContextPath()%>/student/" id="btn-cancel" class="btn-cancel">Cancel</a>
                            <input class="btn-form" type="submit" value="Save">
                        </div>
                    </fieldset>
                </form>
            </div>
        </main>
        <script>
            window.onload = function (e) {

                var clarityMarks = ["Muy confuso", "Confuso", "Algo claro", "Bastante claro", "Súper claro"];
                var helpMarks = ["No ayuda nada", "Le tienes que rogar por algo de ayuda", "Sí le pides ayuda, te la da", "Lo más probable es que te ayude", "Ayuda bastante"];
                var facilityMarks = ["Muy difícil", "Algo difícil", "Lo usual", "Algo fácil", "Súper fácil"];

                var clarity = document.getElementsByName("clarity");
                var help = document.getElementsByName("help");
                var facility = document.getElementsByName("facility");

                addCustomRadioListener(clarity, document.getElementById("clarity-mark"), clarityMarks);
                addCustomRadioListener(help, document.getElementById("help-mark"), helpMarks);
                addCustomRadioListener(facility, document.getElementById("facility-mark"), facilityMarks);

            };

            function addCustomRadioListener(elements, span, marks) {
                var currentMark = "";

                elements.forEach(function (e, i) {
                    e.checked = false;
                    e.onmouseover = function () {
                        span.innerHTML = marks[i];
                    };
                    e.onclick = function () {
                        currentMark = marks[i];
                        span.innerHTML = currentMark;
                    };
                    e.onmouseleave = function () {
                        if (!e.checked)
                            span.innerHTML = currentMark;
                    };
                });
            }
        </script>
    </body>
</html>