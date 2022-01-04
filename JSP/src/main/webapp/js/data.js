function sendData(X_val, Y_val, R_val) {
    let size = 3 * R_val;

    let hor = X_val + size / 2;
    let ver = size / 2 - Y_val;

    let left = hor / size;
    let top = ver / size;

    drawDot(left, top);
    postJSP(X_val, Y_val, R_val);
}

$(document).ready(function () {
    $("#canvas").on("click", function (event) {
        let hor = event.pageX - this.offsetLeft;
        let ver = event.pageY - this.offsetTop;
        let size = this.clientWidth;

        let r = document.values.r.value;

        if (checkR(r)) {
            document.getElementById("r_err").innerHTML = "<br>";

            let del = size / (r * 3);

            let x = Math.round((hor / del - 1.5 * r) * 100) / 100;
            let y = Math.round(((-1 * ver + size / 2) / del) * 100) / 100;

            sendData(x, y, r);

        } else {
            alert("Please choose correct \"R\" value");
            document.getElementById("r_err").innerHTML = "*Please choose R value";
        }
    });
});

function postJSP(x, y, r) {

    var select = document.getElementById("x");
    select.options[2].value = x;
    select.options[2].selected = true;

    trigger = true;


    $("#y").attr("value", y);
    $("#r").attr("value", r);

    $("#submit").trigger('click');
}
