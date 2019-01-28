package persistence;

import dominio.dispositivo.TipoDispositivo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class TipoDispositivoManager implements WithGlobalEntityManager, TransactionalOps {

	private static TipoDispositivoManager instance = new TipoDispositivoManager();

	public TipoDispositivo getTipoDispositivoDeLaBDPorID(Long id) {
		return entityManager().find(TipoDispositivo.class, id);
	}

	public TipoDispositivo getTipoPorNombre(String nombre) {

		return entityManager().createQuery("from TipoDispositivo where nombre = :nombre", TipoDispositivo.class)
				.setParameter("nombre", nombre).getSingleResult();
	}

	public static TipoDispositivoManager getInstance() {
		return instance;
	}

}
