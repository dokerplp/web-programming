<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.example.wildflyee.bean.ResponseBean" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.example.wildflyee.bean.TableBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/section.css">
    <link rel="stylesheet" href="css/graph.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="css/form.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <script src="js/validation.js"></script>
    <script src="js/data.js"></script>
    <script src="js/canvas.js"></script>
    <title>Web lab 1</title>
    <style id="style">
        /*For error styles*/
    </style>
</head>
<body>
<header>
    <div id="author">
        <div id="student">
            <div>Student</div>
            <div>Valerii Butorin</div>
        </div>
        <div id="group">
            <div>Group</div>
            <div>P3231</div>
        </div>
        <div id="option">
            <div>Option</div>
            <div>804</div>
        </div>
    </div>
</header>
<section>
    <div id="left">
        <div id="graph">
            <div>
                Graph
            </div>
            <div>
                <canvas id="canvas" width="1000px" height="1000px">
                    <img id="graph_img" src="img/graph.png" alt="graph">
                </canvas>
                <script>
                    drawImg();
                </script>
            </div>
        </div>
        <div id="form">
            <div>
                Form
            </div>
            <div>
                <form id="values" name="values" action="${pageContext.request.contextPath}/ControllerServlet"
                      method="get" onsubmit="return submitForm()" onreset="resetForm()">
                    <div id="x_val">
                        <div>
                            <label>
                                X
                            </label>
                        </div>
                        <div>
                            <label>
                                <select id="x" name="X">
                                    <option value="err" hidden disabled selected>
                                        &nbsp &nbsp &nbsp &nbsp Select &nbsp &nbsp &nbsp &nbsp
                                    </option>
                                    <option value="-2">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp -2</option>
                                    <option value="-1.5">&nbsp &nbsp &nbsp &nbsp &nbsp -1.5</option>
                                    <option value="-1">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp -1</option>
                                    <option value="-0.5">&nbsp &nbsp &nbsp &nbsp &nbsp -0.5</option>
                                    <option value="0">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 0</option>
                                    <option value="0.5">&nbsp &nbsp &nbsp &nbsp &nbsp 0.5</option>
                                    <option value="1">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 1</option>
                                    <option value="1.5">&nbsp &nbsp &nbsp &nbsp &nbsp 1.5</option>
                                    <option value="2">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 2</option>
                                </select>
                            </label>
                        </div>
                        <div class="errors">
                            <label id="x_err">
                                <br>
                            </label>
                        </div>
                    </div>
                    <div id="y_val">
                        <div>
                            <label>
                                Y
                            </label>
                        </div>
                        <div>
                            <label>
                                <input id="y" type="text" name="Y" placeholder="Value from -3 to 3" class="Y_text">
                            </label>
                        </div>
                        <div class="errors">
                            <label id="y_err">
                                <br>
                            </label>
                        </div>
                    </div>
                    <div id="r_val">
                        <div>
                            <label>
                                R
                            </label>
                        </div>
                        <div>
                            <label>
                                <input id="r" type="text" name="R" placeholder="Value from 1 to 4" class="R_text">
                            </label>
                        </div>
                        <div class="errors">
                            <label id="r_err">
                                <br>
                            </label>
                        </div>
                    </div>
                    <div class="button">
                        <input id="submit" type="submit" value="Submit" name="submit">
                        <input id="reset" type="reset" value="Reset" name="reset">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="right">
        <div id="table">
            <div>
                Table
            </div>
            <div>
                <table id="data">
                    <thead>
                    <tr>
                        <td id="x_col">X</td>
                        <td id="y_col">Y</td>
                        <td id="r_col">R</td>
                        <td id="cur_col">Current time</td>
                        <td id="ex_col">Execution time</td>
                        <td id="res_col">Hit result</td>
                    </tr>
                    </thead>
                    <tbody id="rows">
                    <jsp:useBean id="table" scope="request" class="com.example.wildflyee.bean.TableBean"/>
                    <c:forEach var="row" items="${table.responses}">
                        <tr>
                            <td>
                                    ${row.x}
                            </td>
                            <td>
                                    ${row.y}
                            </td>
                            <td>
                                    ${row.r}
                            </td>
                            <td>
                                    ${row.curTime}
                            </td>
                            <td>
                                    ${row.exTime} ms
                            </td>
                            <td class="${row.res}">
                                <img src="img/${row.res}.png" alt="${row.res}" style="width: 18%" align='center'>
                            </td>
                        </tr>
                        <script>
                            addRow(${row.left}, ${row.top}, ${row.res});
                            drawDots();
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
</body>
</html>