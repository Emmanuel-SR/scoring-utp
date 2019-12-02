<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../jspf/cmeta.jspf" %>
        <link href="<%=request.getContextPath()%>/RESOURCES/css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Search</title>
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
                <div id="professors" class="tabcontent">
                    <h2>Find Professors</h2>
                    <div class="search-group">
                        <input class="input-form width-50-percent" type="text" id="search_professors" name="professor_name">
                    </div>
                    <div>Can't find a professor?&nbsp;<a id="add_link" href="<%=request.getContextPath()%>/professor/add"><b id="searched_text">add a new one!</b> </a></div>
                    <table id="professor_table" class="width-50-percent">
                        <thead>
                            <tr>
                                <th>Professor FullName</th>
                                <th>#Scorings</th>
                                <th>%Facilty</th>
                                <th>%Help</th>
                                <th>%Clarity</th>
                                <th>%Total</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div id="professor_box"></div>
                </div>
                <div id="professors" class="tabcontent"  style="display: none">
                    <h2>Find your professors</h2>
                    <div class="search-group">
                        <input class="input-form width-50-percent" type="text" id="search_professor" name="professor_name">
                    </div>
                </div>
            </div>
        </main>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/util.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/RESOURCES/js/paginator.js" type="text/javascript"></script>
        <%@include file="../../jspf/cscript_alert.jspf" %>
        <script>

            var config = {
                table: document.getElementById("professor_table"),
                box: document.getElementById("professor_box"),
                active_class: "color_page"
            };

            function ajaxCourse(text) {
                var table = document.getElementById("professor_table");
                var tbody = table.getElementsByTagName("tbody")[0];

                tbody.innerHTML = "";

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {

                        var keys = ["professorFullName", "scorings", "clarityAverage", "helpAverage", "facilityAverage", "TotalAverage"];

                        var professors = JSON.parse(this.responseText);

                        professors.forEach(function (e) {
                            var tr = document.createElement("tr");
                            for (var i = 0; i < keys.length; i++) {
                                var td = document.createElement("td");
                                if (keys[i] === 'professorFullName') {
                                    var a = document.createElement("a");
                                    a.href = "<%=request.getContextPath()%>/professor?id=" + e["professorId"];
                                    a.innerHTML = e["professorFullName"];
                                    a.style.textDecoration = "underline";
                                    td.appendChild(a);
                                    tr.appendChild(td);
                                } else {
                                    td.innerHTML = typeof e[keys[i]] === 'undefined' ? "" : e[keys[i]];
                                }
                                tr.appendChild(td);
                            }
                            tbody.appendChild(tr);
                        });
                        paginator(config);
                    }
                };
                xhttp.open("GET", "<%=request.getContextPath()%>/professor/findAllScoringDetailsByText?limit=1000&text=" + text, true);
                xhttp.send();
            }

            window.addEventListener("load", function () {

                ajaxCourse("");

                document.getElementById("search_professors").addEventListener("input", debounce(function () {
                    if (this.value.length > 3) {
                        document.getElementById("searched_text").innerHTML = "add " + this.value + "!";
                        document.getElementById("add_link").href = "<%=request.getContextPath()%>/professor/add?text=" + this.value;
                        ajaxCourse(this.value);
                    } else {
                        document.getElementById("searched_text").innerHTML = "add a new one!";
                        document.getElementById("add_link").href = "<%=request.getContextPath()%>/professor/add";
                    }
                }, 1000), true);

            }, true);
        </script>
    </body>
</html>