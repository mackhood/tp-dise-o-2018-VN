package test;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.SistemaInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;

public class testSistemaInteligente {
	
	SistemaInteligente sistema;
	Cliente mockCliente;
	DispositivoInteligente mockDI;
	DispositivoInteligente otroMockDI;
	DispositivoEstandar mockDE;
	DispositivoEstandar otroMockDE;
	
	@Before
	public void setUp() {
		
		List<DispositivoEstandar> listaDispEstandar = new ArrayList<>();
		List<DispositivoInteligente> listaDispInteligentes = new ArrayList<>();
		
		sistema = new SistemaInteligente();
		mockDI = Mockito.mock(DispositivoInteligente.class);
		otroMockDI = Mockito.mock(DispositivoInteligente.class);
		mockDE = Mockito.mock(DispositivoEstandar.class);
		otroMockDE = Mockito.mock(DispositivoEstandar.class);
		
		mockCliente = Mockito.spy(new Cliente("John","Doe","johnDoe777",new ID(TiposId.DNI,"41291009"),
				new Domicilio("Av Test",2805,14,'B'),44239910,listaDispEstandar,listaDispInteligentes));
		
		mockCliente.agregarDispositivoEstandar(mockDE);
		mockCliente.agregarDispositivoEstandar(otroMockDE);
		mockCliente.agregarDispositivoInteligente(mockDI);
		mockCliente.agregarDispositivoInteligente(otroMockDI);
		
		Mockito.when(mockDI.estaEncendido()).thenReturn(true);
		Mockito.when(otroMockDI.estaEncendido()).thenReturn(true);
	}
	
	@Test
	public void testAlgunDIestaEncendidoClienteCon2DI() {

		assertEquals(true, sistema.algunDIencendido(mockCliente));
	}

	@Test
	public void testCantidadDIencendidosClienteCon2DI() {
		assertEquals(2, sistema.cantidadDIencendidos(mockCliente));
	}

	@Test
	public void testCantidadDIapagadosClienteCon2DI() {
		assertEquals(0, sistema.cantidadDIapagados(mockCliente));
	}

	@Test
	public void testCantidadDispositivosClienteCon2DIy1DE() {
		assertEquals(4, sistema.cantidadDispositivos(mockCliente));
	}
}
