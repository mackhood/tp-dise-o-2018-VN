package reportes;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.usuario.Cliente;

public class ReporteConsumoPorHogar implements WithGlobalEntityManager {
	
	
	public double consumoDeHogarEnPeriodo(long idCliente, Intervalo i) {
		
		Cliente cliente = (Cliente) entityManager().createQuery("from cliente where idCliente = :id").setParameter("id",idCliente);
		return cliente.getDispositivosInteligentes().stream().mapToDouble(d -> d.consumoParaIntervalo(i)).sum();
	}
	
}