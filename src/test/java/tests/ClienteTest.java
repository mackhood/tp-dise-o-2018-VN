package tests;
import clases.*;
import documento.*;
import categorias.*;
import residencial.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;



public class ClienteTest 
{
    Cliente ariel;
	Dispositivo heladera;
	Dispositivo microondas;
	Dispositivo lavaropas;
	List<Dispositivo> dispositivos;
	Administrador admin;
	List <Cliente> clientes;
	List <Administrador> admins;
	@Before
	public void antesDeLosTests()
	{
		JSON json = new JSON();
		clientes = json.devolverClientes();
		ariel = clientes.get(0);
		
		admins = json.devolverAdministradores();
		admin = admins.get(0);
	}
	@Test
	public void testCantMesesDelAdmin()
	{
		this.antesDeLosTests();
		Assert.
		assertEquals(25,admin.cantMesesComoAdmin());
	}
	@Test
	public void testFacturacionEstimadaR8()
	{
		this.antesDeLosTests();
		Dispositivo a1 = new Dispositivo("a1",300,true);
		Dispositivo a2 = new Dispositivo("a2",500,true);
		Dispositivo a3 = new Dispositivo("a3",100,true);
		ariel.agregarDispositivo(a1);
		ariel.agregarDispositivo(a2);
		ariel.agregarDispositivo(a3);
		
		System.out.println(ariel.consumoEnergetico());
		System.out.println(ariel.facturacionEstimada());
		assertEquals("No coincide la facturacion estimada(categoria R8) con lo esperado",1468.444 ,ariel.facturacionEstimada(),0);

	}
	@Test
	public void testFacturacionEstimadaR1()
	{
		this.antesDeLosTests();
		Assert.assertEquals("No coincide la facturacion estimada(categoria R1) con lo esperado", 153.816, ariel.facturacionEstimada(), 0);
	}
	@Test
	public void testSaberSiAlgunDispositivoEstaEncendido()
	{
		this.antesDeLosTests();
		assertTrue(ariel.algunDispositivoEncendido());
	}
	
	@Test
	public void testSaberLaCantidadDeDispositivosEncendidos()
	{
		this.antesDeLosTests();
		assertEquals(1, ariel.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadTotalDispositivos()
	{
		this.antesDeLosTests();
		assertEquals(3, ariel.cantidadDispositivos());
	}
	@Test
	public void testCantidadDispositivosApagados()
	{
		this.antesDeLosTests();
		assertEquals(2, ariel.cantidadDispositivosApagados());
	}
	@Test
	public void consumoEnergetico()
	{
		this.antesDeLosTests();
		assertEquals("No coincide el consumo energetico(categoria R2) con lo esperado",184, ariel.consumoEnergetico(),0);
	}

}
