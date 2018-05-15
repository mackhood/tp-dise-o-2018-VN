package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import Clases.Administrador;
import Clases.Categoria;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;

public class testRepositorioCategoria {
	
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

}
