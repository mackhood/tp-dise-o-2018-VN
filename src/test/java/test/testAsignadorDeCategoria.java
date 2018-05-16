package test;

import Clases.AsignadorDeCategoria;
import Clases.Cliente;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testAsignadorDeCategoria {

	private AsignadorDeCategoria unAsignadorDeCategoria;
	private Cliente unClienteMock;

	@Before
	public  void setUp() {
		unClienteMock = mock(Cliente.class);
		when(unClienteMock.consumoEnergeticoTotal()).thenReturn(200.1);
	}

	@Test
	public void definirCategoriaParaUnClienteMock() {
	//	Assert.assertExeption(unAsignadorDeCategoria.definirCategoriaPara(unClienteMock));
	}

}
