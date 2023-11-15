package com.example.syspedv1;

import entity.DetallePedidosEntity;
import entity.PedidoEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilsDB {
    public void GuardarPedido(String num, List<Integer> cantidad, List<ProductoEntity> p) {
            PedidoEntity pedido = new PedidoEntity();
            pedido.setIdPedido(num);
            pedido.setFechaPedido(new java.sql.Date(obtenerFecha().getTime()));
            pedido.setEstado("Pendiente");
            pedido.setHoraPedido(obtenerHoraActual());
            int contador = 0;
            List<DetallePedidosEntity> detalles = new ArrayList<>();
            for (ProductoEntity aux : p) {
                DetallePedidosEntity detalle = new DetallePedidosEntity();
                detalle.setNumDetalle(cantidad.get(contador));
                detalle.setPedido(num);
                detalle.setProducto(aux.getIdProducto());
                contador++;
                detalles.add(detalle);
            }
            guardar(pedido,detalles);
    }
    // Método para obtener la fecha en formato dd/MM/yyyy
    public Date obtenerFecha() {
    return new java.util.Date();
    }

    // Método para obtener la hora hasta minutos
    public Time obtenerHoraActual() {
        java.util.Date utilDate = new java.util.Date();
        return new Time(utilDate.getTime());
    }
    public void guardar(PedidoEntity pedido, List<DetallePedidosEntity> detalles){
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


}
