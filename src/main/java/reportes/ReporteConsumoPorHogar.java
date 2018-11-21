package reportes;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.usuario.Cliente;

public class ReporteConsumoPorHogar implements WithGlobalEntityManager {
	
	
	public double consumoDeHogarEnPeriodo(long idCliente, Periodo p) {
		
		List<DispositivoInteligente> lista = (List<DispositivoInteligente>) entityManager().createQuery("from DispositivoInteligente where idCliente = :id",DispositivoInteligente.class)
				.setParameter("id",idCliente).getResultList();
		return lista.stream().mapToDouble(d -> d.consumoParaPeriodo(p)).sum();
	}
	
}