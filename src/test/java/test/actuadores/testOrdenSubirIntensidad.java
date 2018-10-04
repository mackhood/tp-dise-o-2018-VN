package test.actuadores;

import dominio.actuador.OrdenSubirIntensidad;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testOrdenSubirIntensidad {

    OrdenSubirIntensidad ordenSubirIntensidad;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {

        List<DispositivoInteligente> listaDispositivosEncendidos = new ArrayList<>();
        ordenSubirIntensidad = new OrdenSubirIntensidad(listaDispositivosEncendidos);

        unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
                .estadoDispositivo(new EstadoEncendido()).consumoEstimadoPorHora(30.0).build());

        listaDispositivosEncendidos.add(unDI);
    }

    @Test
    public void testDISubeIntensidadYAumentaSuConsumoEn50() {
        ordenSubirIntensidad.ejecutar();
        assertEquals(80, unDI.getConsumoEstimadoPorHora(), 1);
    }

    @Test
    public void testDIBajaIntensidadYSuConsumoDesciendeEn50() {
        ordenSubirIntensidad.ejecutarInversa();
        assertEquals(0, unDI.getConsumoEstimadoPorHora(), 1);
    }
}
