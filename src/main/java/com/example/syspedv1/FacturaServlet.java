package com.example.syspedv1;

import entity.FacturaEntity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura"})

public class FacturaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try  {
            FacturaEntity factura = new FacturaEntity();
            request.setAttribute("generarFactura", factura.generarFactura());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        FacturaEntity factura = new FacturaEntity();
        request.setAttribute("generarFactura", factura.generarFactura());
        request.setAttribute("mostrarFactura", factura.mostrarFactura(request));

        getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);

    }
}

