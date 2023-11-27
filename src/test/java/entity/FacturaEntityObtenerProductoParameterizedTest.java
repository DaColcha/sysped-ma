package entity;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class FacturaEntityObtenerProductoParameterizedTest {
    private String nombreProductoEsperado, codigoProducto;
    private static FacturaEntity factura;

    @BeforeClass
    public static void setUpClass() {
        factura = new FacturaEntity();

        // Para establecer una conexión inicial a la BD, y que el tiempo
        // de conexión no influya en las pruebas
        factura.obtenerProducto("001");
    }

    public FacturaEntityObtenerProductoParameterizedTest(String nombreProductoEsperado, String codigoProducto) {
        this.nombreProductoEsperado = nombreProductoEsperado;
        this.codigoProducto = codigoProducto;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> parametros() {
        ArrayList<Object[]> pruebas = new ArrayList<Object[]>();
        pruebas.add(new Object[]{"Huevos Revueltos", "001"});
        pruebas.add(new Object[]{"Tostadas", "002"});
        pruebas.add(new Object[]{"Café", "003"});
        pruebas.add(new Object[]{"Hamburguesa", "004"});
        pruebas.add(new Object[]{"Papas Fritas", "005"});
        return pruebas;
    }

    @Test(timeout = 25)
    public void given_parameters_when_obtain_product_then_ok() {
        assertEquals(this.nombreProductoEsperado, factura.obtenerProducto(this.codigoProducto).getNombreProducto());
    }
}