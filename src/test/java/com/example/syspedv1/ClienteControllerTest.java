package com.example.syspedv1;

import entity.ClienteEntity;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClienteControllerTest {

    static ClienteController clienteController = null;

    @BeforeClass
    public static void setUp() {
        clienteController = new ClienteController();
        System.out.println("setUp()");
    }

    @Test
    public void given_cedula_when_cliente_exists_then_ok(){
        System.out.println("Prueba 1 de Cliente Controller Test");
        assertEquals(true, clienteController.clienteExiste("0605393222"));
    }

    @Test
    public void given_empty_cedula_when_cliente_exists_then_false(){
        System.out.println("Prueba 2 de Cliente Controller Test");
        assertEquals(false, clienteController.clienteExiste("1234567890"));
    }

    @Test
    public void given_null_client_then_exception(){
        System.out.println("Prueba 3 de Cliente Controller Test");
        assertEquals(false, clienteController.clienteExiste(null));
    }

    @Test
    public void given_client_when_register_then_exists(){
        System.out.println("Prueba 4 de Cliente Controller Test");
        ClienteEntity newClient = new ClienteEntity();
        newClient.setCedula("1725887796");
        newClient.setNombres("Jose Daniel");
        newClient.setApellidos("Cobos Jaramillo");
        newClient.setTelefono("0979349623");
        newClient.setDireccion("La Vicentina");
        newClient.setCorreoElectronico("danjs@email.com");

        clienteController.registarCliente(newClient);
        assertEquals(true, clienteController.clienteExiste(newClient.getCedula()));
    }

    @Test
    public void given_existing_client_when_update_then_ok(){
        System.out.println("Prueba 5 de Cliente Controller Test");
        ClienteEntity newClient = new ClienteEntity();
        newClient.setCedula("1725887796");
        newClient.setNombres("Jose Daniel");
        newClient.setApellidos("Cobos Jaramillo");
        newClient.setTelefono("0913151269");
        newClient.setDireccion("Guapulo");
        newClient.setCorreoElectronico("danjs@email.com");

        clienteController.registarCliente(newClient);
        String expect = newClient.toString();
        assertEquals(expect, clienteController.cliente.toString());
    }
}