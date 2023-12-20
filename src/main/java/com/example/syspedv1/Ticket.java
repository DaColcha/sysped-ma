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
    private  List<String> detalles;
    private HistorialPedidos historial;
    private Menu menu;

    public Ticket() {
        this.subtotal = 0;
        this.productosSeleccionados= new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.historial = new HistorialPedidos();
        this.detalles = new ArrayList<>();
        this.menu = new Menu();
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

        int i = 0;
        for(ProductoEntity p : this.menu.obtenerMenu()){
            String aux = request.getParameter("item"+i);
            if(aux != null){
                int cantidad = Integer.parseInt(request.getParameter("cantidad" + i));
                String detalle = request.getParameter("detalle"+i);
                this.productosSeleccionados.add(p);
                this.cantidades.add(cantidad);
                this.detalles.add(detalle);
                cadena += formarItems(p, cantidad);
            }
            i++;
        }

        this.calcularSubtotal();
        cadena += formarPieTabla(this.subtotal);
        historial.agregarPedido(generarCodigoTicket(),cantidades, productosSeleccionados,detalles);
        return cadena;
    }

    private void calcularSubtotal(){
        int cont =0;
        for (ProductoEntity p: this.productosSeleccionados){
                subtotal += p.getPrecio().doubleValue() * this.cantidades.get(cont);
                cont++;
        }
    }

    public  double getSubtotal(){
        return this.subtotal;
    }

    private String formarCabeceraTabla(){
        return "<div class = \"ticket\"> "+
                "<div class=\"ticket-header\">Ticket de Compra</div>"+
                "<div >codigo ticket: "+generarCodigoTicket()+"</div>"+"<table border = \"1\"> "
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
                + "<div/>";
    }
}