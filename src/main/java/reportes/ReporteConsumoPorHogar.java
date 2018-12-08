package reportes;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Periodo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class ReporteConsumoPorHogar implements WithGlobalEntityManager {


    public double consumoDeHogarEnPeriodo(long idCliente, Periodo p) {

        List<DispositivoInteligente> lista = (List<DispositivoInteligente>) entityManager().createQuery("from DispositivoInteligente where idCliente = :id", DispositivoInteligente.class)
                .setParameter("id", idCliente).getResultList();
        return lista.stream().mapToDouble(d -> d.consumoParaPeriodo(p)).sum();
    }

}