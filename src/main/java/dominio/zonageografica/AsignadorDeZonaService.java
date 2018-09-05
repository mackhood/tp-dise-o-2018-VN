package dominio.zonageografica;

import dominio.usuario.Cliente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AsignadorDeZonaService {

    List<ZonaGeografica> listaZonas = new ArrayList<>();

    public  AsignadorDeZonaService(List<ZonaGeografica> listaZonas){
        this.listaZonas = listaZonas;

    }
    public void buscarZonaCoberturaClienteYDevolverZona(Cliente cliente) {

        if(this.zonaCercanaParaCliente(cliente).isPresent()){
            this.buscarTransformadorParaCliente(cliente,this.zonaCercanaParaCliente(cliente).get());


        }
        else{
            System.out.println("No existe zona para cliente");
        }

    }
    public Optional<ZonaGeografica> zonaCercanaParaCliente (Cliente cliente){
        Optional <ZonaGeografica> zonaParaCliente = Optional.of(listaZonas.stream().filter(zona-> zona.perteneceClienteAZona(cliente)).min(Comparator.comparingDouble(t -> t.distanciaACliente(cliente.getPosicion()))).get());
        return zonaParaCliente;
     }
    private void buscarTransformadorParaCliente(Cliente cliente, ZonaGeografica zonaGeografica) {
        cliente.setTransformador(zonaGeografica.conectarATransformadorCercano(cliente));
    }

}
