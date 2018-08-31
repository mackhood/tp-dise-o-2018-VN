package test;

import dominio.dispositivo.Conversor;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.entities.NoTieneDispositivoException;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class testConversor {

	private Conversor conversor;
	private Cliente unMockCliente;
	private DispositivoEstandar dispositivoEstandar;
	private List<DispositivoEstandar> listaDispositivosEstandar = new ArrayList<>();
	private List<DispositivoInteligente> listaDispositivosInteligentes = new ArrayList<>();

	@Before
	public void setup() {
		
		conversor = new Conversor();
		dispositivoEstandar = mock(DispositivoEstandar.class);
		unMockCliente = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
				new Domicilio("Bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar,
				listaDispositivosInteligentes));
		unMockCliente.agregarDispositivoEstandar(dispositivoEstandar);
	}

	@Test

	public void testConvertidorInteligente() throws NoTieneDispositivoException {
		
		unMockCliente.agregarModuloAdaptador(conversor, dispositivoEstandar);
		assertTrue(!unMockCliente.getDispositivosInteligentes().isEmpty());
	}

}
