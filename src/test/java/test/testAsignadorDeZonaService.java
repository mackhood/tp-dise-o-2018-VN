package test;

import dominio.categoria.AsignadorDeCategoria;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.AsignadorDeZonaService;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;
import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;
import net.bytebuddy.asm.Advice.Thrown;
import dominio.entities.NoTieneDispositivoException;
import dominio.entities.ProcessingDataFailedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class testAsignadorDeZonaService {
    private AsignadorDeZonaService asignadorDeZonaService;
    private ZonaGeografica zona1;
    private ZonaGeografica zona2;
    private Cliente clientespy;
    private Transformador transformadorMock;

    @Before
    public void setUp() {
        zona1 = spy(ZonaGeografica.class);
        zona2 = new ZonaGeografica("Zona2",null,new Ubicacion(5,5),5.0);


        transformadorMock = mock(Transformador.class);


        List<ZonaGeografica> listaZonas = new ArrayList();
        listaZonas.add(zona1);
        //listaZonas.add(zona2);
        asignadorDeZonaService = new AsignadorDeZonaService(listaZonas);

        clientespy = spy(Cliente.class);

        when(zona1.devolverTransformadorCercano(clientespy.getPosicion())).thenReturn(transformadorMock);
        when(clientespy.getPosicion()).thenReturn(new Ubicacion(0,0));
        doReturn(transformadorMock).when(zona1).devolverTransformadorCercano(clientespy.getPosicion());


    }
    @Test
    public void testZonaParaCliente () {
        asignadorDeZonaService.buscarZonaCoberturaClienteYDevolverZona(clientespy);
        verify(zona2,times(1)).conectarATransformadorCercano(clientespy);
    }

}
