package test.otros;

import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testParaTransformador {

    List<Transformador> listaTransformadorOtraZona = new ArrayList<>();
    private Cliente clienteMock1;
    private Cliente clienteMock2;
    private Cliente clienteMock3;
    private Transformador transformador1_Zona1;
    private Transformador transformador2_Zona1;
    private Transformador transformador_OtraZona;
    private ZonaGeografica zonaGeografica1;
    private ZonaGeografica zonaGeografica2;
    private Ubicacion ubicacionCliente;
    private Ubicacion ubicacionZona1;
    private Ubicacion ubicacionZona2;
    private List<Transformador> listaTransformadorZona1 = new ArrayList<>();

    @Before
    public void setUp() {

        clienteMock1 = mock(Cliente.class);
        clienteMock2 = mock(Cliente.class);
        clienteMock3 = mock(Cliente.class);

        transformador_OtraZona = mock(Transformador.class);

        listaTransformadorOtraZona.add(transformador_OtraZona);

        List<Cliente> listaClientesTransformador1 = new ArrayList<>();
        List<Cliente> listaClientesTransformador2 = new ArrayList<>();

        transformador1_Zona1 = new Transformador(listaClientesTransformador1, new Ubicacion(20.0, 20.0));
        transformador2_Zona1 = new Transformador(listaClientesTransformador2, new Ubicacion(10.0, 10.0));

        transformador1_Zona1.agregarCliente(clienteMock1);
        transformador1_Zona1.agregarCliente(clienteMock2);
        transformador2_Zona1.agregarCliente(clienteMock3);

        when(clienteMock1.consumoEnergeticoTotal()).thenReturn(350.0);
        when(clienteMock2.consumoEnergeticoTotal()).thenReturn(400.0);
        when(clienteMock3.consumoEnergeticoTotal()).thenReturn(200.0);

        ubicacionCliente = new Ubicacion(1, 1);
        when(clienteMock3.getUbicacion()).thenReturn(ubicacionCliente);

        when(transformador_OtraZona.energiaConsumidaClientes()).thenReturn(600.0);

        listaTransformadorZona1.add(transformador1_Zona1);
        listaTransformadorZona1.add(transformador2_Zona1);

        ubicacionCliente = new Ubicacion(3, 3);
        ubicacionZona1 = new Ubicacion(2, 2);
        ubicacionZona2 = new Ubicacion(4, 4);
        when(clienteMock1.getUbicacion()).thenReturn(ubicacionCliente);
        when(clienteMock1.consumoDispositivosInteligentes()).thenReturn(100.0);
        when(clienteMock2.consumoDispositivosInteligentes()).thenReturn(100.0);

        zonaGeografica1 = new ZonaGeografica("Zona1", listaTransformadorZona1, ubicacionZona1, 10.0);
        zonaGeografica2 = new ZonaGeografica("Zona2", listaTransformadorOtraZona, ubicacionZona2, 20.0);

    }

    @Test
    public void testConsumoTransformador() {
        assertEquals(750.0, transformador1_Zona1.energiaConsumidaClientes());
    }

    @Test
    public void testCalcularDistanciaCliente() {
        assertEquals(26.870057685088806, transformador1_Zona1.calcularDistancia(clienteMock3.getUbicacion()));

    }

    @Test
    public void testSuministroActualPorDispositivosInteligentesDeClientes() {
        assertEquals(200.0, transformador1_Zona1.suministroActual());
    }

    @Test
    public void testAgregarCliente() {

        Cliente clienteTest = Mockito.mock(Cliente.class);
        transformador1_Zona1.agregarCliente(clienteTest);
        assertTrue(transformador1_Zona1.getUsuariosConectados().contains(clienteTest));
    }
}
