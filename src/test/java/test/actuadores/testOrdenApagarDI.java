package test.actuadores;

import dominio.actuador.OrdenApagarDI;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.repositories.RepositorioDispositivo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

public class testOrdenApagarDI {

    OrdenApagarDI ordenApagarDI;
    DispositivoInteligente unDI;
    @Before
    public void setUp() {

        unDI = Mockito.spy(RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("aireAcondicionado","De 2200 frigorias"));
        unDI.encender();
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
