var rows = [];

function addRow(left, top, res) {
    rows.push({
        "left": left,
        "top": top,
        "res": res
    });
}

function drawImg() {
    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");
    let img = document.getElementById("graph_img");

    let size = canvas.width;

    ctx.drawImage(img, 0, 0, size, size);
}

$(function () {
    $(window).resize(drawImg);
});

$(function () {
    $(window).resize(drawDots);
});

function drawDots() {
    rows.forEach(value => {
        drawDot(value.left, value.top, value.res)
    });
}

function drawDot(left, top, res) {

    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    let size = canvas.width;

    let x = size * left;
    let y = size * top;

    if (res) ctx.fillStyle = "green";
    else ctx.fillStyle = "red";

    ctx.beginPath();
    ctx.arc(x, y, 8, 0, Math.PI * 2, false);
    ctx.closePath();
    ctx.fill();
}

