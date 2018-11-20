package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
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
        	
            List <DispositivoEstandar> listaDEs = RepositorioDispositivo.getInstance().getEstandars();
            
            for (int i=0;i<listaDEs.size();i++) {
            	
            	entityManager().persist(listaDEs.get(i));
            }
            
            List <DispositivoInteligente> listaDIs = RepositorioDispositivo.getInstance().getInteligentes();
            
            for (int i=0;i<listaDIs.size();i++) {
            	
            	entityManager().persist(listaDIs.get(i));
            
            }
            
            entityManager().getTransaction().commit();
        });
    }

    public DispositivoInteligente getDispositivoInteligenteDeLaBDPorID(Long id)
    {
        return entityManager().find(DispositivoInteligente.class, id);
    }

    public List<DispositivoInteligente> getDispositivosInteligentesDeLaBD(){

        List<DispositivoInteligente> inteligentes = entityManager().createQuery("from DispositivoInteligente d",DispositivoInteligente.class).getResultList();
        return inteligentes;

    }

}
