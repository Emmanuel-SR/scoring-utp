window.addEventListener("load", function (e) {

    document.getElementById("btn-toggle").addEventListener("click", (function (e) {
        e.preventDefault();
        var isDisplay = true;
        var menu = document.getElementsByClassName("profile-menu")[0];
        return function () {
            if (isDisplay) {
                menu.style.display = "none";
            } else {
                menu.style.display = "flex";
            }
            isDisplay = !isDisplay;
        };
    }(e)), true);

}, true);