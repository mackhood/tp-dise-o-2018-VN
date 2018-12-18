package test.database;

import dominio.categoria.AsignadorDeCategoria;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.entities.NoTieneDispositivoException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import persistence.ClienteManager;
import persistence.DispositivosManager;
import persistence.TransformadorManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import reportes.ReporteConsumoPorDispositivo;
import reportes.ReporteConsumoPorHogar;
import reportes.ReporteConsumoPorTransformador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CasoDePrueba5 implements WithGlobalEntityManager {

	@Before
	public void init() {

		entityManager().getTransaction().begin();
	}

	@Test
	public void testConsumoPorDispositivoDeUsuario() {

		Intervalo i1 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 00), LocalDateTime.of(2018, 10, 12, 22, 45));
		Intervalo i2 = new Intervalo(LocalDateTime.of(2018, 10, 11, 23, 19), LocalDateTime.of(2018, 10, 12, 04, 20));
		Intervalo i3 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 9, 20));
		List<Intervalo> li = new ArrayList<>();
		li.add(i1);
		li.add(i2);
		li.add(i3);
		DispositivoInteligente d = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID((long) 17);
		d.agregarListaIntervalos(li);
		entityManager().persist(d);

		ReporteConsumoPorDispositivo r = new ReporteConsumoPorDispositivo();
		assertEquals(0.9, r.consumoPorDispositivo(25, 17), 0.01);

		Periodo p = new Periodo(LocalDateTime.of(2018, 10, 12, 16, 20), LocalDateTime.of(2018, 10, 13, 02, 00), null);
		assertEquals(0.04, r.consumoPromedioDispositivoEnPeriodo(17, p), 0.001);
	}

	@Test
	public void testTotalDispositivosDeUsuario() {

		ReporteConsumoPorDispositivo r = new ReporteConsumoPorDispositivo();
		assertEquals(2, r.totalDispositivos(25), 0);
	}

	@Test
	public void testConsumoDeHogarEnPeriodo() {
		Periodo p = new Periodo(LocalDateTime.of(2018, 06, 8, 22, 10), LocalDateTime.of(2018, 06, 9, 01, 50), null);
		ReporteConsumoPorHogar r = new ReporteConsumoPorHogar();
		assertEquals(0, r.consumoDeHogarEnPeriodo(25, p), 0.1);
	}

	@Test
	public void testConsumoTransformadorEnPeriodo() {

		ReporteConsumoPorTransformador r = new ReporteConsumoPorTransformador();

		assertEquals(21.63, r.consumoPorTransformador(TransformadorManager.getInstance().obtenerIdBD(3),
				new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 00), LocalDateTime.of(2018, 10, 12, 23, 45))),0.1);
	}

	@After
	public void end() {

		entityManager().getTransaction().rollback();
	}

}