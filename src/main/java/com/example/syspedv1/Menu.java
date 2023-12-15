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
            salida += "<div class=\"product\">" +
                    "<label><input type=\"checkbox\" value=\"1\" name=\"" + "item" + cont + "\">" + "Seleccionar" + "</label>";
            salida += producto.toString();
            salida += "<div class=\"amount-section\">";
            salida += "<label for=\"" + "cantidad" + cont + "\">Cantidad: </label>\n" +
                    "<input class = \"product-amount\"type=\"number\" min= \"0\" name=\"" + "cantidad" + cont + "\">";
            salida += "</div>";
            salida += "</div>";
            cont++;
        }
        salida += "</div>";

        return salida;
    }
}
