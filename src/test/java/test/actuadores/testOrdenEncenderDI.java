package test.actuadores;

import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.repositories.RepositorioDispositivo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class testOrdenEncenderDI {

    OrdenEncenderDI ordenEncenderDI;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {

        unDI = Mockito.spy(RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("Aire Acondicionado","De 2200 frigorias"));
        ordenEncenderDI = new OrdenEncenderDI(unDI);
    }

    @Test
    public void testDIApagadoSeEnciende() {
        ordenEncenderDI.ejecutar();
        assertTrue(unDI.estaEncendido());
    }

    @Test
    public void testDIApagadoSigueApagado() {
        ordenEncenderDI.ejecutarInversa();
        assertTrue(unDI.estaApagado());
    }
}