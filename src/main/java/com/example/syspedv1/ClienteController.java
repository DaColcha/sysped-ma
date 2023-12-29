package com.example.syspedv1;

import entity.ClienteEntity;
import entity.ProductoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class ClienteController {

    ClienteEntity cliente;
    private void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void registarCliente(ClienteEntity cliente) {
        enviarABD(cliente);
        setCliente(cliente);
    }

    public boolean clienteExiste(String cedula){
        ClienteEntity existente = null;

        try  {
            TypedQuery<ClienteEntity> clienteById=  DBConnection.entityManager.createNamedQuery
                    ("Cliente.byIdCliente", ClienteEntity.class);
            clienteById.setParameter(1, cedula);
            existente = clienteById.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(existente != null){
            setCliente(existente);
            return true;
        }
        return false;
    }

    private void actulizarCliente(ClienteEntity clienteExistente, ClienteEntity clienteNuevo){
        clienteExistente.setTelefono(clienteNuevo.getTelefono());
        clienteExistente.setDireccion(clienteNuevo.getDireccion());
        clienteExistente.setCorreoElectronico(clienteNuevo.getCorreoElectronico());
    }
    private void enviarABD(ClienteEntity cliente){
        EntityManager entityManager = DBConnection.entityManager;
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(cliente);

            transaction.commit();
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
