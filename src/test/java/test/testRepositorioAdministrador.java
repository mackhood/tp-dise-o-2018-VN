package test;

import Clases.Categoria;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioAdministradores;
import Clases.repositories.RepositorioCategoria;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class testRepositorioAdministrador {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		return;
	}

	@Test
	public void testDadoUnJsonDeTestSeObtieneCorrectamenteLaCantidadDeRegistros() throws Exception {
		RepositorioAdministradores repositorio = Mockito.mock(RepositorioAdministradores.class);
		when(repositorio.getJsonFile()).thenReturn(this.getJsonTestFile());
		when(repositorio.obtenerAdministradores()).thenCallRealMethod();

		int sizeExoected = repositorio.obtenerAdministradores().size();

		assertEquals("Se obtienen todas las categorias en el json y se valida su cantidad", 1, sizeExoected);
	}

	public String getJsonTestFile() {
		return getClass().getClassLoader().getResource("testAdministradores.json").getFile();
	}


}
