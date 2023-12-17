package com.example.syspedv1;

import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Menu {
    private List<ProductoEntity> productos;
    public Menu() {
        this.productos = this.obtenerMenu();
    }
    public List<ProductoEntity> obtenerMenu() {
        return (DBConnection.entityManager.createNamedQuery("Productos.allResults", ProductoEntity.class).getResultList());
    }
    public String mostrarMenu() {
        String salida = "<div class=\"menu-container\">";
        int cont = 0;

        for (ProductoEntity producto: this.productos) {
            salida += formarCabecera(producto,cont);
            salida += "<button class=\"agregar-carrito\">Agregar al Carrito</button>\n";
            salida += producto.toString();
            salida += "<div class=\"amount-section\">";
            salida += "<label for=\"" + "cantidad" + cont + "\">Cantidad: </label>\n" +
                    "<input class = \"product-amount\"type=\"number\" min= \"0\" name=\"" + "cantidad" + "\">" ;
            salida += "</div>";
            salida += "<textarea class = \"product-detalle\" name=\"detalle\" rows=\"4\" cols=\"50\" placeholder=\"Ingresa tu comentario\"></textarea>";

            salida += "</div>";
            cont++;
        }
        salida += "</div>";

        return salida;
    }

    private String formarCabecera(ProductoEntity producto, int cont) {
        return "<div class=\"product\" data-id=\""+String.format("%03d",cont)+"\""+ "data-img=\""+producto.getImagen()+"\" " +
                "data-des=\""+producto.getNombreProducto()+"\" " +
                "data-precio=\""+producto.getPrecio().doubleValue()+"\" "+
                ">" ;
    }
}
