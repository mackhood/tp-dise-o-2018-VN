package persistence;

import dominio.dispositivo.*;
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

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteManager implements WithGlobalEntityManager, TransactionalOps {

	private static ClienteManager instance = new ClienteManager();

	private ClienteManager() {
	}

	public static ClienteManager getInstance() {
		return instance;
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
		withTransaction(() -> {
			recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
			entityManager().persist(cliente);
			entityManager().getTransaction().commit();
		});

	}

	public List<DispositivoInteligente> getDispositivosExcedidos(String username) {
		Cliente cliente = this.buscarClienteDeLaBDPorUsuario(username);
		RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
		List<DispositivoInteligente> dispositivosExcedidos = recomendacionParaHogarEficiente
				.getDispositivosInteligentesQueSuperanLasHorasMaximas();
		return dispositivosExcedidos.stream().filter(d -> DispositivosManager.getInstance().estaEncendido(d.getId()))
				.collect(Collectors.toList());
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

	public List<Cliente> getClientesDeLaBD() {

		List<Cliente> clientes = entityManager().createQuery("from Cliente c", Cliente.class).getResultList();
		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> obtenerPrimerosNClientes(int val) {
		return (List<Cliente>) entityManager().createNativeQuery("SELECT * FROM cliente LIMIT :n", Cliente.class)
				.setParameter("n", val).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Intervalo> getIntervalosDeUso(long id) {

		return (List<Intervalo>) entityManager().createNativeQuery(
				"select * from intervalo i join dispositivointeligente di"
						+ " on i.idDispositivo = di.idDispositivo and di.idCliente = :id" + " where i.fin is not null",
				Intervalo.class).setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DispositivoInteligente> getDispositivoConsumo(long id) {

		return (List<DispositivoInteligente>) entityManager()
				.createNativeQuery(
						"select * from dispositivointeligente di join intervalo i "
								+ " on i.idDispositivo = di.idDispositivo and di.idCliente = :id",
						DispositivoInteligente.class)
				.setParameter("id", id).getResultList();
	}

	public void persistirCliente(Cliente cliente) {
		withTransaction(() -> {

			entityManager().persist(cliente);
			entityManager().getTransaction().commit();
		});
	}

	@SuppressWarnings("unchecked")
	public List<Double> valorConsumosDeCliente(long id) {
		return (List<Double>) entityManager().createNativeQuery(
				"SELECT consumoEstimadoPorHora*TIMESTAMPDIFF(HOUR,inicio,fin) FROM dispositivointeligente di "
						+ "JOIN intervalo i on i.idDispositivo = di.idDispositivo WHERE di.idCliente = :idc AND i.fin IS NOT NULL")
				.setParameter("idc", id).getResultList();
	}

	public List<Double> auxiliarAdminConsumosWeb(List<Intervalo> li, List<DispositivoInteligente> ld) {

		List<Double> listaConsumos = new ArrayList<>();

		if (li.size() == ld.size()) {

			for (int i = 0; i < li.size(); i++) {

				double consumo = li.get(i).intervaloEnHoras() * ld.get(i).getConsumoEstimadoPorHora();
				listaConsumos.add(consumo);
			}
		}

		return listaConsumos;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtradoClientes(String nombre, String apellido, String calle) {
		return (List<Cliente>) entityManager()
				.createNativeQuery("SELECT * FROM cliente WHERE Calle LIKE :calle AND Nombre LIKE :nombre "
						+ "AND apellido LIKE :apellido ORDER BY Calle DESC", Cliente.class)
				.setParameter("calle", '%' + calle + '%').setParameter("apellido", '%' + apellido + '%')
				.setParameter("nombre", '%' + nombre + '%').getResultList();
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Cliente>
	 * filtrarClientesPorCalle(String calle) { return (List<Cliente>)
	 * entityManager().
	 * createNativeQuery("SELECT * FROM cliente WHERE Calle LIKE :calle " +
	 * "ORDER BY Calle DESC",Cliente.class) .setParameter("calle",
	 * '%'+calle+'%').getResultList(); }
	 * 
	 * @SuppressWarnings("unchecked") public List<Cliente>
	 * filtrarClientesPorNombre(String nombre) { return (List<Cliente>)
	 * entityManager().
	 * createNativeQuery("SELECT * FROM cliente WHERE Nombre LIKE :nombre " +
	 * "ORDER BY Nombre DESC",Cliente.class) .setParameter("nombre",
	 * '%'+nombre+'%').getResultList(); }
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrarClientesPorApellido(String apellido) {
		return (List<Cliente>) entityManager()
				.createNativeQuery("SELECT * FROM cliente WHERE apellido LIKE :apellido " + "ORDER BY apellido DESC",
						Cliente.class)
				.setParameter("apellido", '%' + apellido + '%').getResultList();
	}

	@SuppressWarnings("unchecked")
	public Intervalo ultimoIntervalo(long id) {
		List<Intervalo> li = entityManager().createNativeQuery(
				"SELECT * FROM intervalo WHERE fin = " + "(SELECT max(fin) FROM intervalo WHERE idDispositivo IN "
						+ "(SELECT idDispositivo FROM dispositivoInteligente WHERE idCliente = :id))",
				Intervalo.class).setParameter("id", id).getResultList();
		return li.get(0);
	}

	public Boolean tieneDispositivos(long id) {
		BigInteger cant = (BigInteger) entityManager()
				.createNativeQuery("SELECT COUNT(*) FROM dispositivointeligente WHERE idCliente = :id")
				.setParameter("id", id).getSingleResult();
		return cant.doubleValue() > 0;
	}
}