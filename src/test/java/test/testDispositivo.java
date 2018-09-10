package test;

import dominio.actuador.*;
import dominio.consultores.ConsultaConsumoUltimasNHoras;
//import dominio.consultores.ConsultaEstaApagado;
//import dominio.consultores.ConsultaEstaEncendido;
import dominio.dispositivo.*;
import dominio.entities.NoTieneDispositivoException;
import dominio.regla.Regla;
import dominio.sensor.*;
import dominio.usuario.Cliente;
import dominio.zonageografica.*;

import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

public class testDispositivo {

	private DispositivoEstandar unDE;
	private DispositivoInteligente unDIApagado;
	private DispositivoInteligente unDIEncendido;
	private DispositivoInteligente unDETransformado;

	private ConsultaConsumoUltimasNHoras consultaConsumoUltimasNHoras;

	private OrdenApagarDI ordenApagarDI;
	private OrdenEncenderDI ordenEncenderDI;
	private OrdenPonerModoAhorro ordenPonerModoAhorro;
	private OrdenSubirIntensidad ordenSubirIntensidad;
	private OrdenEncenderDI ordenEncenderDITestRegla;
	private OrdenApagarDI ordenApagarDITestRegla;
	private Regla reglaParaAumentarIntensidadAlAireAcondicionado;
	private Sensor sensorTemperaturaMayor30;
	private Sensor sensorTemperaturaMenorA20;
	private Sensor sensorMovimiento;
	private Cliente unCliente;
	private Conversor moduloAdaptador;

	private Regla reglaParaEncenderAlAireAcondicionado;
	private Regla reglaParaApagarAlAireAcondicionado;
	CondicionPorIgual hayMovimiento;
	CondicionPorMenor menorA20;
	CondicionPorMayor mayorA30;
	DispositivoInteligente unDIAireApagado;
	List<DispositivoInteligente> listaInteligentesTestRegla = new ArrayList<>();

	@Before
	public void setUp() {

		unDE = new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build();
		unDIEncendido = new DispositivoInteligente.DispositivoInteligenteBuilder("da")
				.consumoEstimadoPorHora((double) 500).build();
		unDIApagado = new DispositivoInteligente.DispositivoInteligenteBuilder("AireAcondicionado")
				.consumoEstimadoPorHora((double) 100).build();
		unDIEncendido.setConsumoEstimadoPorHora(23);
		unDIEncendido.setHorasDeUso(2);
		unDIEncendido.encender();

		moduloAdaptador = new Conversor();

		// unDIEncendido.serUsado(10);
		List<DispositivoInteligente> listDispApagados = new ArrayList<>();
		listDispApagados.add(unDIApagado);

		List<DispositivoInteligente> listDispModoAhorro = new ArrayList<>();
		listDispModoAhorro.add(unDIApagado);

		List<DispositivoInteligente> listDispEncendidos = new ArrayList<>();
		listDispEncendidos.add(unDIEncendido);

		List<DispositivoEstandar> listaDispositivosEstandard = new ArrayList<>();
		List<DispositivoInteligente> listaDispositivosInteligentes = new ArrayList<>();

		listaDispositivosEstandard.add(unDE);

		unCliente = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
				new Domicilio("Bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandard,
				listaDispositivosInteligentes));

		// unDETransformado = unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);

		ordenApagarDI = new OrdenApagarDI(listDispEncendidos);
		ordenEncenderDI = new OrdenEncenderDI(listDispApagados);
		ordenPonerModoAhorro = new OrdenPonerModoAhorro(listDispModoAhorro);
		ordenSubirIntensidad = new OrdenSubirIntensidad(listDispEncendidos);

		// Para probar la regla para encender el aire acondicionado
		unDIAireApagado = new DispositivoInteligente.DispositivoInteligenteBuilder("AireAcondicionado")
				.consumoEstimadoPorHora((double) 50).build();
		listaInteligentesTestRegla.add(unDIAireApagado);
		ordenEncenderDITestRegla = new OrdenEncenderDI(listaInteligentesTestRegla);
		List<Condicion> listaCondicionesACumplir = new ArrayList<>();
		reglaParaApagarAlAireAcondicionado = new Regla(ordenApagarDITestRegla, listaCondicionesACumplir);
		reglaParaEncenderAlAireAcondicionado = new Regla(ordenEncenderDITestRegla, listaCondicionesACumplir);
		mayorA30 = new CondicionPorMayor(reglaParaEncenderAlAireAcondicionado, 30, "Temperatura");
		hayMovimiento = new CondicionPorIgual(reglaParaEncenderAlAireAcondicionado, 1, "Movimiento");
		listaCondicionesACumplir.add(mayorA30);
		listaCondicionesACumplir.add(hayMovimiento);
		sensorTemperaturaMayor30 = new Sensor(reglaParaEncenderAlAireAcondicionado);
		sensorMovimiento = new Sensor(reglaParaEncenderAlAireAcondicionado);
		unDIEncendido.setConsumoEstimadoPorHora(100);

