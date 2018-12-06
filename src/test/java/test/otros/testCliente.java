package test.otros;

import dominio.categoria.AsignadorDeCategoria;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.entities.NoTieneDispositivoException;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class testCliente {

    Categoria mockCategoria;
    AsignadorDeCategoria asignadorMock;
    private Cliente unClienteConDEyDI;
    private Cliente unClienteSinDEyConDI;
    private Dispositivo mockDispositivo;
    private DispositivoInteligente otroMockDI;
    private Conversor moduloAdaptador;
    private DispositivoEstandar mockDE;
    private DispositivoInteligente mockDI;

    private Intervalo i1;
    private Intervalo i2;

    @Before
    public void setUp() {

        List<Intervalo> intervalosDeUsoDI1 = new ArrayList<>();
        List<Intervalo> intervalosDeUsoDI2 = new ArrayList<>();

        mockCategoria = Mockito.spy(new Categoria("CategoriaTest", 0, 3000, 50.0, 20.0));
        mockDispositivo = Mockito.mock(Dispositivo.class);
        moduloAdaptador = new Conversor();

        i1 = spy(new Intervalo(LocalDateTime.of(2018, 10, 2, 19, 00), LocalDateTime.of(2018, 10, 2, 20, 00)));
        i2 = spy(new Intervalo(LocalDateTime.of(2018, 10, 2, 4, 30), LocalDateTime.of(2018, 10, 2, 23, 30)));

        intervalosDeUsoDI1.add(i1);
        intervalosDeUsoDI2.add(i2);

        mockDE = Mockito.spy(new DispositivoEstandar.DispositivoEstandarBuilder("televisor")
                .equipoConcreto("Color de tubo fluorescente de 29 a 34").consumoEstimadoPorHora(30.0).horasDeUso(2.0)
                .build());

        mockDI = Mockito
                .spy(new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").equipoConcreto("LED de 24")
                        .consumoEstimadoPorHora(500.0).intervalosDeUso(intervalosDeUsoDI1).build());

        otroMockDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado")
                .equipoConcreto("De 2200 frigorias").consumoEstimadoPorHora(100.0)
                .intervalosDeUso(intervalosDeUsoDI2).build());


        List<DispositivoEstandar> listaDispositivosEstandar = new ArrayList<>();

        List<DispositivoEstandar> listaDispositivosParaOtroCliente = new ArrayList<>();

        List<DispositivoInteligente> listaDispInteligentes = new ArrayList<>();

        unClienteConDEyDI = spy(new Cliente("Fernando", "Sierra", "fer22", "password", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar, listaDispInteligentes));
        unClienteSinDEyConDI = spy(new Cliente("Nicolas", "Sierra", "fer25", "password", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente,
                listaDispInteligentes));
        unClienteConDEyDI.agregarDispositivoInteligente(otroMockDI);
        unClienteConDEyDI.agregarDispositivoInteligente(mockDI);
        unClienteConDEyDI.agregarDispositivoEstandar(mockDE);

        asignadorMock = mock(AsignadorDeCategoria.class);

    }

    @Test
    public void testPuntosAcumuladosDespuesDeAgregar2DICliente() {
        assertEquals(30.0, unClienteConDEyDI.puntosAcumulados());
    }

    @Test(expected = NoTieneDispositivoException.class)
    public void testNoPuedeAgregarModulo() throws NoTieneDispositivoException {
        unClienteSinDEyConDI.agregarModuloAdaptador(moduloAdaptador, mockDE);
    }

    @Test
    public void testTieneDispositivo() {

        assertTrue(unClienteConDEyDI.tieneDE(mockDE));
    }

    @Test
    public void testTieneAlgunDispositivoEncendido() {

        assertFalse(unClienteConDEyDI.algunDispositivoEncendido());
    }

    @Test
    public void testCuantosDIEncendidos() {

        mockDI.encender();
        otroMockDI.encender();
        assertEquals(2, unClienteConDEyDI.cantidadDeDIEncendidos());
    }

    @Test
    public void testCuantosDIApagados() {

        assertEquals(2, unClienteConDEyDI.cantidadDeDIApagados());
    }

    @Test
    public void testAgregarDI() {

        DispositivoInteligente unDITest = Mockito.mock(DispositivoInteligente.class);
        unClienteConDEyDI.agregarDispositivoInteligente(unDITest);
        assertTrue(unClienteConDEyDI.getDispositivosInteligentes().contains(unDITest));
    }

    @Test
    public void testAgregarDE() {

        DispositivoEstandar unDETest = Mockito.mock(DispositivoEstandar.class);
        unClienteConDEyDI.agregarDispositivoEstandar(unDETest);
        assertTrue(unClienteConDEyDI.tieneDE(unDETest));
    }

    @Test
    public void testCuantosDispositivosTiene() {

        assertEquals(3, unClienteConDEyDI.cantidadDeDispositivos());
    }

    @Test
    public void testConsumoDICliente() {

        assertEquals(2400.0, unClienteConDEyDI.consumoDispositivosInteligentes(), 0);
    }

    @Test
    public void testSacarDE() {

        unClienteConDEyDI.sacarDispositivoEstandarLista(mockDE);
        assertFalse(unClienteConDEyDI.tieneDE(mockDE));
    }

    @Test(expected = NoTieneDispositivoException.class)
    public void testNoPuedeUsarDipositivoQueNoTiene() throws NoTieneDispositivoException {
        unClienteSinDEyConDI.usarDispositivo(mockDE, 5);
    }

    @Test
    public void testClienteUsarDispositivo() {

        unClienteConDEyDI.agregarDispositivoEstandar(mockDE);
        unClienteConDEyDI.usarDispositivo(mockDE, 3.0);
        assertEquals(5.0, mockDE.getHorasDeUso());
    }

    @Test
    public void testConsumoEnergeticoTotalDeUnCliente() {
        assertEquals(2460.0, unClienteConDEyDI.consumoEnergeticoTotal());
    }

    @Test
    public void testAhorroAutomatico() {
        unClienteConDEyDI.activarAhorroAutomatico();
        assertTrue(unClienteConDEyDI.estaEnModoAhorroAutomatico());
    }

    @Test
    public void testGastosCliente() {

        // Consumo Total 2460
        // Cargo Variable 20
        // Cargo Fijo 50
        // -------------------
        // Gastos 2460*20 +50 -> 49200+50 -> 49250
        unClienteConDEyDI.setCategoria(mockCategoria);
        assertEquals(49250.0, unClienteConDEyDI.obtenerGastosAproximados());
    }

    @Test
    public void testPuntosAcumuladorDespuesDeAgregarAdaptadorAUnDE() throws NoTieneDispositivoException {
        unClienteConDEyDI.agregarModuloAdaptador(moduloAdaptador, mockDE);
        assertEquals(40.0, unClienteConDEyDI.puntosAcumulados());
    }
}
