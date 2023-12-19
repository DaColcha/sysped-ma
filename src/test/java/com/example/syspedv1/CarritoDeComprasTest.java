package com.example.syspedv1;
import entity.ProductoEntity;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class CarritoDeComprasTest {
    private CarritoDeCompras carrito;

    @Before
    public void setUp() {
        carrito = new CarritoDeCompras();
    }

    @Test
    public void givenNewProduct_whenAgregarProducto_thenProductAdded() {
        System.out.println("Prueba 1 del Carrito de Compras");
        ProductoEntity producto = new ProductoEntity("001", "Producto1", new BigDecimal("20.00"));
        carrito.agregarProducto(producto, 2, "Detalle");
        assertTrue(carrito.mostrarCarrito().contains("Producto1"));
    }

    @Test
    public void givenExistingProduct_whenEliminarProducto_thenProductRemoved() {
        System.out.println("Prueba 2 del Carrito de Compras");
        ProductoEntity producto = new ProductoEntity("002", "Producto2", new BigDecimal("30.00"));
        carrito.agregarProducto(producto, 3, "Detalle");
        carrito.eliminarProducto("002");
        assertFalse(carrito.mostrarCarrito().contains("Producto2"));
    }

    @Test
    public void givenUpdatedQuantity_whenActualizar_thenQuantityUpdated() {
        System.out.println("Prueba 3 del Carrito de Compras");
        ProductoEntity producto = new ProductoEntity("003", "Producto3", new BigDecimal("25.00"));
        carrito.agregarProducto(producto, 1, "Detalle");
        carrito.actualizar("003", 5, null);
        assertTrue(carrito.mostrarCarrito().contains("<td class=\"data-edit\" contenteditable=\"true\">5</td>"));
    }
}