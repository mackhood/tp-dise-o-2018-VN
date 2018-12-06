package reportes;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.usuario.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class ReporteConsumoPorDispositivo implements WithGlobalEntityManager {

    public double consumoPorDispositivo(long idUser, long idDisp) {

        DispositivoInteligente disp = (DispositivoInteligente) entityManager()
                .createQuery("from DispositivoInteligente where idDispositivo = :id and idCliente = :idc",
                        DispositivoInteligente.class)
                .setParameter("id", idDisp).setParameter("idc", idUser).getSingleResult();

        return disp.getConsumoTotal();

    }

    public double totalDispositivos(long idUser) {

        Cliente cliente = (Cliente) entityManager().createQuery("from Cliente where idCliente = :id", Cliente.class)
                .setParameter("id", idUser).getSingleResult();

        return cliente.getDispositivosInteligentes().size();
    }

    public double consumoPromedioDispositivoEnPeriodo(long idDisp, Periodo p) {

        DispositivoInteligente d = (DispositivoInteligente) entityManager()
                .createQuery("from DispositivoInteligente where idDispositivo = :id", DispositivoInteligente.class)
                .setParameter("id", idDisp).getSingleResult();

        List<Intervalo> li = (List<Intervalo>) entityManager()
                .createQuery("from Intervalo where idDispositivo = :id", Intervalo.class).setParameter("id", idDisp)
                .getResultList();

        double totalHorasPeriodo = p.convertir().intervaloEnHoras();

        double horasDeUso = li.stream().mapToDouble(i -> i.horasDentroDe(p.convertir())).sum();

        return horasDeUso * d.getConsumoEstimadoPorHora() / totalHorasPeriodo;
    }

}
