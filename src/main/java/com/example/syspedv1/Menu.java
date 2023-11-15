package com.example.syspedv1;

import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Menu {
    private List<ProductoEntity> productos;
    private TypedQuery<ProductoEntity> obtenerMenu(){
        DBConnection con = new DBConnection();
        TypedQuery<ProductoEntity> productos = DBConnection.entityManager.createNamedQuery("Productos.allResults", ProductoEntity.class);
        this.productos = productos.getResultList();
        return productos;
    }
    public String mostrarMenu(){
        TypedQuery<ProductoEntity> productos = obtenerMenu();
        String salida = "<div class=\"grid-container\">";
        int cont = 0;
        for (ProductoEntity p : productos.getResultList()) {
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

    public List<ProductoEntity> getProductos() {
        return productos;
    }
}
