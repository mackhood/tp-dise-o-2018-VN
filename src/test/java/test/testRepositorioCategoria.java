package test;

import Clases.Categoria;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class testRepositorioCategoria {
	//Usar json De prueba
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		return; 
	}

	@Test
	public void testDevuelveListaVacia() throws ProcessingDataFailedException {
	
		List<Categoria> categoriasVacia = new ArrayList<Categoria>();		
		RepositorioCategoria repoCateogoriaSinDatos = Mockito.mock(RepositorioCategoria.class);
	
		when(repoCateogoriaSinDatos.obtenerCategorias())
		.thenReturn(categoriasVacia);
		
        assertThat("Prueba de obtencion de lista vacia", repoCateogoriaSinDatos.obtenerCategorias().size(), equalTo(0));
	}

	@Test
	public void testObtencionDeCategoriasDelRepositorio() throws Exception{
		Categoria unaCategoria = null;
		RepositorioCategoria repositorio =new RepositorioCategoria();
		unaCategoria =repositorio.obtenerCategorias().get(0);
		assertEquals("R1",unaCategoria.getNombreCategoria());
	}

}
