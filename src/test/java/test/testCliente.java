package test;

import Clases.*;
import Clases.entities.ProcessingDataFailedException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class testCliente {

    private Dispositivo unDispositivoEncendido;
    private Dispositivo unDispositivoApagado;
    private Cliente unClienteCon2Dispositivos;
    private Cliente unClienteSinDispositivos;
    private Dispositivo otroDispositivo;

    @Before
    public void setUp()  {

        unDispositivoEncendido = mock(Dispositivo.class);
        unDispositivoApagado = mock(Dispositivo.class);
        otroDispositivo = mock(Dispositivo.class);

        List<Dispositivo> listaDispositivos = new ArrayList<>();
        List<Dispositivo> listaDispositivosParaOtroCliente = new ArrayList<>();
        unClienteCon2Dispositivos = new Cliente("Fernando","Sierra","fer22",new ID(TiposIdEnum.DNI,"200"),new Domicilio("bariloche",3118,1,'a'),250,listaDispositivos);
        unClienteSinDispositivos= new Cliente("Nicolas","Sierra","fer22",new ID(TiposIdEnum.DNI,"200"),new Domicilio("bariloche",3118,1,'a'),250,listaDispositivosParaOtroCliente);

        unClienteCon2Dispositivos.agregarDispositivo(unDispositivoEncendido);
        unClienteCon2Dispositivos.agregarDispositivo(unDispositivoApagado);

        when(unDispositivoEncendido.isEncendido()).thenReturn(true);
        when(unDispositivoApagado.isEncendido()).thenReturn(false);
        when(unDispositivoEncendido.getConsumoTotal()).thenReturn(25.5);
        when(unDispositivoApagado.getConsumoTotal()).thenReturn(25.5);
        when(otroDispositivo.getConsumoTotal()).thenReturn(200.0);

    }

    @Test
    public void testCantidadDispositivosDeUnCliente(){

        assertEquals(2,unClienteCon2Dispositivos.cantidadDeDispositivos());
        assertEquals(0,unClienteSinDispositivos.cantidadDeDispositivos());

    }
    @Test
    public void testCantidadDispositivosApagadosDeUnCliente(){
        assertEquals(1,unClienteCon2Dispositivos.cantidadDeDispositivosApagados());
        assertEquals(0,unClienteSinDispositivos.cantidadDeDispositivosApagados());
    }
    @Test
    public void testCantidadDispositivosEncedidosDeUnCliente(){
        assertEquals(1,unClienteCon2Dispositivos.cantidadDeDispositivosEncendidos());
        assertEquals(0,unClienteSinDispositivos.cantidadDeDispositivosEncendidos());
    }
    @Test
    public void testConsumoEnergeticoTotalDeUnCliente(){
        assertEquals(51.0,unClienteCon2Dispositivos.consumoEnergeticoTotal());
        assertEquals(0.0,unClienteSinDispositivos.consumoEnergeticoTotal());
    }
    @Test
    public void testVerigifarCategoriaDeUnCliente() throws ProcessingDataFailedException{

        //Se le Agrega otro Dispositivo
        unClienteCon2Dispositivos.agregarDispositivo(otroDispositivo);

        //Se Actualiza la Categoria de los Cliente
        unClienteCon2Dispositivos.actualizarCategoria();
        unClienteSinDispositivos.actualizarCategoria();

        assertEquals("R1",unClienteCon2Dispositivos.nombreCategoria());
        assertEquals("R2",unClienteSinDispositivos.nombreCategoria());
    }
    @Test
    public void testObtenerGastosAproximadosCliente() throws ProcessingDataFailedException{

        //Se le agrega otro dispositivo
        unClienteCon2Dispositivos.agregarDispositivo(otroDispositivo);

        //Se actualiza su categoria
        unClienteCon2Dispositivos.actualizarCategoria();

        //Cargofijo=35.32
        //CargoVariable=0.644
        //ConsumoCliente=251.0
        //35.32+0.644*251.0=196.964
        assertEquals(196.964,unClienteCon2Dispositivos.obtenerGastosAproximados());

    }
    @Test
    public void testObtenerGastosAproximadosConMockCategoria() throws ProcessingDataFailedException{

        Categoria categoriaMock = mock(Categoria.class);

        //Se le agrega otro dispositivo
        unClienteCon2Dispositivos.agregarDispositivo(otroDispositivo);

        //Se actualiza su categoria
        unClienteCon2Dispositivos.actualizarCategoria();

        //Cargofijo=500
        //CargoVariable=0.7
        //ConsumoCliente=251.0
        //500+0.7*251.0=196.964
        double costo =500.0+0.7*unClienteCon2Dispositivos.consumoEnergeticoTotal();

        when(categoriaMock.calcularCostosPara(unClienteCon2Dispositivos)).thenReturn(costo);
        assertEquals(675.7,categoriaMock.calcularCostosPara(unClienteCon2Dispositivos));
    }
    @Test
    public void testActualizacionCategoriaClienteConSpy() throws ProcessingDataFailedException {
         List<Dispositivo> listaDispositivosParaOtroCliente = new ArrayList<>();
         Cliente unClienteSpy = spy(new Cliente("Nicolas","Sierra","fer22",new ID(TiposIdEnum.DNI,"200"),new Domicilio("bariloche",3118,1,'a'),250,listaDispositivosParaOtroCliente));
         Categoria unaCategoriaMock = mock(Categoria.class);
         AsignadorDeCategoria asignadorMock = mock(AsignadorDeCategoria.class);
         when(asignadorMock.definirCategoriaPara(unClienteSpy)).thenReturn(unaCategoriaMock);
         doReturn(asignadorMock)
                .when(unClienteSpy)
                .asignadorDeCategoria();
         unClienteSpy.actualizarCategoria();
         verify(asignadorMock).definirCategoriaPara(unClienteSpy);
         assertEquals(unaCategoriaMock,unClienteSpy.getCategoria());
    }

}
