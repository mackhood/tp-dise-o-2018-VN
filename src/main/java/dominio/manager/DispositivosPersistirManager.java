package dominio.manager;

import dominio.repositories.RepositorioDispositivo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class DispositivosPersistirManager implements WithGlobalEntityManager, TransactionalOps {

    private static DispositivosPersistirManager instance = new DispositivosPersistirManager();
    public void persistirDispositivosDelRepositorio()
    {
        withTransaction(()->{
            RepositorioDispositivo.getInstance().getEstandars().stream().forEach(dispositivoEstandar -> entityManager().persist(dispositivoEstandar));
            RepositorioDispositivo.getInstance().getInteligentes().stream().forEach(dispositivoInteligente -> entityManager().persist(dispositivoInteligente));

            entityManager().getTransaction().commit();
        });
    }
    private DispositivosPersistirManager(){}

    public static DispositivosPersistirManager getInstance(){return instance;}

}
