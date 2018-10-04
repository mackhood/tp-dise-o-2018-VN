package test.otros;

import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testUbicacion {

    Ubicacion ubicacionTest;
    Cliente mockCliente;

	/*
	@Before
	public void setUp() {

		ubicacionTest = new Ubicacion(8, 3);
		mockCliente = Mockito.spy(new Cliente("John", "Doe", "johndoe777", new ID(TiposId.DNI, "34900213"),
				new Domicilio("Av Test", 132, 13, 'B'), 20426007, new Ubicacion(12, 1.5)));
	}*/

    @Test
    public void testCalcularDistancia() {

        assertEquals(9.84, ubicacionTest.calcularDistancia(mockCliente.getUbicacion()), 0.01);
    }

}
