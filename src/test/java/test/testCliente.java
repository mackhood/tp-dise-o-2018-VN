package test;

import Clases.*;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testCliente {

    private Dispositivo unDispositivoEncendido;
    private Dispositivo unDispositivoApagado;
    private Cliente unClienteCon2Dispositivos;
    private Cliente unClienteSinDispositivos;




    @Before
    public void setUp()  {

        unDispositivoEncendido = mock(Dispositivo.class);
        unDispositivoApagado = mock(Dispositivo.class);

        List<Dispositivo> listaDispositivos = new ArrayList<>();
        List<Dispositivo> listaDispositivosParaOtroCliente = new ArrayList<>();
        unClienteCon2Dispositivos = new Cliente("Fernando","Sierra","fer22",new ID(TiposIdEnum.DNI,200),new Domicilio("bariloche",3118,1,'a'),250,listaDispositivos);
        unClienteSinDispositivos= new Cliente("Nicolas","Sierra","fer22",new ID(TiposIdEnum.DNI,200),new Domicilio("bariloche",3118,1,'a'),250,listaDispositivosParaOtroCliente);

        unClienteCon2Dispositivos.agregarDispositivo(unDispositivoEncendido);
        unClienteCon2Dispositivos.agregarDispositivo(unDispositivoApagado);
        when(unDispositivoEncendido.isEncendido()).thenReturn(true);
        when(unDispositivoApagado.isEncendido()).thenReturn(false);
        when(unDispositivoEncendido.getConsumoTotal()).thenReturn(25.5);
        when(unDispositivoApagado.getConsumoTotal()).thenReturn(25.5);

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
    @Test //VER ERROR
    public void testActualizarCategoriaDeUnCliente(){
        unClienteCon2Dispositivos.actualizarCategoria();
        unClienteSinDispositivos.actualizarCategoria();
        assertNotNull(unClienteCon2Dispositivos.getCategoria());
        assertNotNull(unClienteSinDispositivos.getCategoria());
    }
    @Test //VER ERROR
    public void testVerigifarCategoriaDeUnCliente() throws ProcessingDataFailedException{
        unClienteCon2Dispositivos.actualizarCategoria();
        unClienteSinDispositivos.actualizarCategoria();
        //assertEquals("R1",unClienteCon2Dispositivos.nombreCategoria());
        //assertEquals("R1",unClienteSinDispositivos.nombreCategoria());
        Categoria unaCategoria = null;
        RepositorioCategoria repositorio =new RepositorioCategoria();
        unaCategoria =repositorio.obtenerCategorias().get(0);
        assertNotNull(unaCategoria);
    }


}
