package entity;

import com.example.syspedv1.FacturaController;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class FacturaEntityCodigoFacturaParameterizedTest {
    private String ultimoCodigo, codigoEsperado;
    private static FacturaController factura;

    @BeforeClass
    public static void setUpClass() {
        factura = new FacturaController();
    }

    public FacturaEntityCodigoFacturaParameterizedTest(String ultimoCodigo, String codigoEsperado) {
        this.ultimoCodigo = ultimoCodigo;
        this.codigoEsperado = codigoEsperado;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> parametros() {
        ArrayList<Object[]> pruebas = new ArrayList<Object[]>();
        pruebas.add(new Object[]{"00001", "00002"});
        pruebas.add(new Object[]{"00020", "00021"});
        pruebas.add(new Object[]{"00033", "00034"});
        pruebas.add(new Object[]{"00064", "00065"});
        pruebas.add(new Object[]{"00125", "00126"});
        pruebas.add(new Object[]{"00600", "00601"});
        pruebas.add(new Object[]{"01237", "01238"});
        pruebas.add(new Object[]{"00232", "00233"});
        pruebas.add(new Object[]{"12345", "12346"});
        pruebas.add(new Object[]{"03333", "03334"});
        return pruebas;
    }

    @Test
    public void given_parameters_when_generate_bill_code_then_ok() {
        System.out.println("Prueba 1 de FacturaEntity CodigoFactura ParameterizedTest");
        assertEquals(this.codigoEsperado, factura.generarCodigoFactura(ultimoCodigo));
    }

}