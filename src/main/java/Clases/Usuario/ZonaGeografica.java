package Clases.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import  Clases.Usuario.Transformador;

public class ZonaGeografica {

private List<Transformador> transformadores= new ArrayList<>();
private List<Cliente> clientes = new ArrayList<>();

 public ZonaGeografica (List<Transformador> transformadores,List<Cliente> clientes) {

     this.transformadores=transformadores;
     this.clientes=clientes;

 }

 public  Transformador devolverTransformadorCercano (Cliente cliente) {

OptionalDouble d= transformadores.stream().mapToDouble(transformador->transformador.calcularDistancia(cliente)).min();


 
 }


double consumoTotal () {

    return transformadores.stream().mapToDouble(transformador->transformador.energiaSuministrada()).sum();
}

}
