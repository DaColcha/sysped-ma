package com.example.syspedv1;

import entity.DetallePedidosEntity;
import entity.ProductoEntity;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public interface iFactura {

    private ProductoEntity obtenerProducto(String codigoProducto) {
        ProductoEntity producto = new ProductoEntity();
        try  {
            TypedQuery<ProductoEntity> productoById=  DBConnection.entityManager.createNamedQuery
                    ("Producto.byIdProdcuto", ProductoEntity.class);
            productoById.setParameter(1, codigoProducto);
            producto = productoById.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return producto;
    }

    private List<DetallePedidosEntity> obtenerDetallesPedido(String codigoPedido) {
        List<DetallePedidosEntity> detallesPedido = new ArrayList<>();
        try  {
            TypedQuery<DetallePedidosEntity> detallePedidobyId =  DBConnection.entityManager.createNamedQuery
                    ("DetallePedido.byIdDetalle", DetallePedidosEntity.class);
            detallePedidobyId.setParameter(1, codigoPedido);
            detallesPedido = detallePedidobyId.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return detallesPedido;
    }
}
