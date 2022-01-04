var buffer = [];

function addBuffer(left, top, res, r) {
    buffer.push({
        "left": left,
        "top": top,
        "res": res,
        "r": r
    });
}

function drawImg() {
    var canvas = document.getElementById('canvas');
    let img = document.getElementById("img");

    let size = canvas.width;

    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');

        ctx.drawImage(img, 0, 0, size, size);
    }
}

$(document).ready(function () {
    drawImg();
    drawDots();
})

function reload() {
    document.location.reload(false);
}

$(document).ready(function() {
    $("canvas").on("click", function(event) {

        if (document.getElementById("input:hidden_r").value !== '') {

            let canvas = document.getElementById('canvas');

            let clientSize = canvas.clientWidth;
            let canvasSize = canvas.width;

            let hor = (event.pageX - this.offsetLeft) / clientSize * canvasSize;
            let ver = (event.pageY - this.offsetTop) / clientSize * canvasSize;

            let del = canvasSize / 7;

            let x = Math.round((hor / del - 3.5) * 100) / 100;
            let y = Math.round((-1 * ver / del + 3.5) * 100) / 100;


            document.getElementById("input:hidden_x").value = x;
            document.getElementById("input:hidden_y").value = y;

            document.getElementById("input:submit").click();
        }
    });
});


function drawDot(left, top, res){
    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    let size = canvas.width;

    let x = size * left;
    let y = size * top;

    if (res === 'true') ctx.fillStyle = 'green';
    else ctx.fillStyle = 'red';

    ctx.beginPath();
    ctx.arc(x, y, 8, 0, Math.PI * 2, false);
    ctx.closePath();
    ctx.fill();
}

function drawDots(){
    let val = document.getElementById("input:hidden_r").value;
    if (val !== ''){
        buffer.forEach(value => {
            if (value.r === val) {
                drawDot(value.left, value.top, value.res);
            }
        });
    }
}
