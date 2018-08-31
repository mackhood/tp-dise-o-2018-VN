package dominio.zonageografica;

import dominio.transformador.Transformador;
import dominio.usuario.Cliente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AsignadorDeZonaService {

    List<ZonaGeografica> listaZonas = new ArrayList<>();

    public  AsignadorDeZonaService(List<ZonaGeografica> listaZonas){
        this.listaZonas = listaZonas;

    }
    public Transformador buscarZonaCoberturaClienteYDevolverTransformador(Cliente cliente){
        ZonaGeografica zonaParaCliente =listaZonas.stream().filter(zona-> zona.perteneceClienteAZona(cliente)).min(Comparator.comparingDouble(t -> t.distanciaACliente(cliente.getPosicion()))).get();
        return zonaParaCliente.devolverTransformadorCercano(cliente.getPosicion());

    }


}
