package test.actuadores;

import dominio.actuador.OrdenPonerModoAhorro;
import dominio.dispositivo.DispositivoInteligente;
import datos.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class testOrdenPonerModoAhorro {

    OrdenPonerModoAhorro ordenPonerModoAhorro;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {


        unDI = Mockito.spy(RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("Televisor","LED de 24"));
        unDI.encender();
        ordenPonerModoAhorro = new OrdenPonerModoAhorro(unDI);
    }
    
    @Test
    public void testDIEncendidoSePoneEnModoAhorro() {
        ordenPonerModoAhorro.ejecutar();
        assertTrue(unDI.estaEnModoAhorro());
    }

    @Test
    public void testDIEncendidoSigueEncendido() {
        ordenPonerModoAhorro.ejecutarInversa();
        assertTrue(unDI.estaEncendido());
    }
}