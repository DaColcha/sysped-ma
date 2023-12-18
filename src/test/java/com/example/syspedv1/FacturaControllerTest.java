package com.example.syspedv1;

import entity.DetallePedidosEntity;
import entity.ProductoEntity;
import org.junit.BeforeClass;
import org.junit.Test;


import javax.naming.InterruptedNamingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FacturaControllerTest {

    static FacturaController factura = null;

    @BeforeClass
    public static void setUp() {
        factura = new FacturaController();
        System.out.println("setUp()");
    }

    @Test
    public void given_a_product_and_details_when_calcularPrecio_then_ok() {
        System.out.println("Prueba 1");
        BigDecimal expect = new BigDecimal("200.00");
        ProductoEntity producto = new ProductoEntity();
        producto.setPrecio(new BigDecimal("40.00"));
        DetallePedidosEntity detalle = new DetallePedidosEntity();
        detalle.setNumDetalle(5);
        assertEquals(expect, factura.calcularPrecio(producto, detalle));

    }

    @Test
    public void given_two_details_when_get_subtotal_then_ok() {

        System.out.println("Prueba 2");
        BigDecimal expect = new BigDecimal("30.95");

        // Crear detalles
        List<DetallePedidosEntity> detallesPedido = new ArrayList<>();
        DetallePedidosEntity detalle1 = new DetallePedidosEntity();
        DetallePedidosEntity detalle2 = new DetallePedidosEntity();

        detalle1.setNumDetalle(2);
        detalle1.setProducto("001");
        detalle2.setNumDetalle(3);
        detalle2.setProducto("002");

        detallesPedido.add(detalle1);
        detallesPedido.add(detalle2);
        assertEquals(expect, factura.calcularSubtotal(detallesPedido));
    }


    //prueba de desempeño

    @Test(timeout = 100000)
    public void given_calcularSubTotal_should_be_executed_in() {
        System.out.println("Prueba 3");
        List<DetallePedidosEntity> detallesPedido = new ArrayList<>();
        // Agregar un gran número de detalles a la factura
        for (int i = 0; i < 100; i++) {
            DetallePedidosEntity detalle = new DetallePedidosEntity();
            detalle.setNumDetalle(2);
            detalle.setProducto("001");
            detallesPedido.add(detalle);
        }
        factura.calcularSubtotal(detallesPedido);
        assertEquals(new BigDecimal("1598.00"),factura.calcularSubtotal(detallesPedido));
    }

    @Test
    public void given_Subtotal_whenCalcularIva_thenOk() {
        System.out.println("Prueba 4");
        double expected = 18.19;
        double actual = factura.calcularIVA(new BigDecimal("151.60")).doubleValue();
        assertEquals(expected, actual, 0.01);
    }

    // Prueba de excepción
    @Test(expected= NullPointerException.class)
    public void given_a_details_list_null_then_exception(){
        System.out.println("Prueba 5");
        assertEquals(new BigDecimal(0), factura.calcularSubtotal(null));
    }

    @Test
    public void given_the_last_code_when_generarCodigoFactura_then_Ok(){
        System.out.println("Prueba 6");
        String expected = "00005";
        assertEquals(expected, factura.generarCodigoFactura("00004"));
    }
    // Prueba de excepción
    @Test(expected = NumberFormatException.class)
    public void given_a_string_null_when_generarCodigoFactura_then_exception(){
        System.out.println("Prueba 7");
        String expected = "00001";
        assertEquals(expected, factura.generarCodigoFactura(null));

    }

    @Test
    public void given_subtotal_and_Iva_when_calcularTotal_then_Ok(){
        System.out.println("Prueba 8");
        double expect = 34.23;
        double resultado = factura.calcularTotal(new BigDecimal("30.56"),
                                                 new BigDecimal("3.67")).doubleValue();
        assertEquals(expect,resultado,0.01);

    }

}