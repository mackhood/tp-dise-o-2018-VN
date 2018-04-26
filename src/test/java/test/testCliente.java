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
	
	public void init() {
	
		JSON json = new JSON();
		clientes = json.devolverClientes();
		unaPersona = clientes.get(0);
		
		admins = json.devolverAdministradores();
		admin = admins.get(0);
	}
	
	@Test
	
	public void testCantMesesDelAdmin() {
		
		this.init();
		Assert.assertEquals(25,admin.cantMesesComoAdmin());
	}
	
	@Test
	
	public void testFacturacionEstimadaR8() {
		
		this.init();
		Dispositivo a1 = new Dispositivo("a1",300,true);
		Dispositivo a2 = new Dispositivo("a2",500,true);
		Dispositivo a3 = new Dispositivo("a3",100,true);
		unaPersona.agregarDispositivo(a1);
		unaPersona.agregarDispositivo(a2);
		unaPersona.agregarDispositivo(a3);
		
		System.out.println(unaPersona.consumoEnergeticoTotal());
		assertEquals("El valor de la facturación (categoría R8), no se condice con el valor esperado",1468.444 ,unaPersona.obtenerFacturaTentativa(),0);
	}
	
	@Test
	
	public void testFacturacionEstimadaR2() {

		this.init();

		Assert.assertEquals("El valor de la facturación (categoría R2), no se condice con el valor esperado", 153.816, unaPersona.obtenerFacturaTentativa(), 0);
	}
	
	@Test
	
	public void testSaberSiAlgunDispositivoEstaEncendido() {
	
		this.init();
		
		assertTrue(unaPersona.algunDispositivoEncendido());
	}
	
	@Test
	
	public void testSaberCantidadDeDispositivosEncendidos() {

		this.init();
		assertEquals(1, unaPersona.cantidadDeDispositivosEncendidos());
	}
	
	@Test
	
	public void testCantidadTotalDispositivos() {
		
		this.init();
		assertEquals(3, unaPersona.cantidadDeDispositivos());
	}
	
	@Test
	
	public void testCantidadDispositivosApagados() {
	
		this.init();
		assertEquals(2, unaPersona.cantidadDeDispositivosApagados());
	}
	
	@Test
	
	public void consumoEnergetico() {
	
		this.init();
		assertEquals("El valor de consumo energético (categoría R2), no coincide con lo esperado",184, unaPersona.consumoEnergeticoTotal(),0);
	}
}
