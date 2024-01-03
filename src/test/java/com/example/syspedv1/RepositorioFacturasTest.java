package com.example.syspedv1;

import entity.ClienteEntity;
import entity.FacturaEntity;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositorioFacturasTest {

    static RepositorioFacturas repositorioFacturas = null;

    @BeforeClass
    public static void setUp() {
        repositorioFacturas = new RepositorioFacturas();
        System.out.println("setUp()");
    }

    @Test
    public void given_factura_when_register_then_ok(){
        System.out.println("Prueba 1 de Repositorio Facturas Test");
        FacturaEntity newFactura = new FacturaEntity();
        newFactura.setNumFactura("00100");
        newFactura.setCliente("1725887796");
        newFactura.setPedido("00001");
        newFactura.setMetodoPago("Efectivo");
        newFactura.setFechaFactura(new java.sql.Date(newFactura.obtenerFechaFacturacion().getTime()));

        repositorioFacturas.registarFactura(newFactura);
        String expect = newFactura.toString();
        assertEquals(expect, repositorioFacturas.getFactura().toString());
    }

}
