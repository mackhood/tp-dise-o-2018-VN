package test.otros;

import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.DispositivosManager;
import dominio.regla.Regla;
import dominio.repositories.RepositorioReglaActuadorCondicion;
import dominio.sensor.Condicion;
import dominio.sensor.CondicionPorIgual;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.CondicionPorMenor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class testRegla {

    OrdenEncenderDI mockActuador;
    Regla regla;
    CondicionPorMayor mockCondicion;
    CondicionPorMenor otroMockCondicion;
    CondicionPorIgual mockCondicionNoCumplida;
    DispositivoInteligente mockDI;

    @Before
    public void setUp() {

        List<Condicion> listaCondiciones = new ArrayList<>();
        List<Intervalo> intervalos = new ArrayList<>();

        mockDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("unDI")
                .intervalosDeUso(intervalos).build());
        mockDI.encender();
        mockDI.apagar();

        mockActuador = Mockito.spy(new OrdenEncenderDI(mockDI));
        regla = new Regla(mockActuador, listaCondiciones);
        mockCondicion = Mockito.mock(CondicionPorMayor.class);
        otroMockCondicion = Mockito.mock(CondicionPorMenor.class);
        mockCondicionNoCumplida = Mockito.mock(CondicionPorIgual.class);
        mockCondicion.asociarA(regla);
        otroMockCondicion.asociarA(regla);

        Mockito.when(mockCondicion.cumpleCondicion()).thenReturn(true);
        Mockito.when(otroMockCondicion.cumpleCondicion()).thenReturn(true);
        Mockito.when(mockCondicionNoCumplida.cumpleCondicion()).thenReturn(false);

    }

    @Test
    public void testAgregarCondiciones() {

        regla.agregarCondicion(mockCondicionNoCumplida);
        assertEquals(3, regla.getCondicionesACumplir().size());
    }

    /* Hago este test para verificar que asociarA(regla) si hace lo que tiene que hacer, no se por que pero haciendo mocks de
     * las condiciones no funciona, cuando inicializandolas como objetos si.
     */

    @Test
    public void mismoTestSinMocks() {

        List<Condicion> listaCondiciones = new ArrayList<>();
        CondicionPorMayor c = new CondicionPorMayor(10, "t");
        CondicionPorMayor c2 = new CondicionPorMayor(10, "t");
        Regla regla = new Regla(mockActuador, listaCondiciones);
        c.asociarA(regla);
        c2.asociarA(regla);
        assertEquals(2, regla.getCondicionesACumplir().size());
    }

    @Test
    public void testReglaSerNotificadaCumplenTodas() {

        regla.chequearCondicionesYEjecutar();
        assertEquals(true, mockDI.estaEncendido());
    }

    @Test
    public void testReglaSerNotificadaNoCumplenTodas() {

        regla.agregarCondicion(mockCondicionNoCumplida);
        regla.chequearCondicionesYEjecutar();
        assertEquals(false, mockDI.estaEncendido());
    }

    @Test
    public void testCumpleTodasLasCondiciones() {

        assertEquals(true, regla.cumpleTodasLasCondiciones());
    }

    @Test
    public void testNoCumpleTodasLasCondiciones() {

        regla.agregarCondicion(mockCondicionNoCumplida);
        assertEquals(false, regla.cumpleTodasLasCondiciones());
    }

    @Ignore
    public void testRepo() {
        RepositorioReglaActuadorCondicion.getInstance().getSensorCalor().recibirMedicion(RepositorioReglaActuadorCondicion.getInstance().getMedicionCalor());

        Assert.assertEquals(true, DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(9)).estaEncendido());
    }

}
