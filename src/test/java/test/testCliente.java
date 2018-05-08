package test;

import Clases.*;
import Clases.repositories.RepositorioClientes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;



public class testCliente
{	
    Cliente unaPersona;
    Cliente otraPersona;
	Dispositivo heladera;
	Dispositivo microondas;
	Dispositivo lavaropas;
	List<Dispositivo> dispositivos;
	Administrador admin;
	Administrador admin2;
	List <Cliente> clientes;
	List <Administrador> admins;
	
	@Before
	
	public void init() {
	
		RepositorioClientes json = new RepositorioClientes();
		clientes = json.devolverClientes();
		unaPersona = clientes.get(0);
		otraPersona = clientes.get(1);
		
		admins = json.devolverAdministradores();
		admin = admins.get(0);
		admin2 = admins.get(1);
	}
	
	@Test
	
	public void testCantMesesDelAdminCaso1() {
		
		this.init();
		Assert.assertEquals(23,admin.cantMesesComoAdmin());
	}
	@Test
	public void testCantMesesDelAdminCaso2() {
		
		this.init();
		Assert.assertEquals(43,admin2.cantMesesComoAdmin());
	}
	
	@Test
	
	public void testFacturacionEstimadaR8() {
		
		this.init();
		Dispositivo a1 = new Dispositivo("a1",300,false);
		Dispositivo a2 = new Dispositivo("a2",500,false);
		Dispositivo a3 = new Dispositivo("a3",100,false);
		unaPersona.agregarDispositivo(a1);
		unaPersona.agregarDispositivo(a2);
		unaPersona.agregarDispositivo(a3);
		unaPersona.usarDispositivo(a1, LocalDateTime.of(2018, 03, 02, 02, 30),3);
		unaPersona.usarDispositivo(a3, LocalDateTime.of(2018, 05, 12, 23, 40), 2);
		//1100*0.851 +545.96 = 1482.06 //
		assertEquals("El valor de la facturación (categoría R8), no se condice con el valor esperado",1482.06,unaPersona.obtenerGastosAproximados(),0);
	}

/*
	@Test
	
	public void testAsignacionDeCategoria() {
		
		this.init();
		
		AsignarCategoria ac = new AsignarCategoria();
		Dispositivo d = new Dispositivo("d",300,true);
		Dispositivo d2 = new Dispositivo ("d2",100,true);
		unaPersona.agregarDispositivo(d);
		unaPersona.agregarDispositivo(d2);
		Categoria cat6 = new R6();
		unaPersona.usarDispositivo(d,LocalDateTime.of(2018, 04, 22, 19, 20), 1);
		unaPersona.usarDispositivo(d2,LocalDateTime.of(2018, 04, 25, 19, 20), 3);
		assertEquals(cat6,ac.definirCategoriaPara(unaPersona));
	}
*/
	@Test
	
	public void testFacturacionEstimadaR2() {

		this.init();
		Dispositivo a1 = new Dispositivo("a1",210,false);
		unaPersona.agregarDispositivo(a1);
		unaPersona.usarDispositivo(a1, LocalDateTime.of(2018, 01, 11, 21, 15), 1);
		// 210*0.644+35.32 = 170.56 //
		Assert.assertEquals("El valor de la facturación (categoría R2), no se condice con el valor esperado", 170.56, unaPersona.obtenerGastosAproximados(), 0);
	}
	
	@Test
	
	public void testSaberSiAlgunDispositivoEstaEncendido() {
	
		this.init();
		
		assertFalse(unaPersona.algunDispositivoEncendido());
	}
	
	@Test
	
	public void testSaberCantidadDeDispositivosEncendidos() {

		this.init();
		assertEquals(0, unaPersona.cantidadDeDispositivosEncendidos());
	}
	
	@Test
	
	public void testCantidadTotalDispositivos() {
		
		this.init();
		assertEquals(3, unaPersona.cantidadDeDispositivos());
	}
	
	@Test
	
	public void testCantidadDispositivosApagados() {
	
		this.init();
		assertEquals(3, unaPersona.cantidadDeDispositivosApagados());
	}
	
	@Test
	
	public void consumoEnergetico() {
	
		this.init();
		
		Dispositivo d = new Dispositivo("d",215,false);
		otraPersona.agregarDispositivo(d);
		otraPersona.usarDispositivo(d, LocalDateTime.of(2017, 11, 28, 00, 10), 3);
		// 215*3 = 645 //
		assertEquals("El valor de consumo energético no coincide con lo esperado",645, otraPersona.consumoEnergeticoTotal(),0);
	}
}
