package test.otros;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testUbicacion {

    Ubicacion ubicacionTest;
    Cliente mockCliente;


	@Before
	public void setUp() {

	    List<DispositivoEstandar> estandars = new ArrayList<>();
	    List<DispositivoInteligente> inteligentes = new ArrayList<>();
		ubicacionTest = new Ubicacion(8, 3);
		mockCliente = Mockito.spy(new Cliente("John", "Doe", "johndoe777","asd", new ID(TiposId.DNI, "34900213"),
                new Domicilio("Av Test", 132, 13, 'B'), 20426007, estandars, inteligentes));
		mockCliente.setUbicacion( new Ubicacion(12, 1.5));
	}

    @Test
    public void testCalcularDistancia() {

        assertEquals(9.84, ubicacionTest.calcularDistancia(mockCliente.getUbicacion()), 0.01);
    }

}
