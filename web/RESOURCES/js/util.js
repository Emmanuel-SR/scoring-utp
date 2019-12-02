/*https://davidwalsh.name/function-debounce*/
function debounce(func, wait, immediate) {
    var timeout;
    return function () {
        var context = this, args = arguments;
        var later = function () {
            timeout = null;
            if (!immediate)
                func.apply(context, args);
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow)
            func.apply(context, args);
    };
}

function arrayRemove(arr, value) {
    return arr.filter(e => e !== value);
}

function findNeighborElement(element, index) {
    return element.parentNode.parentNode.cells[index].innerHTML;
}