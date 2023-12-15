package com.example.syspedv1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "menu", urlPatterns = {"", "/"})

public class MenuServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            try  {
                Menu menu = new Menu();
                request.setAttribute("menu", menu.mostrarMenu());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = new Ticket();
        request.setAttribute("ticket", ticket.generarTicket(request));
        getServletContext().getRequestDispatcher("/ticket.jsp").forward(request, response);

    }
}
