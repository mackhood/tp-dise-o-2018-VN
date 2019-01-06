package persistence;

import dominio.repositories.RepositorioDispositivo;
import dominio.repositories.RepositorioTipoDispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.TipoDispositivo;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.math.BigInteger;
import java.util.List;

public class DispositivosManager implements WithGlobalEntityManager, TransactionalOps {

	private static DispositivosManager instance = new DispositivosManager();

	private DispositivosManager() {
	}

	public static DispositivosManager getInstance() {
		return instance;
	}

	public void persistirTiposDeDispositivo() {
		withTransaction(() -> {
			RepositorioTipoDispositivo.getInstance().getTipos().stream().forEach(t -> entityManager().persist(t));
			entityManager().getTransaction().commit();
		});
	}

	public void persistirDispositivosDelRepositorio() {
		withTransaction(() -> {
			RepositorioDispositivo.getInstance().getEstandars().stream()
					.forEach(dispositivoEstandar -> entityManager().persist(dispositivoEstandar));
			RepositorioDispositivo.getInstance().getInteligentes().stream()
					.forEach(dispositivoInteligente -> entityManager().persist(dispositivoInteligente));

			entityManager().getTransaction().commit();
		});
	}

	public TipoDispositivo getTipoPorNombre(String nombre) {
		return entityManager().createQuery("from TipoDispositivo where nombre = :nom", TipoDispositivo.class)
				.setParameter("nom", nombre).getSingleResult();
	}

	public DispositivoInteligente getDispositivoInteligenteDeLaBDPorID(Long id) {
		return entityManager().find(DispositivoInteligente.class, id);
	}

	public DispositivoEstandar getDispositivoEstandarDeLaDPorEquipoConcreto(String unEquipoConcreto) {
		return (DispositivoEstandar) entityManager()
				.createNativeQuery("SELECT* FROM dispositivoestandar WHERE equipoConcreto = :equipConcreto",
						DispositivoEstandar.class)
				.setParameter("equipConcreto", unEquipoConcreto).getSingleResult();
	}

	public List<DispositivoInteligente> getDispositivosInteligentesDeLaBD() {

		List<DispositivoInteligente> inteligentes = entityManager()
				.createQuery("from DispositivoInteligente d", DispositivoInteligente.class).getResultList();
		return inteligentes;

	}

	public void persistirDispositivoInteligente(DispositivoInteligente di) {
		withTransaction(() -> {

			entityManager().persist(di);
			entityManager().getTransaction().commit();
		});
	}

	public void persistirTipoDispositivo(TipoDispositivo tipo) {
		withTransaction(() -> {

			entityManager().persist(tipo);
			entityManager().getTransaction().commit();
		});
	}

	public DispositivoInteligente getDispositivoPorDetalleEquipo(String detalle) {
		return (DispositivoInteligente) entityManager()
				.createNativeQuery("SELECT * FROM dispositivointeligente WHERE equipoConcreto = :detalle LIMIT 1",
						DispositivoInteligente.class)
				.setParameter("detalle", detalle).getSingleResult();
	}

	public DispositivoEstandar getDispositivoEstandarDeLaBD(Long id) {
		return entityManager().find(DispositivoEstandar.class, id);

	}

	public double getConsumoUltimoPeriodo(Long id) {
		return this.getDispositivoInteligenteDeLaBDPorID(id).consumoUltimoIntervalo();
	}

	public void borrarDispositivoInteligene(DispositivoInteligente disp) {

		withTransaction(() -> {
			entityManager().remove(disp);
			entityManager().getTransaction().commit();
		});
	}

	@SuppressWarnings("unchecked")
	public List<DispositivoInteligente> getDispositivosDeCliente(long id) {
		return (List<DispositivoInteligente>) entityManager()
				.createNativeQuery("SELECT * FROM dispositivointeligente " + "WHERE idCliente = :id",
						DispositivoInteligente.class)
				.setParameter("id", id).getResultList();
	}

	public void agregarDispositivoACliente(long idcliente, long idDisp) {
		withTransaction(() -> {

			entityManager()
					.createNativeQuery("UPDATE dispositivointeligente SET idCliente = :idc WHERE idDispositivo = :idd")
					.setParameter("idc", idcliente).setParameter("idd", idDisp).executeUpdate();

			entityManager().getTransaction().commit();
		});
	}

	@SuppressWarnings("unchecked")
	public DispositivoInteligente dispUltimoConsumo(long id) {
		List<DispositivoInteligente> li = entityManager().createNativeQuery(
				"SELECT * FROM dispositivointeligente di JOIN intervalo i ON di.idDispositivo = i.idDispositivo "
						+ "WHERE i.fin = (SELECT MAX(fin) FROM intervalo "
						+ "WHERE idDispositivo IN (SELECT idDispositivo FROM dispositivointeligente WHERE idCliente = :id))",
				DispositivoInteligente.class).setParameter("id", id).getResultList();
		return li.get(0);
	}

	public Boolean tieneIntervalos(long id) {
		BigInteger cantidad = (BigInteger) entityManager()
				.createNativeQuery("SELECT COUNT(*) FROM intervalo WHERE idDispositivo = :id").setParameter("id", id)
				.getSingleResult();
		return cantidad.doubleValue() > 0;
	}

	@SuppressWarnings("unchecked")
	public List<TipoDispositivo> getTipos() {
		return entityManager().createNativeQuery("SELECT * FROM tipodispositivo", TipoDispositivo.class)
				.getResultList();
	}
}
