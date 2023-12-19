package com.example.syspedv1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ticket", urlPatterns = {"/ticket"})

public class TicketServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = new Ticket();
        request.setAttribute("ticket", ticket.generarTicket(request));
        getServletContext().getRequestDispatcher("/ticket.jsp").forward(request, response);
    }

}
