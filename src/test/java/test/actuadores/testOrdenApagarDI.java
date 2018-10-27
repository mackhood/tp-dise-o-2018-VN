package test.actuadores;

import dominio.actuador.OrdenApagarDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class testOrdenApagarDI {

    OrdenApagarDI ordenApagarDI;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {

        unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
                .estadoDispositivo(new EstadoEncendido()).horaEncendido(LocalDateTime.now()).build());

        ordenApagarDI = new OrdenApagarDI(unDI);

    }

    @Test
    public void testDIEncendidoSeApaga() {
        ordenApagarDI.ejecutar();
        assertTrue(unDI.estaApagado());
    }

    @Test
    public void testDIEncendidoSigueEncendido() {
        ordenApagarDI.ejecutarInversa();
        assertTrue(unDI.estaEncendido());
    }
}
