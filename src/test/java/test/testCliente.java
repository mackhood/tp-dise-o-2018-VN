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
import org.mockito.Mock;
import org.mockito.Mockito;

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
    
    private Convertidor convertidor;
    
    private SistemaInteligente unSI;

    private Fabricante fabricante;

    Categoria categoriaMocktest1;
    Categoria categoriaMocktest2;
    List<Categoria>  listaCategoriaMock;
    AsignadorDeCategoria asignadorMock;


    @Before
    public void setUp() {

        unSI = new SistemaInteligente();
        unDispositivoEncendido = mock(DispositivoEstandar.class);
        unDispositivoApagado = mock(DispositivoEstandar.class);
        //unDispositivoEncendido = new DispositivoEstandar("da", 500, fabricante);
        //unDispositivoApagado = new DispositivoEstandar("AireAcondicionado", 100, fabricante);
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


       when(unDispositivoEncendido.getConsumoTotal()).thenReturn(25.5);
       when(unDispositivoApagado.getConsumoTotal()).thenReturn(25.5);

        listaDispositivosEstandar.add(unDispositivoApagado);
        listaDispositivosEstandar.add(unDispositivoApagado);


        unClienteConDEyDI = spy(new Cliente("Fernando", "Sierra", "fer22", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar, listaDispInteligentes));
        unClienteSinDEyConDI = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente, listaDispInteligentes));
        when(otroDispositivo.getConsumoTotal()).thenReturn(200.0);

        unClienteConDEyDI.agregarDispositivoInteligente(unDIEncendido);
        unClienteConDEyDI.agregarDispositivoInteligente(unDIApagado);


        //Siguientes lineas Utilizadas para test de actualizar categoria y gastos aproximados.
        asignadorMock = mock(AsignadorDeCategoria.class, Mockito.CALLS_REAL_METHODS);
        categoriaMocktest1 = mock(Categoria.class);
        categoriaMocktest2 = mock(Categoria.class);

        when(categoriaMocktest1.cumpleCondicion(unClienteConDEyDI)).thenReturn(true);
        when(categoriaMocktest2.cumpleCondicion(unClienteConDEyDI)).thenReturn(false);

        listaCategoriaMock = new ArrayList<>();

        listaCategoriaMock.add(categoriaMocktest1);
        listaCategoriaMock.add(categoriaMocktest2);



    }

    @Test
    public void testPuntosAcumuladosDespuesDeAgregar2DICliente()
    {
        assertEquals(30.0, unClienteConDEyDI.puntosAcumulados());
    }

    @Test
    public void testPuntosAcumuladorDespuesDeAgregarAdaptadorAUnDE()
    {
        convertidor = new Convertidor();
        unClienteConDEyDI.agregarModuloAdaptador(convertidor, unDE);
        assertEquals(35.0, unClienteConDEyDI.puntosAcumulados());
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


    @Test
    public void testConsumoEnergeticoTotalDeUnCliente() {
        assertEquals(51.0, unClienteConDEyDI.consumoEnergeticoTotal());
        assertEquals(0.0, unClienteSinDEyConDI.consumoEnergeticoTotal());
    }


    @Test
    public void testVerificarActualizacionDeCategoriaCliente() throws ProcessingDataFailedException {

        Categoria unaCategoriaMockSeteada = mock(Categoria.class);

        when(unaCategoriaMockSeteada.getNombreCategoria()).thenReturn("CategoriaR1");
        unClienteConDEyDI.setCategoria(unaCategoriaMockSeteada);

        assertEquals("CategoriaR1", unClienteConDEyDI.nombreCategoria());


        AsignadorDeCategoria asignadorMock = mock(AsignadorDeCategoria.class, Mockito.CALLS_REAL_METHODS);

        when(asignadorMock.getObtenerCategoriasDelRepositorio()).thenReturn(listaCategoriaMock);

        when(categoriaMocktest1.getNombreCategoria()).thenReturn("CategoriaR2");

        asignadorMock.actualizarPara(unClienteConDEyDI);
        assertEquals("CategoriaR2", unClienteConDEyDI.nombreCategoria());

    }


    @Test
    public void testObtenerGastosAproximadosCliente() throws ProcessingDataFailedException {

        //Cargofijo=35.32
        //CargoVariable=0.644
        //ConsumoCliente=51.0
        //35.32+0.644*51.0= 68.164


        when(categoriaMocktest1.getCargoVariable()).thenReturn(0.644);
        double gasto = 35.32 + categoriaMocktest1.getCargoVariable() * unClienteConDEyDI.consumoEnergeticoTotal();



        when(asignadorMock.getObtenerCategoriasDelRepositorio()).thenReturn(listaCategoriaMock);

        when(categoriaMocktest1.calcularCostosPara(unClienteConDEyDI)).thenReturn(gasto);

        asignadorMock.actualizarPara(unClienteConDEyDI);

        unClienteConDEyDI.obtenerGastosAproximados();

        verify(categoriaMocktest1).calcularCostosPara(unClienteConDEyDI);


        assertEquals(68.164, unClienteConDEyDI.obtenerGastosAproximados());

    }
}
