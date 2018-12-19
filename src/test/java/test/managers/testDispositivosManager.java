package test.managers;

import org.junit.Test;
import persistence.DispositivosManager;
import persistence.TransformadorManager;

import static junit.framework.TestCase.assertEquals;

public class testDispositivosManager {

	@Test
	public void testGetInstance() {

		DispositivosManager instance = DispositivosManager.getInstance();

		assertEquals(instance.getClass(), DispositivosManager.class);

	}

	@Test
	public void getDispositivoInteligenteDeLaBDPorID() {

		DispositivosManager instance = DispositivosManager.getInstance();

		assertEquals(9,instance.getDispositivoInteligenteDeLaBDPorID((long) 9).getId(),0);

	}

}
