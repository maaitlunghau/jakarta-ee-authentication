package com.fpt.controller;

import com.fpt.dal.AccountDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AuthController", urlPatterns = {"/controller"})
public class AuthController extends HttpServlet {

    private final AccountDal dal = new AccountDal();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 2.2.1. Lấy giá trị các đối số
        String action = request.getParameter("action") != null
                ? request.getParameter("action") : "";

        String user = request.getParameter("txtName");
        String pass = request.getParameter("txtPass");

        switch (action) {
            case "Login" -> {
                if (dal.checkLogin(user, pass)) {
                    // lưu thông tin account vào session
                    response.addCookie(new Cookie("username", user) {
                        {
                            setMaxAge(24 * 60 * 60);
                        }
                    });
                    response.addCookie(new Cookie("password", pass) {
                        {
                            setMaxAge(24 * 60 * 60);
                        }
                    });

                    response.sendRedirect("home.jsp");
                } else {
                    request.setAttribute("errMes", "Username or password is incorrect!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            case "Register" -> {
                if (dal.registerAccount(user, pass)) {
                    request.setAttribute("ssMes", "Success, please login in!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.setAttribute("errMes", "Username already exists!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            case "Logout" -> {
                request.getSession().invalidate(); // huỷ session

                // xoá cookie
                response.addCookie(new Cookie("username", "") {
                    {
                        setMaxAge(0);
                    }
                });
                response.addCookie(new Cookie("password", "") {
                    {
                        setMaxAge(0);
                    }
                });

                response.sendRedirect("login.jsp");
            }
            default -> {
                response.sendRedirect("login.jsp");
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}