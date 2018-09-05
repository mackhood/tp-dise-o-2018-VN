package test;


import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.AsignadorDeZonaService;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;




import org.junit.Before;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class testAsignadorDeZonaService {
    private AsignadorDeZonaService asignadorDeZonaService;
    private ZonaGeografica zona1;
    private ZonaGeografica zona2;
    private Cliente clienteMock;
    private Transformador transformadorMock;
    private List<ZonaGeografica> listaZonas;

    @Before
    public void setUp() {
        zona1 = mock (ZonaGeografica.class);
        zona2 = mock (ZonaGeografica.class);


        transformadorMock = mock(Transformador.class);


        listaZonas = new ArrayList();
        listaZonas.add(zona1);
        //listaZonas.add(zona2);
        asignadorDeZonaService = new AsignadorDeZonaService(listaZonas);

        clienteMock = mock (Cliente.class);


        when(zona1.conectarATransformadorCercano(clienteMock)).thenReturn(transformadorMock);
        when(clienteMock.getPosicion()).thenReturn(new Ubicacion(0,0));
        when(zona1.perteneceClienteAZona(clienteMock)).thenReturn(true);
        when(zona2.perteneceClienteAZona(clienteMock)).thenReturn(true);
        when(zona2.conectarATransformadorCercano(clienteMock)).thenReturn(null);



    }
    @Test
    public void testZonaYTranformdorParaCliente ()  {
        asignadorDeZonaService.buscarZonaCoberturaClienteYDevolverZona(clienteMock);
        verify(clienteMock).setTransformador(transformadorMock);
    }
    @Test
    public void testExisteZonaParaclientePeroNotransformador()  {
        List<ZonaGeografica> otraListaZonas = new ArrayList<>();
        otraListaZonas.add(zona2);
        AsignadorDeZonaService otroAsignadorDeZona = new AsignadorDeZonaService(otraListaZonas);
        otroAsignadorDeZona.buscarZonaCoberturaClienteYDevolverZona(clienteMock);
        verify(clienteMock).setTransformador(null);


    }
   /* @Test
    public void testNoExisteZonaCliente() {
    }
    }*/
}
