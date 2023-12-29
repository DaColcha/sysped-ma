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
    public void agregarPedido(String idPedido, List<Integer> cantidades, List<ProductoEntity> productos,List<String> Detalles){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(idPedido);
        pedido.setFechaPedido(new java.sql.Date(pedido.obtenerFecha().getTime()));
        pedido.setEstado("Pendiente");
        pedido.setHoraPedido(pedido.obtenerHoraActual());
        guardarEnDB(pedido, obtenerDetallesPedido(idPedido, cantidades, productos,Detalles));
    }
    private static List<DetallePedidosEntity> obtenerDetallesPedido(String idPedido, List<Integer> cantidades, List<ProductoEntity> productos,List<String> Detalles) {
        int contador = 0;
        List<DetallePedidosEntity> detalles = new ArrayList<>();
        DetallePedidosEntity detalle;
        for (ProductoEntity producto : productos) {
            detalle = new DetallePedidosEntity();
            detalle.setCantidad(cantidades.get(contador));
            detalle.setPedido(idPedido);
            detalle.setProducto(producto.getIdProducto());
            detalle.setEspecificaciones(Detalles.get(contador));
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
            actualizarPedido(pedidoExistente, pedido);

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

    public void actualizarPedido(PedidoEntity pedidoExistente, PedidoEntity pedido){
        if (pedidoExistente != null) {
            pedidoExistente.setEstado(pedido.getEstado());
            pedidoExistente.setFechaPedido(pedido.getFechaPedido());
            pedidoExistente.setHoraPedido(pedido.getHoraPedido());
        }
    }

    public String ultimoCodigo(){
        String code = "00000";
        try  {
            List<PedidoEntity> pedidosOrdenDescendente = DBConnection.entityManager.createNamedQuery
                    ("Pedido.ultimo", PedidoEntity.class).getResultList();
            if (pedidosOrdenDescendente != null){
                code = pedidosOrdenDescendente.get(0).getIdPedido();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

}
