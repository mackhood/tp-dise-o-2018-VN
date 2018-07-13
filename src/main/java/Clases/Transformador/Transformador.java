package Clases.Transformador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Clases.Dispositivo.Dispositivo;
import Clases.Usuario.Cliente;
import Clases.ZonaGeografica.ZonaGeografica;

public class Transformador {

	protected ZonaGeografica unaZona;
	List<Cliente> usuariosConectados = new ArrayList<>();
	List <Dispositivo> dispositivosConectados = new ArrayList<>();
	
	public Transformador(ZonaGeografica zona) {
		
		unaZona = zona;
	}
	
	public void dispositivosUsandoRed() {
		
		/* No se como convertir el stream a lista de dispositivos, la idea era
			tomando la lista de usuarios, sacar una lista de dispositivos conectados a la red
				dados los disp de cada usuario, pense que se podia castear pero no */
		
		List <Dispositivo> disp = usuariosConectados.stream().flatMap(user -> user.todosLosDispositivos());
		dispositivosConectados.addAll(disp);
	}
	
	public double suministroActual() {
		
		return dispositivosConectados.stream().mapToDouble(disp -> disp.getConsumoEstimadoPorHora()).sum();
		
	}
}
