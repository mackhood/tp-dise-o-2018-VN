package test;

import Clases.Categoria.AsignadorDeCategoria;
import Clases.Categoria.Categoria;
import Clases.Dispositivo.*;
import Clases.Fabricante;
import Clases.Usuario.Cliente;
import Clases.Usuario.Domicilio;
import Clases.Usuario.ID;
import Clases.Usuario.TiposId;
import Clases.entities.ProcessingDataFailedException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class testCliente {

    private DispositivoEstandar unDispositivoEncendido;
    private DispositivoEstandar unDispositivoApagado;
    private Cliente unClienteConDEyDI;
    private Cliente unClienteSinDEyConDI;
    private Dispositivo otroDispositivo;

    private DispositivoEstandar unDE;
    private DispositivoInteligente unDIApagado;
    private DispositivoInteligente unDIEncendido;
    private DispositivoInteligente unDETransformado;

    private SistemaInteligente unSI;

    private Fabricante fabricante;

    @Before
    public void setUp() {

        unSI = new SistemaInteligente();
        unDispositivoEncendido = mock(DispositivoEstandar.class);
        unDispositivoApagado = mock(DispositivoEstandar.class);
        otroDispositivo = mock(Dispositivo.class);

        fabricante = new Fabricante(1);
        unDE = new DispositivoEstandar("a1", 300, fabricante);
        unDIApagado = new DispositivoInteligente("da", 500, fabricante);
        unDIEncendido = new DispositivoInteligente("AireAcondicionado", 100, fabricante);
        unDIEncendido.encender();
        unDIEncendido.serUsado(10);

        List<DispositivoEstandar> listaDispositivosEstandar = new ArrayList<>();

        listaDispositivosEstandar.add(unDE);
        List<DispositivoEstandar> listaDispositivosParaOtroCliente = new ArrayList<>();


        List<DispositivoInteligente> listaDispInteligentes = new ArrayList<>();


        /*
        when(unDispositivoEncendido.esCiertoEstado(new EstadoEncendido()).thenReturn(false);
        when(unDispositivoApagado.esCiertoEstado(EstadoDispositivo.APAGADO)).thenReturn(false);
        */
        when(unDispositivoEncendido.getConsumoTotal()).thenReturn(25.5);
        when(unDispositivoApagado.getConsumoTotal()).thenReturn(25.5);

        listaDispositivosEstandar.add(unDispositivoApagado);
        listaDispositivosEstandar.add(unDispositivoApagado);


        unClienteConDEyDI = spy(new Cliente("Fernando", "Sierra", "fer22", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar, listaDispInteligentes));
        unClienteSinDEyConDI = spy(new Cliente("Nicolas", "Sierra", "fer22", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente, listaDispInteligentes));
        when(otroDispositivo.getConsumoTotal()).thenReturn(200.0);

        unClienteConDEyDI.agregarDispositivoInteligente(unDIEncendido);
        unClienteConDEyDI.agregarDispositivoInteligente(unDIApagado);

    }

    @Test
    public void testPuntosAcumuladosDespuesDeAgregar2DICliente()
    {
        assertEquals(30.0, unClienteConDEyDI.puntosAcumulados());
    }

    @Test
    public void testPuntosAcumuladorDespuesDeAgregarAdaptadorAUnDE()
    {
        unClienteConDEyDI.agregarModuloAdaptador(unDE);
        assertEquals(55.0, unClienteConDEyDI.puntosAcumulados());
    }

    @Test
    public void testAlgunDIestaEncendidoClienteCon2DI()
    {
        assertEquals(true,unSI.algunDIencendido(unClienteConDEyDI));
    }

    @Test
    public void testCantidadDIencendidosClienteCon2DI()
    {
        assertEquals(1,unSI.cantidadDIencendidos(unClienteConDEyDI));
    }

    @Test
    public void testCantidadDIapagadosClienteCon2DI()
    {
        assertEquals(1,unSI.cantidadDIapagados(unClienteConDEyDI));
    }

    @Test
    public void testCantidadDispositivosClienteCon2DIy1DE()
    {
        assertEquals(5,unSI.cantidadDispositivos(unClienteConDEyDI));
    }


    /*
    @Ignore
    public void testClienteCon2DispositivosEstandaresLigando1ModuloAdaptadorAUnDispEstandarNuevo() {
        DispositivoEstandar dispEstandar = new DispositivoEstandar("asd", 100);
        unClienteCon2Dispositivos.agregarDispositivo(dispEstandar);
        unClienteCon2Dispositivos.agregarModuloAdaptador(dispEstandar);
        assertEquals(55.0, unClienteCon2Dispositivos.puntosAcumulados());
    }
    */

    /*
    @Test
    public void testCantidadDispositivosDeUnCliente() {
        assertEquals(2, unClienteCon2Dispositivos.cantidadDeDispositivos());
        assertEquals(0, unClienteSinDispositivos.cantidadDeDispositivos());

    }

    @Test
    public void testCantidadDispositivosApagadosDeUnCliente() {
        assertEquals(1, unClienteCon2Dispositivos.cantidadDeDispositivosEncendidos());
        assertEquals(0, unClienteSinDispositivos.cantidadDeDispositivosApagados());
    }

    @Test
    public void testCantidadDispositivosEncedidosDeUnCliente() {
        assertEquals(1, unClienteCon2Dispositivos.cantidadDeDispositivosEncendidos());
        assertEquals(0, unClienteSinDispositivos.cantidadDeDispositivosEncendidos());
    }
    */
    @Test
    public void testConsumoEnergeticoTotalDeUnCliente() {
        assertEquals(51.0, unClienteConDEyDI.consumoDEEnergeticoTotal());
        assertEquals(0.0, unClienteSinDEyConDI.consumoDEEnergeticoTotal());
    }

    /*
    @Test
    public void testVerififarCategoriaDeUnCliente() throws ProcessingDataFailedException {

        Categoria unaCategoriaMock = mock(Categoria.class);
        when(unaCategoriaMock.getNombreCategoria()).thenReturn("CategoriaR1");
        unClienteCon2Dispositivos.setCategoria(unaCategoriaMock);

        assertEquals("CategoriaR1", unClienteCon2Dispositivos.nombreCategoria());

        Categoria categoriaMock = mock(Categoria.class);
        when(categoriaMock.cumpleCondicion(unClienteCon2Dispositivos)).thenReturn(true);
        AsignadorDeCategoria asignadorMock = mock(AsignadorDeCategoria.class);
        when(asignadorMock.definirCategoriaPara(unClienteCon2Dispositivos)).thenReturn(unaCategoriaMock);
        when(unClienteCon2Dispositivos.asignadorDeCategoria()).thenReturn(asignadorMock);
        when(unaCategoriaMock.getNombreCategoria()).thenReturn("CategoriaR2");

        unClienteCon2Dispositivos.actualizarCategoria();
        assertEquals("CategoriaR2", unClienteCon2Dispositivos.nombreCategoria());

    }



    @Test
    public void testObtenerGastosAproximadosCliente() throws ProcessingDataFailedException {

        //Cargofijo=35.32
        //CargoVariable=0.644
        //ConsumoCliente=51.0
        //35.32+0.644*51.0= 68.164

        Categoria categoriaMock = mock(Categoria.class);
        when(categoriaMock.getCargoVariable()).thenReturn(0.644);
        Double gasto = 35.32 + categoriaMock.getCargoVariable() * unClienteCon2Dispositivos.consumoEnergeticoTotal();
        AsignadorDeCategoria asignadorMock = mock(AsignadorDeCategoria.class);
        when(asignadorMock.definirCategoriaPara(unClienteCon2Dispositivos)).thenReturn(categoriaMock);
        when(categoriaMock.calcularCostosPara(unClienteCon2Dispositivos)).thenReturn(gasto);
        doReturn(asignadorMock)
                .when(unClienteCon2Dispositivos)
                .asignadorDeCategoria();
        unClienteCon2Dispositivos.actualizarCategoria();
        unClienteCon2Dispositivos.obtenerGastosAproximados();


        verify(categoriaMock).calcularCostosPara(unClienteCon2Dispositivos);


        assertEquals(68.164, unClienteCon2Dispositivos.obtenerGastosAproximados());

    }

    @Test
    public void testObtenerGastosAproximadosConMockCategoria() throws ProcessingDataFailedException {

        Categoria categoriaMock = mock(Categoria.class);

        //Se le agrega otro dispositivo
        unClienteCon2Dispositivos.agregarDispositivo(otroDispositivo);

        //Se actualiza su categoria
        unClienteCon2Dispositivos.actualizarCategoria();

        //Cargofijo=500
        //CargoVariable=0.7
        //ConsumoCliente=251.0
        //500+0.7*251.0=196.964
        double costo = 500.0 + 0.7 * unClienteCon2Dispositivos.consumoEnergeticoTotal();

        when(categoriaMock.calcularCostosPara(unClienteCon2Dispositivos)).thenReturn(costo);
        assertEquals(675.7, categoriaMock.calcularCostosPara(unClienteCon2Dispositivos));
    }

    @Test
    public void testActualizacionCategoriaClienteConSpy() throws ProcessingDataFailedException {

        List<Dispositivo> listaDispositivosParaOtroCliente = new ArrayList<>();
        Cliente unClienteSpy = spy(new Cliente("Nicolas", "Sierra", "fer22", new ID(TiposId.DNI, "200"), new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente));
        Categoria unaCategoriaMock = mock(Categoria.class);
        AsignadorDeCategoria asignadorMock = mock(AsignadorDeCategoria.class);
        when(asignadorMock.definirCategoriaPara(unClienteSpy)).thenReturn(unaCategoriaMock);
        doReturn(asignadorMock)
                .when(unClienteSpy)
                .asignadorDeCategoria();
        unClienteSpy.actualizarCategoria();
        verify(asignadorMock).definirCategoriaPara(unClienteSpy);
        assertEquals(unaCategoriaMock, unClienteSpy.getCategoria());
    }

    */
}
