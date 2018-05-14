package test;


import Clases.Administrador;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class testAdministrador {

	private Administrador unAdmin;

	@BeforeClass
	public void setUp() {
		unAdmin = Mockito.mock(Administrador.class);
	}

	@Test
	public void test() {


	}

}
