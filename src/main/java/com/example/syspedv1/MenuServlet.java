package com.example.syspedv1;

import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "menu", urlPatterns = {"","/"})

public class MenuServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            try  {
                DBConnection con = new DBConnection();
                TypedQuery<ProductoEntity> productos = con.getEntityManager().createNamedQuery("Productos.allResults", ProductoEntity.class);
                request.setAttribute("menu", FormarMenu(productos.getResultList()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DBConnection con = new DBConnection();
        int i = 0;
        double total = 0;
        String cadena =  "<div class = \"ticket\"> "+
                     "<div class=\"ticket-header\">Ticket de Compra</div>"+"<table>"
                    + "<tr>"
                    + "<th>Cantidad</th>"
                    + "<th>Nombre</th>"
                    + "<th>Precio Unitario</th>"
                    + "<th>Total</th>"
                    + "</tr>";
        TypedQuery<ProductoEntity> productos = con.getEntityManager().createNamedQuery("Productos.allResults", ProductoEntity.class);
        for(ProductoEntity p : productos.getResultList()){
            String aux = request.getParameter("item"+i);
            if(aux != null){
                int cantidad = 1;
                if (!request.getParameter("cantidad"+i).isBlank()) {
                    cantidad = Integer.parseInt(request.getParameter("cantidad" + i));
                }
                cadena += formarItems(p, cantidad);
                total += p.getPrecio().doubleValue() * cantidad;
            }
            i++;
        }
        cadena += "<tr>"
                + "<td colspan=\"3\"><strong>Total:</strong></td>"
                + "<td>$ " + BigDecimal.valueOf(total) + "</td>"
                + "</tr>";
        cadena += "</table> <div/>";
        request.setAttribute("ticket", cadena);
        request.setAttribute("menu", FormarMenu(productos.getResultList()));

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
    private String FormarMenu(List<ProductoEntity> productos){
        String salida = "<div class=\"grid-container\">";
        int cont = 0;
        for (ProductoEntity p : productos) {
            salida += "<div class=\"producto\">" +
                    "<label><input type=\"checkbox\" value=\"1\" name=\"" + "item" + cont + "\">" + "Seleccionar" + "</label>";

            salida += "<p>" + p.toString() + "</p>";

            salida += "<label for=\"" + "cantidad" + cont + "\">Cantidad: </label>\n" +
                    "<input class = \"dato\"type=\"number\" min= \"0\" name=\"" + "cantidad" + cont + "\">";

            salida += "</div>";
            cont++;
        }

        salida += "</div>";
        return salida;
    }

    private String formarItems(ProductoEntity p, int cantidad){
        return "<tr>"
                + "<td>" + cantidad + "</td>"
                + "<td>" + p.getNombreProducto() + "</td>"
                + "<td>" + p.getPrecio() + "</td>"
                + "<td>" + p.getPrecio().multiply(BigDecimal.valueOf(cantidad)) + "</td>"
                + "</tr>";
    }

}
