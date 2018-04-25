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
	Administrador admin1;
	Administrador admin2;
	Cliente otro;
	List <Cliente> clientes;
	List <Administrador> admins;
	@Before
	public void antesDeLosTests()
	{
		//JSON json = new JSON();
		//clientes = json.devolverClientes();
		//ariel = clientes.get(0);
		
		//admins = json.devolverAdministradores();
		//admin = admins.get(0);
		heladera = new Dispositivo("Heladera",100,true);
		microondas  = new Dispositivo("Microondas",50,false);
		lavaropas  = new Dispositivo("Lavarropas",34,false);

		dispositivos = new ArrayList();
		
		dispositivos.add(heladera);
		dispositivos.add(microondas);
		dispositivos.add(lavaropas);
		
		Documento doc = new Documento("48158457", TipoDocumento.DNI);
		ariel = new Cliente("Ariel","Galvan","galvanariel97","z","av.av", 1, doc,44444444, dispositivos);
		
		//Cliente otro = new Cliente("Juan","Apellido","jad","e","avenida",2,doc,48192389,dispositivos);
		
		admin1 = new Administrador("AAAAA","BBBBBB",LocalDate.of(2016, 5, 18));
		admin2 = new Administrador("XXXX","ZZZZ",LocalDate.of(2014, 9, 20));
	}
	@Test
	public void testCantMesesDelAdmin()
	{
		this.antesDeLosTests();
		assertEquals(25,admin1.cantMesesComoAdmin());
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
		
		assertEquals("No coincide la facturacion estimada(categoria R8) con lo esperado",1468.444 ,ariel.facturacionEstimada(),0);

	}
	@Test
	public void testFacturacionEstimadaR2()
	{
		this.antesDeLosTests();

		Assert.assertEquals("No coincide la facturacion estimada(categoria R2) con lo esperado", 153.816, ariel.facturacionEstimada(), 0);
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
