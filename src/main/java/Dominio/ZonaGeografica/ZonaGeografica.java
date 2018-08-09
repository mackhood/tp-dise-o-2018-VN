package Dominio.ZonaGeografica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Dominio.Transformador.Transformador;
import Dominio.Usuario.Cliente;

public class ZonaGeografica {

protected List<Transformador> transformadores = new ArrayList<>() ;

	public ZonaGeografica (List<Transformador> transformadores) {
	 	 this.transformadores=transformadores;
	}

	public double consumoTotal () {
		return transformadores.stream().mapToDouble(transformador->transformador.energiaConsumidaClientes()).sum();
	}
	public Transformador devolverTransformadorCercano (Cliente cliente) {

		return transformadores.stream().min(Comparator.comparingDouble(t -> t.calcularDistancia(cliente))).get();

	}

	public boolean hayAlgunTransformador() {
		
		return !this.transformadores.isEmpty();
	}


	public double consumoTotalZona() {
		
		return transformadores.stream().mapToDouble(transformador -> transformador.suministroActual()).sum();
	}

    public boolean energiaMayorA900() {
	    return this.consumoTotal() > 900;
    }
    public List<Transformador> getTransformadores(){
	    return  transformadores;

    }

	public void conectarATransformadorCercano(Cliente cliente) {
		Transformador transformadorCercano = this.devolverTransformadorCercano(cliente);
		transformadorCercano.agregarCliente(cliente);

	}
}
