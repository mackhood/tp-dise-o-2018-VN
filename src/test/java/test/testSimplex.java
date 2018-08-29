package test;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.simplexserviceautomatic.Simplex;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.repositories.Repositorios;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testSimplex {

	DispositivoInteligente aireAcondicionado3500;
	DispositivoInteligente lampara11W;
	DispositivoEstandar lavarropas5kgAgua;
	Simplex simplex;
	Cliente unCliente;
	List<Dispositivo> dispositivos = new ArrayList<>();
	List<DispositivoInteligente> inteligentes = new ArrayList<>();
	List<DispositivoEstandar> estandares = new ArrayList<>();

	@Before
	public void setUp() {
		aireAcondicionado3500 = new DispositivoInteligente("aireAcondicionado", "De 3500 frigorias", false, 0.05);
		lampara11W = new DispositivoInteligente("lampara", "De 11W", true, 0.0004);
		lavarropas5kgAgua = new DispositivoEstandar.DispositivoEstandarBuilder("lavarropas")
				.equipoConcreto("Automatico de 5kg con calentamiento de agua").esBajoConsumo(false)
				.consumoEstimadoPorHora(0.005).build();

		estandares.add(lavarropas5kgAgua);
		inteligentes.add(aireAcondicionado3500);
		inteligentes.add(lampara11W);

		unCliente = new Cliente("Ariel", "Galvan", "galvanariel97", new ID(TiposId.DNI, "40130179"),
				new Domicilio("asd", 1, 1, 'a'), 4444444, estandares, inteligentes);

		dispositivos.add(aireAcondicionado3500);
		dispositivos.add(lampara11W);
		dispositivos.add(lavarropas5kgAgua);
		simplex = new Simplex();

		simplex.execute(unCliente.todosLosDispositivos());
	}

	@Test
	public void testPrimerDispositivo() {
		Assert.assertEquals("aireAcondicionado", unCliente.todosLosDispositivos().get(0).getNombre());
	}

	@Test
	public void testSegundoDispositivo() {
		Assert.assertEquals("lampara", unCliente.todosLosDispositivos().get(1).getNombre());
	}

	@Test
	public void testTercerDispositivo() {
		Assert.assertEquals("lavarropas", unCliente.todosLosDispositivos().get(2).getNombre());
	}

	@Test
	public void testCoefAireAcondicionado3500() {
		Assert.assertEquals(1.613, Repositorios.dispositivos.coefConsumokwh(aireAcondicionado3500), 0);
	}

	@Test
	public void testCoefLampara11W() {
		Assert.assertEquals(0.011, Repositorios.dispositivos.coefConsumokwh(lampara11W), 0);
	}

	@Test
	public void testCoefDispositivos() {
		Assert.assertEquals(0.011, Repositorios.dispositivos.coeficientesDeConsumoKwh(dispositivos)[1], 0);
	}

	@Test
	public void testCoefsInecuacionesAireAcondicionado() {
		Assert.assertEquals(1,
				Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(aireAcondicionado3500)[0], 0);
	}

	@Test
	public void testSizeCoefsInecuacionesAireAcondicionado() {
		Assert.assertEquals(8,
				Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(aireAcondicionado3500).length, 0);
	}

	@Test
	public void testResultadoFuncionEconomica() {

		Assert.assertEquals(1000, simplex.getResultadoFuncionEconomica(), 50);
	}

	@Test
	public void testConsumoRecomendadoAireAcondicionado() {
		Assert.assertEquals(90, aireAcondicionado3500.getHorasMaximaPorConsumo(), 10);
	}

	@Test
	public void testConsumoRecomendadoLampara() {
		Assert.assertEquals(370, lampara11W.getHorasMaximaPorConsumo(), 10);
	}

	@Test
	public void testConsumoRecomendadoLavarropas() {
		Assert.assertEquals(560, lavarropas5kgAgua.getHorasMaximaPorConsumo(), 10);
	}
}
