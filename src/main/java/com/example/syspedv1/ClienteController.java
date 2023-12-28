package com.example.syspedv1;

import entity.ClienteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ClienteController {

    ClienteEntity cliente;
    EntityManager entityManager = DBConnection.entityManager;
    private void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void registarCliente(ClienteEntity cliente) {
        enviarABD(cliente);
        setCliente(cliente);
    }

    public boolean clienteExiste(String cedula){
        ClienteEntity existente = obtenerExistente(cedula);
        if(existente != null){
            this.cliente = existente;
            return true;
        }
        return false;
    }

    public ClienteEntity obtenerExistente(String cedula){
        return entityManager.find(ClienteEntity.class, cedula);
    }

    private void actulizarCliente(ClienteEntity clienteExistente, ClienteEntity clienteNuevo){
        clienteExistente.setTelefono(clienteNuevo.getTelefono());
        clienteExistente.setDireccion(clienteNuevo.getDireccion());
        clienteExistente.setCorreoElectronico(clienteNuevo.getCorreoElectronico());
    }
    private void enviarABD(ClienteEntity cliente){
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            //Si ya existe actualizamos
            if(clienteExiste(cliente.getCedula())){
                actulizarCliente(obtenerExistente(cliente.getCedula()), cliente);
            }else{
                //guardamos
                entityManager.persist(cliente);
            }

            transaction.commit();
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
