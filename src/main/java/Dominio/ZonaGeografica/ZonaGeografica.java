package Dominio.ZonaGeografica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.Transformador.Transformador;
import Dominio.Usuario.Cliente;

public class ZonaGeografica {

protected List<Transformador> transformadores = new ArrayList<>() ;

protected List<ZonaGeografica> zonasCercanas = new ArrayList<>();



	public ZonaGeografica (List<Transformador> transformadores, List<Cliente> clientes) {
	 	 this.transformadores=transformadores;
	}


	double consumoTotal () {

		return transformadores.stream().mapToDouble(transformador->transformador.energiaSuministrada()).sum();
	}
	public Transformador devolverTransformadorCercano (Cliente cliente) {

		Comparator<Transformador> comparator = Comparator.comparingDouble(transformador -> transformador.calcularDistancia(cliente));
		return transformadores.stream().min((comparator)).get();
	}


	public boolean hayAlgunTransformador() {
		
		return !this.transformadores.isEmpty();
	}
	
	public void setZonaCercana(ZonaGeografica unaZona) {
		
		this.zonasCercanas.add(unaZona);
	}
	
	public ZonaGeografica zonaConTransformadorMasCercana() {
		
		return zonasCercanas.stream().filter(zona -> zona.hayAlgunTransformador()).collect(Collectors.toList()).get(0);
	}
	
	public double consumoTotalZona() {
		
		return transformadores.stream().mapToDouble(transformador -> transformador.suministroActual()).sum();
	}
}
