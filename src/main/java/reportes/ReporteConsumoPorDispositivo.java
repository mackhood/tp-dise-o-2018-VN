package reportes;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;

public class ReporteConsumoPorDispositivo implements WithGlobalEntityManager {
	
	
	public double consumoPorDispositivo(long idUser, long idDisp) {
		
		DispositivoInteligente disp = (DispositivoInteligente) entityManager().createQuery("from dispositivointeligente where idDispositivo = :id and idCliente = :idc")
				.setParameter("id", idDisp).setParameter("idc", idUser);
		
		return disp.getConsumoTotal()/this.totalDispositivos(idUser);
		
	}
	
	public double totalDispositivos(long idUser) {
		
		Cliente cliente = (Cliente) entityManager().createQuery("from cliente where idCliente = :id").setParameter("id", idUser);
		
		return cliente.getDispositivosInteligentes().size();
	}

}
