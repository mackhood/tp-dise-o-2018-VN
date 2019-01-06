package test.actuadores;

import dominio.actuador.OrdenSubirIntensidad;
import dominio.dispositivo.DispositivoInteligente;
import repositories.RepositorioDispositivo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class testOrdenSubirIntensidad {

    OrdenSubirIntensidad ordenSubirIntensidad;
    DispositivoInteligente unDI;

    @Before
    public void setUp() {

        unDI = Mockito.spy(RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("Aire Acondicionado","De 3500 frigorias"));


        ordenSubirIntensidad = new OrdenSubirIntensidad(unDI);
    }

    @Test
    public void testDISubeIntensidadYAumentaSuConsumoEnMedioKwh() {
        ordenSubirIntensidad.ejecutar();
        assertEquals(2.113, unDI.getConsumoEstimadoPorHora(), 1);
    }

    @Test
    public void testDIBajaIntensidadYSuConsumoDesciendeEn50() {
        ordenSubirIntensidad.ejecutarInversa();
        assertEquals(1.113, unDI.getConsumoEstimadoPorHora(), 1);
    }
}
