package test;


import Clases.Cliente;
import Clases.Dispositivo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class testCliente {
    private Cliente unaPersona;
    private Dispositivo heladera;
    private Dispositivo televisor;


    @Before
    public void setUp()  {

        heladera = Mockito.mock(Dispositivo.class);
        televisor = Mockito.mock(Dispositivo.class);
        unaPersona = Mockito.mock(Cliente.class);

        unaPersona.agregarDispositivo(televisor);
        unaPersona.agregarDispositivo(heladera);

        when(televisor.getConsumoTotal()).thenReturn(2.5);
        when(heladera.getConsumoTotal()).thenReturn(3.5);

        when(televisor.isEncendido()).thenReturn(false);
        when(heladera.isEncendido()).thenReturn(true);

    }

    @Test
    public void testAlgunDispositivoEncendido() {
         Assert.assertTrue(unaPersona.algunDispositivoEncendido());
    }

    @Test
    public void testCantDispositivosEncendidos() {
                Assert.assertEquals(1,unaPersona.cantidadDeDispositivos() );
    }
    @Test
    public void testCantidadDispositivosApagados()  {
        Assert.assertEquals(1,unaPersona.cantidadDeDispositivosApagados());
    }
    @Test
    public void testCantidadDispositivos()  {
        Assert.assertEquals(2,unaPersona.cantidadDeDispositivos());
    }

    @Test
    public void testConsumoEnergeticoTotal(){

        assertEquals("El valor de consumo energético no coincide con lo esperado", 5, unaPersona.consumoEnergeticoTotal(), 0);

    }

}
