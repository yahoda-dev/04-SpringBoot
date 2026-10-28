package com.yahodadev.intro.chap3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/chap3/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("message");

        req.setAttribute("moveType", "REDIRECT");
        req.setAttribute("message", msg);

        // Redirect의 경우 아래처럼 Request의 값을 직접 조합하여 Redirect Response로 반환해야 함
        String redirectUrl = req.getContextPath() + "/chap3/result?message="  + msg;
        resp.sendRedirect(redirectUrl);
    }
}
