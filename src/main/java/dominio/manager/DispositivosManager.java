package dominio.manager;

import dominio.dispositivo.DispositivoInteligente;
import dominio.repositories.RepositorioDispositivo;
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
            RepositorioDispositivo.getInstance().getEstandars().stream().forEach(dispositivoEstandar -> entityManager().persist(dispositivoEstandar));
            RepositorioDispositivo.getInstance().getInteligentes().stream().forEach(dispositivoInteligente -> entityManager().persist(dispositivoInteligente));

            entityManager().getTransaction().commit();
        });
    }

    public DispositivoInteligente traerCiertoDispositivoInteligenteDeLaDB(Long id)
    {
        return entityManager().find(DispositivoInteligente.class, id);
    }

    public List<DispositivoInteligente> getDispositivosInteligentes(){

        List<DispositivoInteligente> inteligentes = entityManager().createQuery("from DispositivoInteligente d",DispositivoInteligente.class).getResultList();
        return inteligentes;

    }

}
