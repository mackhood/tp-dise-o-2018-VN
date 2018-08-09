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

		Comparator<Transformador> comparator = Comparator.comparingDouble(transformador -> transformador.calcularDistancia(cliente));
		return transformadores.stream().min((comparator)).get();
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
		Transformador transformadorCercano = transformadores.stream().min((t1,t2)-> Double.compare(t1.calcularDistancia(cliente),t1.calcularDistancia(cliente))).get();
		transformadorCercano.agregarCliente(cliente);

	}
}
