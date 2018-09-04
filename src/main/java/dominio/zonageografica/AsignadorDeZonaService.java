package dominio.zonageografica;

import dominio.transformador.Transformador;
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
    public void buscarZonaCoberturaClienteYDevolverZona(Cliente cliente){
        Optional <ZonaGeografica> zonaParaCliente = Optional.of(listaZonas.stream().filter(zona-> zona.perteneceClienteAZona(cliente)).min(Comparator.comparingDouble(t -> t.distanciaACliente(cliente.getPosicion()))).get());
        if (zonaParaCliente.isPresent()){
                this.buscarTransformadorParaCliente(cliente,zonaParaCliente.get());
        }
        else{
            System.out.println("no zona de servicio para cliente");
        }
    }

    private void buscarTransformadorParaCliente(Cliente cliente, ZonaGeografica zonaGeografica) {
        zonaGeografica.conectarATransformadorCercano(cliente);
    }

}
