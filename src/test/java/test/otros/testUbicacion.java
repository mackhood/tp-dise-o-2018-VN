package test.otros;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testUbicacion {

    Ubicacion ubicacionTest;
    Cliente mockCliente;
    List<DispositivoInteligente> dispositivoInteligentes;
    List<DispositivoEstandar> dispositivoEstandars;

	@Before
	public void setUp() {

		ubicacionTest = new Ubicacion(8, 3);
		dispositivoInteligentes = new ArrayList<>();
        dispositivoEstandars = new ArrayList<>();
		mockCliente = Mockito.spy(new Cliente("John", "Doe", "johndoe777", "password",new ID(TiposId.DNI, "34900213"),
                new Domicilio("Av Test", 132, 13, 'B'), 20426007, dispositivoEstandars, dispositivoInteligentes));
		mockCliente.setUbicacion( new Ubicacion(12.0, 1.5));
	}

    @Test
    public void testCalcularDistancia() {

        assertEquals(9.84, ubicacionTest.calcularDistancia(mockCliente.getUbicacion()), 0.01);
    }

}
