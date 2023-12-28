package com.example.syspedv1;

import com.google.gson.Gson;
import entity.ClienteEntity;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura"})
public class FacturaServlet extends HttpServlet {
    FacturaController facturaController = new FacturaController();
    ClienteController clientController = new ClienteController();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try  {
            request.setAttribute("generarFactura", facturaController.ingresoDatosCliente());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            // Obtener datos
            String cedula = request.getParameter("cedulaCliente");
            String nombre = request.getParameter("nombreCliente");
            String apellido = request.getParameter("apellidoCliente");
            String telefono = request.getParameter("telefonoCliente");
            String email = request.getParameter("emailCliente");
            String direccion = request.getParameter("direccionCliente");

            //Crear objeto
            ClienteEntity client = new ClienteEntity();
            client.setCedula(cedula);
            client.setNombres(nombre);
            client.setApellidos(apellido);
            client.setTelefono(telefono);
            client.setCorreoElectronico(email);
            client.setDireccion(direccion);

            //Actualizar base de datos
            clientController.registarCliente(client);

            request.setAttribute("generarFactura", "");
            request.setAttribute("mostrarFactura", facturaController.mostrarFactura(request));
            request.setAttribute("mostrarDetalleFactura", facturaController.mostrarDetalleFactura(request));
            getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedulaCliente");
        System.out.println(cedula);
        if(clientController.clienteExiste(cedula)){
            ClienteEntity existente = clientController.cliente;
            request.setAttribute("cedulaCliente", cedula);
            request.setAttribute("nombreCliente", existente.getNombres());
            request.setAttribute("apellidoCliente", existente.getApellidos());
            request.setAttribute("telefonoCliente", existente.getTelefono());
            request.setAttribute("emailCliente", existente.getCorreoElectronico());
            request.setAttribute("direccionCliente", existente.getDireccion());
        }else{
            request.setAttribute("error", "Cliente no registrado");
        }
        getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);

    }
}

