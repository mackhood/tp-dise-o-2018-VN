package test.otros;

import dominio.entities.ZonaNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.ServicioAsignadorDeZonaService;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class testServicioAsignadorDeZonaService {
    private ServicioAsignadorDeZonaService servicioAsignadorDeZonaService;
    private ZonaGeografica zona1;
    private ZonaGeografica zona2;
    private Cliente clienteMock;
    private Transformador transformadorMock;

    @Before
    public void setUp() {
        zona1 = mock(ZonaGeografica.class);
        zona2 = mock(ZonaGeografica.class);

        transformadorMock = mock(Transformador.class);


        List<ZonaGeografica> listaZonas = new ArrayList<>();
        listaZonas.add(zona1);
        listaZonas.add(zona2);

        servicioAsignadorDeZonaService = new ServicioAsignadorDeZonaService(listaZonas);

        clienteMock = mock(Cliente.class);

        when(zona2.conectarATransformadorCercano(clienteMock)).thenReturn(transformadorMock);
        when(clienteMock.getUbicacion()).thenReturn(new Ubicacion(0, 0));
        when(zona1.perteneceClienteAZona(clienteMock)).thenReturn(true);
        when(zona2.perteneceClienteAZona(clienteMock)).thenReturn(true);
        when(zona1.distanciaAcliente(clienteMock)).thenReturn(3.0);
        when(zona2.distanciaAcliente(clienteMock)).thenReturn(1.0);

    }

    @Test
    public void testAsignarTransformadorParaCliente() {
        servicioAsignadorDeZonaService.buscarTransformadorCercanoPara(clienteMock);
        verify(clienteMock).setTransformador(transformadorMock);
    }

    @Test(expected = ZonaNullException.class)
    public void testNoExisteZonaParaCliente() {
        List<ZonaGeografica> otraListaZonas = new ArrayList<>();
        ZonaGeografica zona3 = mock(ZonaGeografica.class);
        when(zona3.perteneceClienteAZona(clienteMock)).thenReturn(false);
        otraListaZonas.add(zona3);
        ServicioAsignadorDeZonaService otroAsignadorDeZona = new ServicioAsignadorDeZonaService(otraListaZonas);
        otroAsignadorDeZona.buscarTransformadorCercanoPara(clienteMock);
    }
}
