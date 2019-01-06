package test.managers;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.TransformadorManager;

public class testTransformadorManager {

	@Test
	public void testObtenerIDPorNumeroDeSerie() {
		
		assertEquals((long) 58, TransformadorManager.getInstance().obtenerTrasformador(3).getId());
	}

}
