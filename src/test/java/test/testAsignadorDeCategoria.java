package test;

import Clases.AsignadorDeCategoria;
import Clases.Categoria;
import Clases.Cliente;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testAsignadorDeCategoria {

	private AsignadorDeCategoria unAsignadorDeCategoria;
	private Cliente unClienteMock;
	private Categoria categoria1Mock;
	private Categoria categoria2Mock;

	@Before
	public  void setUp() {
		unClienteMock = mock(Cliente.class);
		categoria1Mock =mock(Categoria.class);
		categoria2Mock =mock(Categoria.class);
		when(unClienteMock.consumoEnergeticoTotal()).thenReturn(200.1);
		when(categoria1Mock.cumpleCondicion(unClienteMock)).thenReturn(true);
		when(categoria2Mock.cumpleCondicion(unClienteMock)).thenReturn(false);
	}

	@Test
	public void definirCategoriaParaUnClienteMock() {
		//Tenedria q Asignador de categoria Recibir una lista de Categorias
		//Usar el methodo definirCategoriaPara(unCliente) con los mocksCategoria
		//Devolver categoria1Mock
	}

}
