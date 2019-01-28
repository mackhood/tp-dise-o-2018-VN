package test.database;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;
import dominio.usuario.Administrador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import persistence.AdministradorManager;
import persistence.ClienteManager;
import persistence.DispositivosManager;
import persistence.TipoDispositivoManager;
import datos.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CargarDatos extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	@Test
	public void test0CargarTiposDeDispositivo()
	{
		RepositorioTipoDispositivo.getInstance().persistirTiposDeDispositivo();
	}
	
	@Test
	public void test2CargarCliente() {
		
		withTransaction(() -> {

			Intervalo i1 = new Intervalo(LocalDateTime.of(2018, 12, 18, 13, 00),
					LocalDateTime.of(2500, 12, 18, 23, 50));
			Intervalo i2 = new Intervalo(LocalDateTime.of(2018, 12, 14, 00, 10),
					LocalDateTime.of(2018, 12, 14, 05, 05));
			Intervalo i3 = new Intervalo(LocalDateTime.of(2018, 12, 11, 03, 30),
					LocalDateTime.of(2018, 12, 11, 12, 00));

			List<Intervalo> li = new ArrayList<>();
			List<Intervalo> li2 = new ArrayList<>();
			li.add(i1);
			li.add(i2);
			li2.add(i3);

			DispositivoInteligente di1 = new DispositivoInteligente.DispositivoInteligenteBuilder(
					"Dispositivo de Testeo1")
							.equipoConcreto("Test1").consumoEstimadoPorHora(0.913)
							.tipoDispositivo(
									TipoDispositivoManager.getInstance().getTipoDispositivoDeLaBDPorID(new Long(1)))
							.build();
			
			di1.setTipo(TipoDispositivoManager.getInstance().getTipoPorNombre("Otros"));
			di1.agregarListaIntervalos(li);
			di1.encender();

			entityManager().persist(di1);

			DispositivoInteligente di2 = new DispositivoInteligente.DispositivoInteligenteBuilder(
					"Dispositivo de Testeo2")
							.equipoConcreto("Test2").consumoEstimadoPorHora(0.24)
							.tipoDispositivo(
									TipoDispositivoManager.getInstance().getTipoDispositivoDeLaBDPorID(new Long(1)))
							.build();
			di2.setTipo(TipoDispositivoManager.getInstance().getTipoPorNombre("Otros"));
			di2.agregarListaIntervalos(li2);
			di2.encender();
			entityManager().persist(di2);

			Domicilio domicilio = new Domicilio("Alem", 204, 9, 'B');
			ID id = new ID(TiposId.DNI, "37110945");	
			List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
			List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

			dispositivosInteligentes.add(di1);
			dispositivosInteligentes.add(di2);
			Cliente unCliente = new Cliente("Ariel", "Galvan", "galvanariel", "password", id, domicilio, 42211000,
					dispositivosEstandares, dispositivosInteligentes);

			Ubicacion ubicacion = new Ubicacion(5, 2);
			unCliente.setUbicacion(ubicacion);

			entityManager().persist(unCliente);
			entityManager().getTransaction().commit();
		});
	}

	@Test
	public void test1cargarTablaDispositivos() {
		RepositorioDispositivo.getInstance().persistirDispositivosDelRepositorio();
	}

	@Test
	public void test3cargarIntervaloADispositivoCreado() {
		String nombreDI = "Mi Dispositivo";
		DispositivoInteligente diPersistir = new DispositivoInteligente.DispositivoInteligenteBuilder(nombreDI)
				.equipoConcreto("Dispositivo de Testeo").consumoEstimadoPorHora((double) 0.412).build();
		diPersistir.setTipo(TipoDispositivoManager.getInstance().getTipoPorNombre("Otros"));
		entityManager().persist(diPersistir);

		DispositivoInteligente dispositivoInteligenteObtenido = (DispositivoInteligente) entityManager()
				.createQuery("from DispositivoInteligente where nombre = :nombre", DispositivoInteligente.class)
				.setParameter("nombre", nombreDI).getSingleResult();
		LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30);
		LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30);
		Intervalo intervalo = new Intervalo(horaEncendido, horaApagado);
		List<Intervalo> intervalos = new ArrayList<>();
		intervalos.add(intervalo);
		entityManager().persist(intervalo);
		dispositivoInteligenteObtenido.agregarListaIntervalos(intervalos);
		entityManager().persist(dispositivoInteligenteObtenido);
		entityManager().getTransaction().commit();
	}

	@Test
	public void test4cargarIntervaloADispositivoID19() {
		DispositivoInteligente di = DispositivosManager.getInstance()
				.getDispositivoInteligenteDeLaBDPorID(new Long(19));
		LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 11, 10, 15, 30);
		LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 11, 23, 10, 20);
		Intervalo intervalo = new Intervalo(horaEncendido, horaApagado);

		entityManager().persist(intervalo);
		di.agregarIntervalo(intervalo);

		entityManager().persist(di);
		entityManager().getTransaction().commit();

	}

	@Test
	public void test5cargarIntervaloADispositivoID22() {
		DispositivoInteligente di = DispositivosManager.getInstance()
				.getDispositivoInteligenteDeLaBDPorID(new Long(22));
		LocalDateTime horaEncendido = LocalDateTime.of(2018, 07, 15, 19, 45, 00);
		LocalDateTime horaApagado = LocalDateTime.of(2018, 07, 15, 00, 30, 00);
		Intervalo intervalo = new Intervalo(horaEncendido, horaApagado);

		entityManager().persist(intervalo);
		di.agregarIntervalo(intervalo);

		entityManager().persist(di);
		entityManager().getTransaction().commit();

	}

	@Test
	public void test6cargarTransformador() {

		Intervalo i1 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 10), LocalDateTime.of(2018, 10, 12, 22, 45));
		Intervalo i2 = new Intervalo(LocalDateTime.of(2018, 10, 11, 22, 20), LocalDateTime.of(2018, 10, 12, 04, 20));
		Intervalo i3 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 16, 20));
		Intervalo i4 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 10), LocalDateTime.of(2018, 10, 12, 22, 45));
		Intervalo i5 = new Intervalo(LocalDateTime.of(2018, 10, 11, 22, 20), LocalDateTime.of(2018, 10, 12, 04, 20));
		Intervalo i6 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 16, 20));
		List<Intervalo> li = new ArrayList<>();
		List<Intervalo> li2 = new ArrayList<>();
		li.add(i1);
		li.add(i2);
		li.add(i3);
		li2.add(i4);
		li2.add(i5);
		li2.add(i6);
		DispositivoInteligente di1 = new DispositivoInteligente.DispositivoInteligenteBuilder("Tester1")
				.equipoConcreto("Dispositivo de Testeo").consumoEstimadoPorHora((double) 0.700).intervalosDeUso(li)
				.build();
		di1.setTipo(TipoDispositivoManager.getInstance().getTipoPorNombre("Otros"));
		di1.agregarListaIntervalos(li);
		entityManager().persist(di1);

		DispositivoInteligente di2 = new DispositivoInteligente.DispositivoInteligenteBuilder("Tester2")
				.equipoConcreto("Dispositivo de Testeo").consumoEstimadoPorHora((double) 1.110).intervalosDeUso(li)
				.build();

		di2.setTipo(TipoDispositivoManager.getInstance().getTipoPorNombre("Otros"));
		di2.agregarListaIntervalos(li2);
		entityManager().persist(di2);

		Domicilio domicilio = new Domicilio("Av. Directorio", 830, 1, 'C');
		ID id = new ID(TiposId.DNI, "37200918");
		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

		dispositivosInteligentes.add(di1);
		dispositivosInteligentes.add(di2);
		Cliente unCliente = new Cliente("Fernando", "Sierra", "fernandosierra9", "password", id, domicilio, 47581269,
				dispositivosEstandares, dispositivosInteligentes);

		Ubicacion ubicacion = new Ubicacion(5, 2);
		unCliente.setUbicacion(ubicacion);
		entityManager().persist(ubicacion);
		entityManager().persist(unCliente);
		Transformador nuevoTransformador = new Transformador(3);
		Ubicacion ubicacionTrasnformador = new Ubicacion(15, 15);
		entityManager().persist(ubicacionTrasnformador);
		nuevoTransformador.setUbicacion(ubicacionTrasnformador);
		nuevoTransformador.agregarCliente(unCliente);
		entityManager().persist(nuevoTransformador);

		entityManager().getTransaction().commit();
	}

	@Test
	public void test7cargarAdmin() {
		
		withTransaction(() -> {
			Domicilio domicilio = new Domicilio("Jean Jaures", 905, 4, 'D');

			Administrador unAdministrador = new Administrador("German", "Jugo", LocalDate.of(2016, 5, 18), "gerjor",
					"1234");

			unAdministrador.setDomicilio(domicilio);

			entityManager().persist(unAdministrador);
			entityManager().getTransaction().commit();
		});
	}
}
