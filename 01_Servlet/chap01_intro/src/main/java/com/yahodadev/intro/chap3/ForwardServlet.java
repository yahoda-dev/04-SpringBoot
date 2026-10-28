package com.yahodadev.intro.chap3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/chap3/forward")
public class ForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("message");

        req.setAttribute("moveType", "FORWARD");
        req.setAttribute("message", msg);

        req.getRequestDispatcher("/chap3/result").forward(req, resp);
    }
}
