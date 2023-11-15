package com.example.syspedv1;

import entity.DetallePedidosEntity;
import entity.PedidoEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HistorialPedidos {
    private List<PedidoEntity> pedididos;

    public void agregarPedido(String num, List<Integer> cantidad, List<ProductoEntity> p){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(num);
        pedido.setFechaPedido(new java.sql.Date(pedido.obtenerFecha().getTime()));
        pedido.setEstado("Pendiente");
        pedido.setHoraPedido(pedido.obtenerHoraActual());
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
        guardarEnDB(pedido,detalles);
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
