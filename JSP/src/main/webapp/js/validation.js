var trigger = false;

function submitForm() {

    //Validation triggers
    let X_valid = false;
    let Y_valid = false;
    let R_valid = false;

    //Values of fields
    let X_val, Y_val, R_val;

    let form = document.values;

    let X_select = form.x;
    let Y_text = form.y;
    let R_text = form.r;

    //X validation
    X_val = X_select.value;
    X_valid = checkX(X_val);

    //Y validation
    Y_val = Y_text.value;
    Y_valid = checkY(Y_val) || trigger;

    //R validation
    R_val = R_text.value;
    R_valid = checkR(R_val);

    return validation(X_valid, Y_valid, R_valid);
}

function checkX(X_val) {
    return (X_val.match(/^-?\d(\.\d+)?$/) && !X_val.match(/^-0(\.0+)?$/)) != null;
}

function checkY(Y_val) {
    return ((Y_val.match(/^-?[0-2](\.\d+)?$/) || Y_val.match(/^-?3$/)) && !Y_val.match(/^-0(\.0+)?$/)) != null;
}

function checkR(R_val) {
    return ((R_val.match(/^[1-3](\.\d+)?$/) || R_val.match(/^4$/))) != null;
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

function validation(X_valid, Y_valid, R_valid) {
    let valid = X_valid && Y_valid && R_valid;

    if (!(valid)) {
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
    }
    return valid;
}
