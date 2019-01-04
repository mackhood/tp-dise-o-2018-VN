package persistence;
import dominio.repositories.RepositorioDispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

public class DispositivosManager implements WithGlobalEntityManager, TransactionalOps {

	private static DispositivosManager instance = new DispositivosManager();

	private DispositivosManager() {
	}

	public static DispositivosManager getInstance() {
		return instance;
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

	public DispositivoInteligente getDispositivoInteligenteDeLaBDPorID(Long id) {
		return entityManager().find(DispositivoInteligente.class, id);
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

	@SuppressWarnings("unchecked")
	public List<DispositivoInteligente> getDispositivosParaAlta() {
		return (List<DispositivoInteligente>) entityManager()
				.createNativeQuery("SELECT * FROM dispositivointeligente WHERE idCliente IS NULL"
						+ " AND equipoConcreto IS NOT NULL ORDER BY Nombre ASC", DispositivoInteligente.class)
				.getResultList();
	}

	public DispositivoInteligente getDispositivoPorDetalleEquipo(String detalle) {
		return (DispositivoInteligente) entityManager()
				.createNativeQuery("SELECT * FROM dispositivointeligente WHERE nombreConcreto = :detalle LIMIT 1",
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
	public DispositivoInteligente dispUltimoConsumo() {
		List<DispositivoInteligente> li = entityManager()
				.createNativeQuery("SELECT idDispositivo FROM intervalo WHERE fin = (SELECT MAX(fin) FROM intervalo)",
						DispositivoInteligente.class)
				.getResultList();
		return li.get(0);
	}

	public Boolean tieneIntervalos(long id) {
		BigInteger cantidad = (BigInteger) entityManager()
				.createNativeQuery("SELECT COUNT(*) FROM intervalo WHERE idDispositivo = :id")
				.setParameter("id", id).getSingleResult();
		return cantidad.doubleValue() > 0;
	}
}
