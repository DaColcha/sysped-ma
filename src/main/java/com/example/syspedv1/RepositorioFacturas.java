package com.example.syspedv1;

import entity.ClienteEntity;
import entity.FacturaEntity;
import entity.PedidoEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class RepositorioFacturas {

    private FacturaEntity factura;

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public void registarFactura(FacturaEntity factura) {
        this.guardarFacturaEnBD(factura);
        this.setFactura(factura);
    }

    /*private void actulizarEstadoPedido(String nuevoEstadoPedido){
        EntityManager entityManager = DBConnection.entityManager;
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.find(PedidoEntity.class, this.factura.getPedido());
            this.cliente.setTelefono(clienteNuevo.getTelefono());

            transaction.commit();

        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }*/


    private void guardarFacturaEnBD(FacturaEntity factura){
        EntityManager entityManager = DBConnection.entityManager;
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(factura);

            transaction.commit();
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
