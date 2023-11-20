package com.example.syspedv1;

import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private double subtotal;
    private List<ProductoEntity> productosSeleccionados;
    private List<Integer> cantidades;

    private HistorialPedidos historial;

    public Ticket() {
        this.subtotal = 0;
        this.productosSeleccionados= new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.historial = new HistorialPedidos();
    }

    private String formarItems(ProductoEntity p, int cantidad){
        return "<tr>"
                + "<td>" + cantidad + "</td>"
                + "<td>" + p.getNombreProducto() + "</td>"
                + "<td>" + p.getPrecio() + "</td>"
                + "<td>" + p.getPrecio().multiply(BigDecimal.valueOf(cantidad)) + "</td>"
                + "</tr>";
    }
    private String generarCodigoTicket(){
        // Formatear el ultimo número como un código con ceros a la izquierda
        return String.format("%05d", Integer.parseInt(historial.ultimoCodigo()) + 1);
    }

    public  String generarTicket(HttpServletRequest request){
        String cadena =  formarCabeceraTabla();

        TypedQuery<ProductoEntity> productos = DBConnection.entityManager
                .createNamedQuery("Productos.allResults", ProductoEntity.class);

        int i = 0;
        for(ProductoEntity p : productos.getResultList()){
            String aux = request.getParameter("item"+i);
            if(aux != null){
                int cantidad = (!request.getParameter("cantidad" + i).isBlank()) ? Integer.parseInt(request.getParameter("cantidad" + i)) : 1;

                this.productosSeleccionados.add(p);
                this.cantidades.add(cantidad);

                cadena += formarItems(p, cantidad);
            }
            i++;
        }

        this.calcularSubtotal();
        cadena += formarPieTabla(this.subtotal);
        historial.agregarPedido(generarCodigoTicket(),cantidades, productosSeleccionados);
        return cadena;
    }

    private void calcularSubtotal(){
        for (ProductoEntity p: this.productosSeleccionados){
            for (Integer i: this.cantidades){
                subtotal += p.getPrecio().doubleValue() * i;
            }
        }
    }

    public  double getSubtotal(){
        return this.subtotal;
    }

    private String formarCabeceraTabla(){
        return "<div class = \"ticket\"> "+
                "<div class=\"ticket-header\">Ticket de Compra</div>"+
                "<div >codigo ticket: "+generarCodigoTicket()+"</div>"+"<table>"
                + "<tr>"
                + "<th>Cantidad</th>"
                + "<th>Nombre</th>"
                + "<th>Precio Unitario</th>"
                + "<th>Total</th>"
                + "</tr>";
    }
    private  String formarPieTabla(double total) {
        return "<tr>"
                + "<td colspan=\"3\"><strong>Total:</strong></td>"
                + "<td>$ " + BigDecimal.valueOf(total) + "</td>"
                + "</tr>"
                + "</table> "
                + "<div/>"
                + "<form action=\"factura.jsp\" method=\"post\">"
                + "<div class=\"contenedor\"><button type=\"submit\">Generar factura</button></div>"
                + "</form>";
    }
}