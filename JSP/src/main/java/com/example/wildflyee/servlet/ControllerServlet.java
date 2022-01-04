package com.example.wildflyee.servlet;

import com.example.wildflyee.bean.TableBean;
import com.example.wildflyee.util.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {



    private final TableBean table = new TableBean();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {

        response.setContentType("text/html");

        request.setAttribute("table", table);

        Float x = Converter.StringToFloat(request.getParameter("X"));
        Float y = Converter.StringToFloat(request.getParameter("Y"));
        Float r = Converter.StringToFloat(request.getParameter("R"));

        if (validate(x, y, r)) getServletContext().getNamedDispatcher("AreaCheckServlet").forward(request, response);
        else getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    final boolean validate(Float x, Float y, Float r) {
        return (x != null) && (y != null) && (r != null) && checkX(x) && checkY(y) && checkR(r);

    }

    final boolean checkX(float x) {
        String s = x + "";
        return s.matches("-?\\d(\\.\\d+)?") && !s.matches("-0(\\.0+)?");
    }

    final boolean checkY(float y) {
        String s = y + "";
        return ((s.matches("-?[0-2](\\.\\d+)?") || s.matches("-?3")) && !s.matches("-0(\\.0+)?"));
    }

    final boolean checkR(float r) {
        String s = r + "";
        return ((s.matches("[1-3](\\.\\d+)?") || s.matches("4")));
    }
}