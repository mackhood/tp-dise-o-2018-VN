package test.actuadores;

import dominio.actuador.OrdenPonerModoAhorro;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class testOrdenPonerModoAhorro {

    OrdenPonerModoAhorro ordenPonerModoAhorro;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {

        List<DispositivoInteligente> listaDispositivosApagados = new ArrayList<>();

        ordenPonerModoAhorro = new OrdenPonerModoAhorro(listaDispositivosApagados);


        unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
                .estadoDispositivo(new EstadoEncendido()).build());

        listaDispositivosApagados.add(unDI);
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