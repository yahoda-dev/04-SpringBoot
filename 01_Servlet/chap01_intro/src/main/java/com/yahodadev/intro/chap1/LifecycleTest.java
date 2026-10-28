package com.yahodadev.intro.chap1;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// Tomcat에 등록하기 위한 어노테이션, name은 필수가 아니라 value만 등록한다.
@WebServlet(value = "/life-cycle")
public class LifecycleTest extends HttpServlet {
    private int initCount = 0;
    private int serviceCount = 0;
    private int destroyCount = 0;

    @Override // 최초 서블릿 요청 시 호출되는 메서드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init() => " + (++initCount));
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service() => " + (++serviceCount));
    }

    @Override // 서블릿 컨테이너가 종료될 때 호출되는 메서드
    public void destroy() {
        System.out.println("destroy() => " + (++destroyCount));
    }
}
