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

        return this.getIndexOfMin(transformadores,cliente);


 
 }


double consumoTotal () {

    return transformadores.stream().mapToDouble(transformador->transformador.energiaSuministrada()).sum();
}
public Transformador getIndexOfMin(List<Transformador> data,Cliente cliente) {
        Double min = Double.MAX_VALUE;
        Transformador transformador = null;
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            Double f = data.get(i).calcularDistancia(cliente);
            if (min > f) {
                min = f;
                index = i;
                transformador = data.get(i);
            }
        }
        return transformador;
 }

}
