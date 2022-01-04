package com.example.wildflyee.servlet;


import com.example.wildflyee.bean.ResponseBean;
import com.example.wildflyee.bean.TableBean;
import com.example.wildflyee.util.Converter;
import com.sun.istack.internal.NotNull;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TableBean table = (TableBean) req.getAttribute("table");

        ResponseBean response = new ResponseBean();

        float x = Converter.StringToFloat(req.getParameter("X"));
        float y = Converter.StringToFloat(req.getParameter("Y"));
        float r = Converter.StringToFloat(req.getParameter("R"));
        long now = System.nanoTime();

        String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Boolean res = isIn(x, y, r);
        Long exTime = (System.nanoTime() - now) / 1000;

        float size = 3 * r;

        float hor = x + size / 2;
        float ver = size / 2 - y;

        Float left = hor / size;
        Float top = ver / size;

        response.setX(x);
        response.setY(y);
        response.setR(r);
        response.setCurTime(curTime);
        response.setExTime(exTime);
        response.setRes(res);

        response.setLeft(left);
        response.setTop(top);

        req.setAttribute("table", table);


        RequestDispatcher dis = req.getRequestDispatcher("index.jsp");
        dis.forward(req, resp);
    }

    private boolean isIn(float x, float y, float r) {
        return (isCircle(x, y, r) || isRect(x, y, r) || isTriangle(x, y, r));
    }

    private boolean isTriangle(float x, float y, float r) {
        return (x <= 0 && y <= 0 && y + x + r / 2 >= 0);
    }

    private boolean isCircle(float x, float y, float r) {
        return (x >= 0 && y <= 0 && x * x + y * y <= (r / 2) * (r / 2));
    }

    private boolean isRect(float x, float y, float r) {
        return (x <= 0 && y >= 0 && x >= -1 * r / 2 && y <= r);
    }

}
