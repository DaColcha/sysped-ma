package com.example.syspedv1;

import entity.ProductoEntity;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CarritoDeProductos {
    private List<ProductoEntity> productos = new ArrayList<>();
    private List<Integer> cantidades = new ArrayList<>();
    private List<String> detalles = new ArrayList<>();

    public void setProductos(List<ProductoEntity> productos) {
        this.productos = productos;
    }
    public void agregarProducto(ProductoEntity producto, int cantidad, String detalle){
        int flag = ComprobarProductoExistente(producto.getIdProducto());

        if(flag != -1){
            cantidades.set(flag, cantidades.get(flag) + cantidad);
            if(detalle !=null){
                detalles.set(flag,detalle);
            }
        }else {
            productos.add(producto);
            cantidades.add(cantidad);
            detalles.add(detalle);
        }
    }

    private int ComprobarProductoExistente(String cod) {
        int cont = 0;
        for(ProductoEntity p:productos ){
            if(p.getIdProducto().equals(cod)){
                return cont;
            }
            cont++;
        }
        return -1;
    }

    public  void eliminarProducto(String cod){
        int index =-1;
        int cont =0;

        for(ProductoEntity p:productos ){

            if(p.getIdProducto().equals(cod)){
                index = cont;
            }
            cont++;
        }
        productos.remove(index);
        cantidades.remove(index);
        detalles.remove(index);
    }

    public String mostrarCarrito(){
        String tabla = formarCabeceraTabla();
        int cont =0;
        double total =0.0;
        tabla += "<tbody>";
        for(ProductoEntity p: productos){
            tabla +=formarFila(p,cantidades.get(cont), detalles.get(cont));
            total += ((p.getPrecio().multiply(BigDecimal.valueOf(cantidades.get(cont))) )).doubleValue();
            cont++;
        }
        tabla += "</tbody>";
        if(productos.size() ==0){
            return "";
        }
        tabla += formarPieTabla(total);

        return tabla;

    }
    private String formarCabeceraTabla(){
        return "<table id=\"miTabla\">" +"<theader<<tr>"
                + "<th>Codigo</th>"
                + "<th>imagen</th>"
                + "<th>Descripcion</th>"
                + "<th>Cantidad</th>"
                + "<th>Precio</th>"
                + "<th>Total</th>"
                + "<th>Detalle</th>"
                + "</tr>";
    }
    private  String formarPieTabla(double total) {
        return "<tr>"
                + "<td colspan=\"3\"><strong>Total:</strong></td>"
                + "<td>$ " + BigDecimal.valueOf(total) + "</td>"
                + "</tr>"
                + "</table> "
                + "<button class=\"delete-all-button\">Eliminar Orden</button>"
                +"<button class=\"Actualizar\">Actualizar Orden</button>";
    }
    private String formarFila(ProductoEntity p, int cantidad,String detalle){
        return "<tr>"
                + "<td class=\"id\">" + p.getIdProducto()+ "</td>"
                +"<td><img src = \"" + p.getImagen() +"\"/></td>"
                + "<td>" + p.getNombreProducto() + "</td>"
                + "<td contenteditable=\"true\" class=\"numero-celda cantidad\">" + cantidad + "</td>"
                + "<td>" + p.getPrecio() + "</td>"
                + "<td>" + p.getPrecio().multiply(BigDecimal.valueOf(cantidad)) + "</td>"
                + "<td contenteditable=\"true\" class=\"detalle\">" + detalle+ "</td>"
                + "<td><button class=\"delete-button\" data-id=\"" + p.getIdProducto() + "\">Eliminar</button></td>"
                + "</tr>";
    }

    public void eliminarTdo() {
        productos.clear();
        cantidades.clear();
        detalles.clear();
    }

    public void actualizar(String id, int cantidad, String detalle) {
        int flag = ComprobarProductoExistente(id);

        if (flag != -1) {
            cantidades.set(flag, cantidad);
            if (detalle != null) {
                detalles.set(flag, detalle);
            }

        }
    }

    public String crearFormulario(){
        String salida = "";
        int cont =0;
        for (ProductoEntity p: productos){
            salida += "<input type=\"hidden\" value=\"1\" name=\"" + "item" + Integer.parseInt(p.getIdProducto()) + "\">";
            salida +="<input type=\"hidden\" value=\""+cantidades.get(cont)+"\" name=\"" + "cantidad" + Integer.parseInt(p.getIdProducto()) + "\">";
            salida +="<input type=\"hidden\" value=\""+detalles.get(cont)+"\" name=\"" + "detalles" + Integer.parseInt(p.getIdProducto()) + "\">";
            cont++;
        }
        salida += "<button type=\"submit\">Realizar ticket</button>";
        return salida;

    }
}
