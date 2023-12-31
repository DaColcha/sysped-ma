package com.example.syspedv1;

import entity.ClienteEntity;
import entity.DetallePedidosEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class FacturaController {

    private ClienteEntity cliente;
    private String codPedido;

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public BigDecimal calcularSubtotal(List<DetallePedidosEntity> detallesPedido) {
        BigDecimal resultado = new BigDecimal("0");
        String idProducto;
        ProductoEntity producto;
        for(DetallePedidosEntity detalles : detallesPedido){
            idProducto = detalles.getProducto();
            producto = obtenerProducto(idProducto);
            resultado = resultado.add(calcularPrecio(producto, detalles));
        }
        return resultado;
    }

    public BigDecimal calcularPrecio(ProductoEntity producto, DetallePedidosEntity detallePedido){
        return BigDecimal.valueOf(detallePedido.getCantidad()).multiply(producto.getPrecio());
    }

    public BigDecimal calcularIVA(BigDecimal subtotal) {
        BigDecimal resultado;
        resultado = BigDecimal.valueOf(0.12).multiply(subtotal);
        resultado = resultado.setScale(2, RoundingMode.HALF_UP);
        return resultado;
    }

    public BigDecimal calcularTotal(BigDecimal subtotal, BigDecimal impuesto) {
        return subtotal.add(impuesto);
    }

    public String generarCodigoFactura(String ultimoCodigo) {
        return String.format("%05d", Integer.parseInt(ultimoCodigo) + 1);
    }

    public ProductoEntity obtenerProducto(String codigoProducto) {
        ProductoEntity producto = new ProductoEntity();
        try  {
            TypedQuery<ProductoEntity> productoById=  DBConnection.entityManager.createNamedQuery
                    ("Producto.byIdProdcuto", ProductoEntity.class);
            productoById.setParameter(1, codigoProducto);
            List<ProductoEntity> resultList = productoById.getResultList();
            if (!resultList.isEmpty()) {
                producto = resultList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return producto;
    }

    private List<DetallePedidosEntity> obtenerDetallesPedido() {
        List<DetallePedidosEntity> detallesPedido = new ArrayList<>();
        try  {
            TypedQuery<DetallePedidosEntity> detallePedidobyId =  DBConnection.entityManager.createNamedQuery
                    ("DetallePedido.byIdDetalle", DetallePedidosEntity.class);
            detallePedidobyId.setParameter(1, this.codPedido);
            detallesPedido = detallePedidobyId.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return detallesPedido;
    }


    public String mostrarFactura(){
        String resultado =  "<h2>Factura Nº "+ generarCodigoFactura("0") + "</h2>"
                + "<p>Correspondiente al pedido Nº "+ this.codPedido + "</p> <hr />"
                + "<table class=\"table-client\">"
                + "<tr>"
                + "<th> Cliente: </th>" + "<td>" + this.cliente.getNombres() + " " + this.cliente.getApellidos()+ "</td>"
                + "</tr>"
                + "<tr>"
                + "<th> Cédula:</th>" + "<td>" + this.cliente.getCedula() + "</td>"
                + "</tr>"
                + "<tr>"
                + "<th> Teléfono: </th>" + "<td>" + this.cliente.getTelefono() + "</td>"
                + "</tr>"
                + "<tr>"
                + "<th> Correo electrónico: </th>" + "<td>" + this.cliente.getCorreoElectronico() + "</td>"
                + "</tr>"
                + "<tr>"
                + "<th>Dirección: </th>" + "<td>" + this.cliente.getDireccion() + "</td>"
                + "</tr>"
                + "</table>"
                + "<hr />";
        return resultado;
    }

    private String formarDetallesPedido(ProductoEntity producto, int cantidad){
        return "<tr>"
                + "<td>" + cantidad + "</td>"
                + "<td>" + producto.getNombreProducto() + "</td>"
                + "<td>"  + producto.getPrecio() + "</td>"
                + "<td>"  + producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)) + "</td>"
                + "</tr>";
    }

    public String mostrarDetalleFactura(){
        String resultado = "<table>"
                + "<tr>"
                + "<th>Cantidad</th>"
                + "<th>  Nombre  </th>"
                + "<th>P. Unitario</th>"
                + "<th>Total</th>"
                + "</tr>";
        String idProducto;
        ProductoEntity producto;
        for(DetallePedidosEntity detalles : this.obtenerDetallesPedido()){
            idProducto = detalles.getProducto();
            producto = obtenerProducto(idProducto);
            resultado += formarDetallesPedido(producto, detalles.getCantidad());
        }
        resultado += mostrarCalculoTotal();
        return resultado;
    }

    public String mostrarCalculoTotal(){
        BigDecimal subtotal = this.calcularSubtotal(this.obtenerDetallesPedido());
        String resultado =
                "<tr>"
                        + "<th colspan=\"3\"> Subtotal </th>" + "<td>"  + subtotal + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<th colspan=\"3\"> IVA </th>" + "<td>"  + this.calcularIVA(subtotal) + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<th colspan=\"3\"> Total </th>" + "<td>" + this.calcularTotal(subtotal, this.calcularIVA(subtotal)) + "</td>"
                        + "</tr>"
                        + "</table>";
        return resultado;
    }

}
