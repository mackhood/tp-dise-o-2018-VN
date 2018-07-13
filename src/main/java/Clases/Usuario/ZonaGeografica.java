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



double consumoTotal () {

    return transformadores.stream().mapToDouble(transformador->transformador.energiaSuministrada()).sum();
}
public Transformador devolverTransformadorCercano (Cliente cliente) {
        if(transformadores.size() == 0){
            System.out.println("No tiene transformadores la zona");
            return null;
        }
        else{
            Double min = transformadores.get(0).calcularDistancia(cliente);
            Transformador transformador = transformadores.get(0);
            for (int i = 0; i < transformadores.size(); i++) {
                Double f = transformadores.get(i).calcularDistancia(cliente);
                if (min > f) {
                    min = f;
                    transformador = transformadores.get(i);
                }
            }
            if (min > transformador.getRadio()){
                System.out.println("No existe Transformador Cercano");
                return  null;
            }
            return transformador;
        }
 }

}
