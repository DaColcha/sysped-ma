package com.example.syspedv1;

import entity.ClienteEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClienteTest {

    private static Cliente cliente = null;

    @BeforeClass
    public static void setUpClass() {
        cliente = Mockito.mock(Cliente.class);
    }

    @Test
    public void given_id_when_obtain_client_then_found() {
        System.out.println("Prueba 1 de Cliente Test");
        Mockito.when(cliente.verificarExistencia(Mockito.any())).thenReturn(true);
        boolean clienteExiste = cliente.verificarExistencia("1722134956");
        Mockito.when(cliente.obtenerCliente(Mockito.any())).
                thenReturn(clienteExiste ? new ClienteEntity() : null);
        assertNotNull(cliente.obtenerCliente("1722134956"));
    }

    @Test
    public void given_client_data_when_register_client_then_ok() {
        System.out.println("Prueba 2 de Cliente Test");
        Mockito.when(cliente.verificarExistencia(Mockito.any())).thenReturn(false);
        boolean clienteExiste = cliente.verificarExistencia("1722126958");
        Mockito.when(cliente.registrarCliente(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).
                thenReturn(!clienteExiste);
        assertTrue(cliente.registrarCliente("1722134956", "Peter", "Parker", "test@gmail.com", "9999999999"));
    }
}