package com.yahodadev.intro.chap1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/service-test")
public class ServiceTest extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("message");

        System.out.println("GET-message : " + msg);

        resp.setContentType("text/plain; charset=UTF-8"); // 평문 문자열(UTF-8) 타입으로 반환
        resp.getWriter().println("GET으로 받은 값 : " + msg);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String msg = req.getParameter("message");

        resp.setContentType("text/plain; charset=UTF-8"); // 평문 문자열(UTF-8) 타입으로 반환
        resp.getWriter().println("POST로 받은 값 : " + msg);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
