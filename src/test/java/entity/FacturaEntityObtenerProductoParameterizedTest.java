package entity;

import com.example.syspedv1.FacturaController;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class FacturaEntityObtenerProductoParameterizedTest {
    private String nombreProductoEsperado, codigoProducto;
    private static FacturaController factura;

    @BeforeClass
    public static void setUpClass() {
        factura = new FacturaController();

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

    @Test(timeout = 100000)
    public void given_parameters_when_obtain_product_then_ok() {
        System.out.println("Prueba 1 de FacturaEntity ObtenerProducto ParameterizedTest");
        assertEquals(this.nombreProductoEsperado, factura.obtenerProducto(this.codigoProducto).getNombreProducto());
    }
}