package reportes;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import javax.persistence.*;

public class ReporteConsumoPorDispositivo implements WithGlobalEntityManager {
	
	
	public double consumoPorDispositivo(long idUser, long idDisp) {
		
		DispositivoInteligente disp = (DispositivoInteligente) entityManager().createQuery("from DispositivoInteligente where idDispositivo = :id and idCliente = :idc",DispositivoInteligente.class)
				.setParameter("id", idDisp).setParameter("idc", idUser).getSingleResult();
		
		return disp.getConsumoTotal()/this.totalDispositivos(idUser);
		
	}
	
	public double totalDispositivos(long idUser) {
		
		Cliente cliente = (Cliente) entityManager().createQuery("from Cliente where idCliente = :id",Cliente.class).setParameter("id", idUser).getSingleResult();
		
		return cliente.getDispositivosInteligentes().size();
	}
	
}
