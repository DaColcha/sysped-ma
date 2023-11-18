package com.example.syspedv1;

import entity.DetallePedidosEntity;
import entity.PedidoEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class HistorialPedidos {
    public void agregarPedido(String idPedido, List<Integer> numDetalles, List<ProductoEntity> productos){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(idPedido);
        pedido.setFechaPedido(new java.sql.Date(pedido.obtenerFecha().getTime()));
        pedido.setEstado("Pendiente");
        pedido.setHoraPedido(pedido.obtenerHoraActual());
        guardarEnDB(pedido, obtenerDetallesPedido(idPedido, numDetalles, productos));
    }
    private static List<DetallePedidosEntity> obtenerDetallesPedido(String idPedido, List<Integer> numDetalles, List<ProductoEntity> productos) {
        int contador = 0;
        List<DetallePedidosEntity> detalles = new ArrayList<>();
        DetallePedidosEntity detalle;
        for (ProductoEntity producto : productos) {
            detalle = new DetallePedidosEntity();
            detalle.setNumDetalle(numDetalles.get(contador));
            detalle.setPedido(idPedido);
            detalle.setProducto(producto.getIdProducto());
            contador++;
            detalles.add(detalle);
        }
        return detalles;
    }

    private void guardarEnDB(PedidoEntity pedido, List<DetallePedidosEntity> detalles){
        EntityManager entityManager = DBConnection.entityManager;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Cargar el pedido desde la base de datos si ya existe
            PedidoEntity pedidoExistente = entityManager.find(PedidoEntity.class, pedido.getIdPedido());

            // Si el pedido existe, actualizar sus atributos con los nuevos valores
            if (pedidoExistente != null) {
                pedidoExistente.setEstado(pedido.getEstado());
                pedidoExistente.setFechaPedido(pedido.getFechaPedido());
                pedidoExistente.setHoraPedido(pedido.getHoraPedido());
                pedido = pedidoExistente;
            }

            // Guardar el pedido
            entityManager.persist(pedido);

            // Asociar los detalles al pedido y guardarlos
            for (DetallePedidosEntity detalle : detalles) {
                detalle.setPedido(pedido.getIdPedido());
                entityManager.persist(detalle);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public String ultimoCodigo(){
        try  {
            TypedQuery<PedidoEntity> pedidoMax = DBConnection.entityManager.createNamedQuery("Pedido.ultimo", PedidoEntity.class);
            for(PedidoEntity p : pedidoMax.getResultList()){
                return p.getIdPedido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "00000";
    }



}