		sensorTemperaturaMenorA20 = new Sensor(reglaParaApagarAlAireAcondicionado);
		menorA20 = new CondicionPorMenor(reglaParaApagarAlAireAcondicionado, 20, "Temperatura");
	}

	@Test
	public void testCumpleCondicionPorMayor() {
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(41, "Temperatura"));
		assertEquals(true, mayorA30.cumpleCondicion());
	}

	@Test
	public void testCumpleCondicionPorIgual() {
		sensorMovimiento.recibirMedicion(new Medicion(1, "Movimiento"));
		assertEquals(true, hayMovimiento.cumpleCondicion());
	}

	@Test
	public void testCumpleCondicionPorMenor() {
		sensorTemperaturaMenorA20.recibirMedicion(new Medicion(4, "Temperatura"));
		assertEquals(true, menorA20.cumpleCondicion());
	}

	/*
	 * Caso con multiples cambios de estado y estado final OK para ejecutar actuador
	 */

	@Test
	public void testCumpleTodasLasCondiciones() {

		sensorMovimiento.recibirMedicion(new Medicion(0, "Movimiento"));
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(40, "Temperatura"));
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(31, "Temperatura"));
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(22, "Temperatura"));
		sensorMovimiento.recibirMedicion(new Medicion(1, "Movimiento"));
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(44, "Temperatura"));
		assertEquals(true, unDIAireApagado.estaEncendido());
	}

	@Test
	public void testNoCumpleTodasLasCondiciones() {
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(29, "Temperatura"));
		sensorMovimiento.recibirMedicion(new Medicion(1, "Movimiento"));
		assertEquals(false, unDIAireApagado.estaEncendido());
	}

	/*
	 * En este test, quiero probar que al principio se cumplen ambas condiciones,
	 * por lo que se ejecuta el actuador. Luego el sensor que verifica la
	 * temperatura recibe una medicion que no cumple la condicion, por lo que el
	 * actuador realiza la accion inversa (en este caso, vuelve a apagar el aire)
	 */
	@Test
	public void testCambioDeEstado() {
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(32, "Temperatura"));
		sensorMovimiento.recibirMedicion(new Medicion(1, "Movimiento"));
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(29, "Temperatura"));
		Assert.assertEquals(false, unDIAireApagado.estaEncendido());
	}
	
	@Test
	public void testAgregarCondiciones() {
		
		List<Condicion> listaCondiciones = new ArrayList<>();
		List<DispositivoInteligente> listaDisp = new ArrayList<>();
		Regla reglaTest = new Regla(new OrdenPonerModoAhorro(listaDisp), listaCondiciones);
		reglaTest.agregarCondicion(mayorA30);
		reglaTest.agregarCondicion(hayMovimiento);
		assertEquals(2,reglaTest.getCondicionesACumplir().size());
	}
	
	@Test
	public void testReglaSerNotificadaCumplenTodas() {
		
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(41,"Temperatura"));
		sensorMovimiento.recibirMedicion(new Medicion(1,"Movimiento"));
		reglaParaEncenderAlAireAcondicionado.serNotificada();
		Assert.assertEquals(true,unDIAireApagado.estaEncendido());
	}
	
	@Test
	public void testReglaSerNotificadaNoCumplenTodas() {
		
		sensorTemperaturaMayor30.recibirMedicion(new Medicion(19,"Temperatura"));
		sensorMovimiento.recibirMedicion(new Medicion(1,"Movimiento"));
		reglaParaEncenderAlAireAcondicionado.serNotificada();
		Assert.assertEquals(false, unDIAireApagado.estaEncendido());
	}

	@Test
	public void testConsumoDIEncendidoLuegoApagado() {
		LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30, 100);
		LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30, 100);
		unDIEncendido.setHoraEncendido(horaEncendido);
		unDIEncendido.setHoraApagado(horaApagado);
		unDIEncendido.sumarHorasDeUso(horaEncendido, horaApagado);
		// unDIEncendido.apagar();
		// Assert.assertEquals(0,horaEncendido.until(LocalDateTime.of(2018,8,6,20,45,30,100),ChronoUnit.HOURS));
		// Assert.assertEquals(0,horaEncendido.until(unDIEncendido.getHoraApagado(),ChronoUnit.HOURS));
		// Assert.assertEquals(0,unDIEncendido.getHoraApagado().until(horaEncendido,ChronoUnit.HOURS));
		Assert.assertEquals(700.0, unDIEncendido.getConsumoTotal(), 10);
		// Assert.assertEquals(0,LocalDateTime.now());
	}

	@Test
	public void testDEUsadoPor5HorasConsumoTotal() {
		unDE.serUsado(5);
		assertEquals(1500.0, unDE.getConsumoTotal());
	}

	@Test
	public void testDETUsadoPor90HorasConsumoUltimas3Horas() throws NoTieneDispositivoException {
		unDE.serUsado(1);
		DispositivoEstandarInteligente unDET = new DispositivoEstandarInteligente(unDE);
		unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);
		consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDET, 3);
		assertEquals(300.0, consultaConsumoUltimasNHoras.consultar());
	}

	@Test
	public void testDIEncendidoConsumoUltimas2Horas() {
		consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDIEncendido, 2);
		assertEquals(200.0, consultaConsumoUltimasNHoras.consultar());
	}

	@Test
	public void testDIEncendidoConsumoUltimas10Horas() {
		unDIEncendido.apagar();
		consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDIEncendido, 10);

		assertEquals(200.0, consultaConsumoUltimasNHoras.consultar());
	}

	@Test
	public void testDEConsultaDeConsumoTotalDeUnDispositivoEstandarUsadoPor3Horas() {
		unDE.serUsado(3);
		assertEquals(900.0, unDE.getConsumoTotal());
	}

	@Test
	public void testDEConsultaConsumoDeUnDispositivoPorHoraEstandar() {
		assertEquals(300.0, unDE.consumoEstimadoPorHora());
	}

	@Test
	public void testDIApagadoSeEnciende() {
		ordenEncenderDI.ejecutar();
		assertEquals(true, unDIApagado.estaEncendido());
	}

	@Test
	public void testDIApagadoSePoneEnModoAhorro() {
		ordenPonerModoAhorro.ejecutar();
		assertEquals(true, unDIApagado.estaEnModoAhorro());
	}

	@Test
	public void testDIEncendidoSeApaga() {
		ordenApagarDI.ejecutar();
		assertEquals(true, unDIEncendido.estaApagado());
	}

}