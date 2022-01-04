function submitForm() {

    //Validation triggers
    let X_valid = false;
    let Y_valid = false;
    let R_valid = false;

    //Values of fields
    let X_val, Y_val, R_val;

    let form = document.values;

    let X_radio = form.getElementsByClassName("X_radio");
    let Y_text = form.Y;
    let R_select = form.R;

    //X validation
    for (let i = 0; i < X_radio.length; i++) {
        if (X_radio[i].checked) {
            X_valid = true;
            X_val = X_radio[i].value;
            break;
        }
    }

    //Y validation
    Y_val = Y_text.value;
    if ( ( Y_val.match(/^-?([0-4].\d+)?$/) || Y_val.match(/^-5$/) || Y_val.match(/^5$/) ) && !Y_val.match(/^-0$/) && !Y_val.match(/^-?0.0+$/) )  Y_valid = true;

    //R validation
    R_val = R_select.value;
    if (R_val !== "err") R_valid = true;

    if (!(X_valid && Y_valid && R_valid)) {
        if (!X_valid) {
            document.getElementById("x").style = "color: #E51C22;"
            document.getElementById("x_err").innerHTML = "*Please choose X value";
        } else {
            document.getElementById("x").style = "color: black";
            document.getElementById("x_err").innerHTML = "<br>";
        }

        if (!Y_valid) {
            document.getElementById("y_err").innerHTML = "*Please input correct Y value";
            document.getElementById("style").innerHTML = "#values input[type=\"text\"]::placeholder{\n" +
                "    color: #E51C22;\n" +
                "}"
        } else {
            document.getElementById("y_err").innerHTML = "<br>";
            document.getElementById("style").innerHTML = "#values input[type=\"text\"]::placeholder{\n" +
                "    color: grey;\n" +
                "}"
        }

        if (!R_valid) {
            document.getElementById("r_err").innerHTML = "*Please choose R value";
        } else {
            document.getElementById("r_err").innerHTML = "<br>";
        }
    } else {
        resetForm();
        getPHP(X_val, Y_val, R_val);
    }

    return false;
}

function resetForm() {

    document.getElementById("x").style = "width: 100%; color: black";

    document.getElementById("x_err").innerHTML = "<br>";
    document.getElementById("y_err").innerHTML = "<br>";
    document.getElementById("r_err").innerHTML = "<br>";

    document.getElementById("style").innerHTML = "#values input[type=\"text\"]::placeholder{\n" +
        "    color: grey;\n" +
        "}"
}

$(document).ready(function() {
    $("#graph_img").on("click", function(event) {

        let hor = event.pageX - this.offsetLeft;
        let ver = event.pageY - this.offsetTop;
        let size = this.clientWidth;

        let r = document.values.R.value;

        if (r !== "err"){

            let del = size/(r * 3);

            let x = Math.round((hor/del - 1.5*r)*100)/100;
            let y = Math.round(((-1*ver + size/2)/del)*100)/100;

            getPHP(x,y,r);
        }
        else alert("Please choose \"R\" value");
    });
});

function getPHP(x, y, r) {
    let request = new XMLHttpRequest();
    const url = "php/main.php?x=" + x + "&y=" + y + "&r=" + r + "&date=" + new Date().getTimezoneOffset();
    request.open('GET', url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-url');
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            let data = JSON.parse(request.responseText);
            document.getElementById("rows").innerHTML += row(data.x, data.y, data.r, data.current, data.execution, data.result);
        }
    });
    request.send();
}

function row(x, y, r, cur_time, ex_time, res) {
    let link = "img/" + res + ".png";
    let img = "<img src=\"" + link + "\" alt=\"" + res + "\" style=\"width: 18%\" align='center'>"
    return  "<tr>" + col(x) + col(y) + col(r) + col(cur_time) + col(ex_time) + "<td class=\"" + res + "\">\n" + img + "</td>\n" + "</tr>"
}

function col(val) {
    return "<td>\n" + val + "</td>\n"
}
