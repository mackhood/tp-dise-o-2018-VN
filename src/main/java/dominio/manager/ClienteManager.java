package dominio.manager;

import dominio.Consumo;
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
			Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
			ID id = new ID(TiposId.DNI, "10125789");
			DispositivoEstandar dispEstandar = entityManager().find(DispositivoEstandar.class, new Long(3));
			List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
			dispositivosEstandares.add(dispEstandar);

			DispositivoInteligente aireAcondicionado3500 = entityManager().find(DispositivoInteligente.class,
					new Long(10));
			DispositivoInteligente ventilador = entityManager().find(DispositivoInteligente.class, new Long(17));
			List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

			dispositivosInteligentes.add(aireAcondicionado3500);
			dispositivosInteligentes.add(ventilador);

			Cliente unCliente = new Cliente("ariel", "galvan", "galvanariel", "password", id, domicilio, 47581269,
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
	public void ejecutarRecomendacionHogar(String username)
	{
		Cliente cliente = this.buscarClienteDeLaBDPorUsuario(username);
		RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
		recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
	}

	public Long getIdDelClientePorUsuario(String username)
	{
		Cliente cliente = entityManager().createQuery("from Cliente c where usuario='" + username + "'", Cliente.class)
				.getSingleResult();

		return cliente.getId();
	}

	public double consumoHogar(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin)
	{
		ReporteConsumoPorHogar reporteConsumoPorHogar = new ReporteConsumoPorHogar();
		Periodo periodo = new Periodo(fechaInicio,fechaFin,null);
		return reporteConsumoPorHogar.consumoDeHogarEnPeriodo(this.getIdDelClientePorUsuario(username),periodo);
	}

	@SuppressWarnings("unchecked")
	public List<Consumo> getConsumosDeCliente(long id) {

		return (List<Consumo>) entityManager()
				.createNativeQuery("select inicio, fin, consumoEstimadoPorHora*timestampdiff(HOUR,inicio,fin) "
						+ "from intervalo i join dispositivointeligente di ON i.idDispositivo = di.idDispositivo "
						+ "and di.idCliente = :id")
				.setParameter("id", id).getResultList();
	}

	public List<Cliente> getClientesDeLaBD() {

		List<Cliente> clientes = entityManager().createQuery("from Cliente c", Cliente.class).getResultList();
		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<Intervalo> getIntervalosDeUso(long id) {
    	
    	return (List<Intervalo>) entityManager().createNativeQuery("select * from intervalo i join dispositivointeligente di"
    			+ " on i.idDispositivo = di.idDispositivo and di.idCliente = :id",Intervalo.class).setParameter("id", id).getResultList();
    }

}