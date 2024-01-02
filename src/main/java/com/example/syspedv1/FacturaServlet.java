package com.example.syspedv1;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.ClienteEntity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura"})
public class FacturaServlet extends HttpServlet {
    static FacturaController facturaController = new FacturaController();
    ClienteController clientController = new ClienteController();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {

            if (clientController.cliente == null){
                // Obtener datos
                String cedula = request.getParameter("cedulaCliente");
                String nombre = request.getParameter("nombreCliente");
                String apellido = request.getParameter("apellidoCliente");
                String telefono = request.getParameter("telefonoCliente");
                String email = request.getParameter("emailCliente");
                String direccion = request.getParameter("direccionCliente");

                //Creamos objeto
                ClienteEntity client = new ClienteEntity();
                client.setCedula(cedula);
                client.setNombres(nombre);
                client.setApellidos(apellido);
                client.setTelefono(telefono);
                client.setCorreoElectronico(email);
                client.setDireccion(direccion);

                //Actualizar base de datos
                clientController.registarCliente(client);

                //request.setAttribute("cedulaCliente", cedula);
                //request.setAttribute("codigoPedido", request.getParameter("codigoPedido"));
            }

            facturaController.setCodPedido(request.getParameter("codigoPedido"));
            facturaController.setCliente(clientController.cliente);

            request.setAttribute("generarFactura", "");
            request.setAttribute("mostrarFactura", facturaController.mostrarFactura());
            request.setAttribute("mostrarDetalleFactura", facturaController.mostrarDetalleFactura());
            request.setAttribute("mostrarMetodoPago", facturaController.mostrarMetodoDePago());
            getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonResponse;
        response.setContentType("application/json");

        Gson gson = new Gson();
        JsonObject jsonRequest = gson.fromJson(request.getReader(), JsonObject.class);
        String cedula = jsonRequest.get("cedulaCliente").getAsString();

        if(clientController.clienteExiste(cedula)){
            ClienteEntity existente = clientController.cliente;

            String nombreCliente = existente.getNombres();
            String apellidoCliente = existente.getApellidos();
            String telefonoCliente=existente.getTelefono();
            String emailCliente=existente.getCorreoElectronico();
            String direccionCliente=existente.getDireccion();

            jsonResponse = String.format(
                    "{\"nombreCliente\":\"%s\",\"apellidoCliente\":\"%s\",\"telefonoCliente\":\"%s\",\"emailCliente\":\"%s\",\"direccionCliente\":\"%s\",\"noEncontrado\":\"\"}",
                    nombreCliente, apellidoCliente, telefonoCliente, emailCliente, direccionCliente);

        }else{
            jsonResponse = "{\"noEncontrado\":\"Cliente no registrado\"}";
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
        }
        //getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);

    }
}

