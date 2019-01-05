package test.managers;

import org.junit.Assert;
import org.junit.Test;
import persistence.ClienteManager;
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
	public void testTipoDispositivoAireAcondicionado()
	{
		Assert.assertEquals("Aire Acondicionado", DispositivosManager.getInstance().getTipoPorNombre("Aire Acondicionado").getNombre());

	}
	@Test
	public void testGetDispositivoInteligenteDeLaBDPorID()
	{
		Assert.assertEquals("Aire Acondicionado", DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(18)).getNombre());
	}
	@Test
	public void testGetDispositivoEstandarDeLaDPorEquipoConcreto(){

		Assert.assertEquals("Televisor", DispositivosManager.getInstance().getDispositivoEstandarDeLaDPorEquipoConcreto("Color de tubo fluorescente de 21").getNombre());
	}
	@Test
	public void testGetDispositivosInteligentesDeLaBD(){
		Assert.assertEquals(21, DispositivosManager.getInstance().getDispositivosInteligentesDeLaBD().size());
	}
	@Test
	public void testGetDispositivoPorDetalleEquipo(){
		Assert.assertEquals("Televisor",DispositivosManager.getInstance().getDispositivoPorDetalleEquipo("LED de 24").getNombre());
	}
	@Test
	public void testGetDispositivoEstandarDeLaBD(){
		Assert.assertEquals("Lavarropas", DispositivosManager.getInstance().getDispositivoEstandarDeLaBD(new Long(14)).getNombre());
	}
	@Test
	public void testGetDispositivosDeCliente(){
		Assert.assertEquals(2,DispositivosManager.getInstance().getDispositivosDeCliente(new Long(41)).size());
	}
	@Test
	public void testTieneIntervalos(){
		Assert.assertEquals(true,DispositivosManager.getInstance().tieneIntervalos(new Long(34)));
	}
	@Test
	public void testCantidadTiposDispositivos()
	{
		Assert.assertEquals(9,DispositivosManager.getInstance().getTipos().size());
	}
	@Test
	public void getDispositivoInteligenteDeLaBDPorID() {

		DispositivosManager instance = DispositivosManager.getInstance();

		assertEquals(18,instance.getDispositivoInteligenteDeLaBDPorID((long) 18).getId(),0);

	}

}
