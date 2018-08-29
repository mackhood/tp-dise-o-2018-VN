package test;

import com.sun.deploy.resources.Deployment_it;
import dominio.dispositivo.Convertidor;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class testConvertidor {

	private Convertidor convertidor;
	private Cliente unMockCliente;
	private DispositivoEstandar dispositivoEstandar;
	private List<DispositivoEstandar> listaDispositivosEstandard = new ArrayList<>();
	private List<DispositivoInteligente> listaDispositivosInteligentes = new ArrayList<>();

	@Before
	public void setup() {
		dispositivoEstandar = mock(DispositivoEstandar.class);
		unMockCliente = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
				new Domicilio("Bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandard,
				listaDispositivosInteligentes));
	}

	@Test

	public void testConvertidorInteligente() {

		convertidor.convertirInteligente(unMockCliente, dispositivoEstandar);
		assertTrue(unMockCliente.getDispositivosInteligentes().contains(dispositivoEstandar));
	}

}
