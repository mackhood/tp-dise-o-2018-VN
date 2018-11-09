package test.database;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.DispositivosManager;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class cargarIntervaloDispositivo extends AbstractPersistenceTest implements WithGlobalEntityManager {
    @Test
    public void cargarIntervaloADispositivoID13()
    {
        DispositivoInteligente di = DispositivosManager.getInstance().traerCiertoDispositivoInteligenteDeLaDB(new Long(13));
        LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30, 100);
        LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30, 100);
        Intervalo intervalo = new Intervalo(horaEncendido,horaApagado);
        List<Intervalo> intervalos = new ArrayList<>();
        intervalos.add(intervalo);

        di.agregarListaIntervalos(intervalos);

        entityManager().persist(di);
        entityManager().getTransaction().commit();

    }

    @Test
    public void test()
    {
        DispositivoInteligente di = DispositivosManager.getInstance().traerCiertoDispositivoInteligenteDeLaDB(new Long(13));
        Assert.assertEquals(1,di.getIntervalosDeUso().size());
    }
}
