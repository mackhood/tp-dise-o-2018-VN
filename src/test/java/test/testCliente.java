package test;
import Clases.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;



public class testCliente
{
    Cliente unaPersona;
	Dispositivo heladera;
	Dispositivo microondas;
	Dispositivo lavaropas;
	List<Dispositivo> dispositivos;
	Admin admin;
	List <Cliente> clientes;
	List <Admin> admins;
	
	@Before
	public void antesDeLosTests()
	{
		JSON json = new JSON();
		clientes = json.devolverClientes();
		unaPersona = clientes.get(0);
		
		admins = json.devolverAdministradores();
		admin = admins.get(0);
	}
	@Test
	public void testCantMesesDelAdmin()
	{
		this.antesDeLosTests();
		Assert.assertEquals(25,admin.cantMesesComoAdmin());
	}
	@Test
	public void testFacturacionEstimadaR8()
	{
		this.antesDeLosTests();
		Dispositivo a1 = new Dispositivo("a1",300,true);
		Dispositivo a2 = new Dispositivo("a2",500,true);
		Dispositivo a3 = new Dispositivo("a3",100,true);
		unaPersona.agregarDispositivo(a1);
		unaPersona.agregarDispositivo(a2);
		unaPersona.agregarDispositivo(a3);
		
		System.out.println(unaPersona.consumoEnergeticoTotal());
		assertEquals("No coincide la facturacion estimada(categoria R8) con lo esperado",1468.444 ,unaPersona.obtenerFacturaTentativa(),0);

	}
	@Test
	public void testFacturacionEstimadaR2()
	{
		this.antesDeLosTests();

		Assert.assertEquals("No coincide la facturacion estimada(categoria R2) con lo esperado", 153.816, unaPersona.obtenerFacturaTentativa(), 0);
	}
	@Test
	public void testSaberSiAlgunDispositivoEstaEncendido()
	{
		this.antesDeLosTests();
		assertTrue(unaPersona.algunDispositivoEncendido());
	}
	
	@Test
	public void testSaberLaCantidadDeDispositivosEncendidos()
	{
		this.antesDeLosTests();
		assertEquals(1, unaPersona.cantidadDeDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadTotalDispositivos()
	{
		this.antesDeLosTests();
		assertEquals(3, unaPersona.cantidadDeDispositivos());
	}
	@Test
	public void testCantidadDispositivosApagados()
	{
		this.antesDeLosTests();
		assertEquals(2, unaPersona.cantidadDeDispositivosApagados());
	}
	@Test
	public void consumoEnergetico()
	{
		this.antesDeLosTests();
		assertEquals("No coincide el consumo energetico(categoria R2) con lo esperado",184, unaPersona.consumoEnergeticoTotal(),0);
	}

}
