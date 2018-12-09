package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import reportes.ReporteConsumoPorHogar;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteManager implements WithGlobalEntityManager, TransactionalOps {

	private static ClienteManager instance = new ClienteManager();

	private ClienteManager() {
	}

	public static ClienteManager getInstance() {
		return instance;
	}

	public void persistirClienteDePrueba() {
		withTransaction(() -> {
			Domicilio domicilio = new Domicilio("Av. Cordoba", 1230, 7, 'A');
			ID id = new ID(TiposId.DNI, "40241506");
			DispositivoEstandar dispEstandar = entityManager().find(DispositivoEstandar.class, new Long(6));
			List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
			dispositivosEstandares.add(dispEstandar);

			DispositivoInteligente aireAcondicionado3500 = entityManager().find(DispositivoInteligente.class,
					new Long(13));
			DispositivoInteligente ventilador = entityManager().find(DispositivoInteligente.class, new Long(17));
			List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

			dispositivosInteligentes.add(aireAcondicionado3500);
			dispositivosInteligentes.add(ventilador);

			Cliente unCliente = new Cliente("Ariel", "Galvan", "galvanariel", "password", id, domicilio, 47581269,
					dispositivosEstandares, dispositivosInteligentes);

			Ubicacion ubicacion = new Ubicacion(5, 2);
			unCliente.setUbicacion(ubicacion);

			entityManager().persist(unCliente);
			entityManager().getTransaction().commit();
		});
	}

	public boolean esCliente(String username) {
		return entityManager().createQuery("from Cliente c where usuario='" + username + "'", Cliente.class)
				.getResultList().size() > 0;
	}

	public Cliente buscarClienteDeLaBDPorUsuario(String username) {

		Cliente cliente = entityManager().createQuery("from Cliente c where usuario='" + username + "'", Cliente.class)
				.getSingleResult();

		return cliente;

	}

	public void ejecutarRecomendacionHogar(String username) {
		Cliente cliente = this.buscarClienteDeLaBDPorUsuario(username);
		RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
		recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
	}

	public Long getIdDelClientePorUsuario(String username) {
		Cliente cliente = entityManager().createQuery("from Cliente c where usuario='" + username + "'", Cliente.class)
				.getSingleResult();

		return cliente.getId();
	}

	public double consumoHogar(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		ReporteConsumoPorHogar reporteConsumoPorHogar = new ReporteConsumoPorHogar();
		Periodo periodo = new Periodo(fechaInicio, fechaFin, null);
		return reporteConsumoPorHogar.consumoDeHogarEnPeriodo(this.getIdDelClientePorUsuario(username), periodo);
	}

	public double consumoUltimoPeriodo(String username) {
		return this.buscarClienteDeLaBDPorUsuario(username).getConsumoUltimoIntervalo();
	}

	public List<Cliente> getClientesDeLaBD() {

		List<Cliente> clientes = entityManager().createQuery("from Cliente c", Cliente.class).getResultList();
		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<Intervalo> getIntervalosDeUso(long id) {

		return (List<Intervalo>) entityManager()
				.createNativeQuery("select * from intervalo i join dispositivointeligente di"
						+ " on i.idDispositivo = di.idDispositivo and di.idCliente = :id", Intervalo.class)
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DispositivoInteligente> getDispositivoConsumo(long id) {

		return (List<DispositivoInteligente>) entityManager().createNativeQuery(
				"select * from dispositivointeligente di join intervalo i "
						+ " on i.idDispositivo = di.idDispositivo and di.idCliente = :id",
				DispositivoInteligente.class).setParameter("id", id).getResultList();
	}

	public void persistirCliente(Cliente cliente) {
		withTransaction(() -> {

			entityManager().persist(cliente);
			entityManager().getTransaction().commit();
		});
	}
	
	public List<Double> auxiliarAdminConsumosWeb(List<Intervalo> li, List<DispositivoInteligente> ld) {
		
		List<Double> listaConsumos = new ArrayList<>();
		
		if(li.size() == ld.size()) {
			
			for(int i=0;i<li.size();i++) {
				
				double consumo = li.get(i).intervaloEnHoras() * ld.get(i).getConsumoEstimadoPorHora();
				listaConsumos.add(consumo);
			}
		}
		
		return listaConsumos;
	}

}