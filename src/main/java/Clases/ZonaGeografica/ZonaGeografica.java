package Clases.ZonaGeografica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Clases.Transformador.Transformador;

	public class ZonaGeografica {

protected List<Transformador> transformadores = new ArrayList<>() ;

protected List<ZonaGeografica> zonasCercanas = new ArrayList<>();

protected float radioCubierto;

	
	public ZonaGeografica(float radio) {
	
		radioCubierto = radio;
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
