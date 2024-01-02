package com.example.syspedv1;

import entity.ClienteEntity;
import entity.FacturaEntity;
import entity.PedidoEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "pago", urlPatterns = {"/pago"})

public class PagoServlet extends HttpServlet {

    FacturaController facturaController = new FacturaController();
    RepositorioFacturas repositorioFacturas = new RepositorioFacturas();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {

            if (repositorioFacturas.getFactura() == null){
                // Obtener datos para la factura
                String numFactura = facturaController.generarCodigoFactura(facturaController.ultimoNumeroFactura());
                String codCliente = FacturaServlet.facturaController.getCliente().getCedula();
                String codPedido = FacturaServlet.facturaController.getCodPedido();
                String metodoPago = request.getParameter("metodoPago");


                //Creamos objeto para la factura
                FacturaEntity nuevaFactura = new FacturaEntity();
                nuevaFactura.setNumFactura(numFactura);
                nuevaFactura.setCliente(codCliente);
                nuevaFactura.setPedido(codPedido);
                nuevaFactura.setMetodoPago(metodoPago);
                nuevaFactura.setFechaFactura(new java.sql.Date(nuevaFactura.obtenerFechaFacturacion().getTime()));


                //Actualizar base de datos
                repositorioFacturas.registarFactura(nuevaFactura);

                //Actualizar estado del pedido pagado
                FacturaController facturaAuxiliar = new FacturaController();
                facturaAuxiliar.actualizarEstadoPedido(codPedido);

                String notificacionPago = "<hr/>El pago ha sido registrado con éxito. \nEl método de pago fue: " + metodoPago;
                request.setAttribute("notificacionPago", notificacionPago);

            }

            getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/factura.jsp").forward(request, response);
    }

}
