package com.wad.fils.lab2.controllers;

import com.wad.fils.lab2.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.wad.fils.lab2.controllers.RegistrationController.getHash;

@WebServlet(name= "LoginController" , value="/LoginController")
public class LoginController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Login Controller Post Method");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username1");
            String password = request.getParameter("password1");
            try {
                if(username==""||password=="")
                {
                    out.println("You need to complete all the fields");
                    request.getRequestDispatcher("login.html").include(request,response);
                }
                else if (userService.UserVerification(username, getHash(password)) == true) {
                    request.getRequestDispatcher("index.html").include(request,response);
                }
                else {
                    out.println("Wrong username/password");
                    request.getRequestDispatcher("login.html").include(request,response);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}