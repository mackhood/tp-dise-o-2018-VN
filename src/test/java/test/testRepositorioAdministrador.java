package test;

import dominio.repositories.RepositorioAdministradores;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class testRepositorioAdministrador {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		return;
	}

	@Ignore
	public void testDadoUnJsonDeTestSeObtieneCorrectamenteLaCantidadDeRegistros() throws Exception {
		RepositorioAdministradores repositorio = Mockito.mock(RepositorioAdministradores.class);
		when(repositorio.getJsonFile()).thenReturn(this.getJsonTestFile());
		when(repositorio.obtenerAdministradores()).thenCallRealMethod();

		int sizeExpected = repositorio.obtenerAdministradores().size();

		assertEquals("Se obtienen todas las categorias en el json y se valida su cantidad", 1, sizeExpected);
	}

	public String getJsonTestFile() {
		return getClass().getClassLoader().getResource("testAdministradores.json").getFile();
	}

}
