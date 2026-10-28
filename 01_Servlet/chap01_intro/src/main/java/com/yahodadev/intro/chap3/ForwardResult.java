package com.yahodadev.intro.chap3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/chap3/result")
public class ForwardResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String moveType = (String)req.getAttribute("moveType");


        Object attributeMessage = req.getAttribute("message");
        String parameterMessage = req.getParameter("message");

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("=== forward / redirect 실행 결과 ===");
        out.println("이동 방식: " + moveType);
        out.println("request 속성(message): " + attributeMessage);
        out.println("request 파라미터(message): " + parameterMessage);
    }
}
