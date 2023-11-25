package com.example.syspedv1;

import entity.FacturaEntity;
import entity.PedidoEntity;
import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "menu", urlPatterns = {"","/"})

public class AppsServlet extends HttpServlet {
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
        Menu menu = new Menu();
        Ticket ticket = new Ticket();
        request.setAttribute("ticket", ticket.generarTicket(request));
        request.setAttribute("menu", menu.mostrarMenu());


        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
