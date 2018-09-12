package dominio.zonageografica;

import dominio.entities.ZonaNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import org.hsqldb.rights.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AsignadorDeZonaService {

    private List<ZonaGeografica> zonas = new ArrayList<>();


    public  AsignadorDeZonaService(List<ZonaGeografica> listaZonas){
        this.zonas = listaZonas;

    }
    public Transformador buscarTransformadorCercanoPara(Cliente cliente) {

        ZonaGeografica zonaGeograficaCercana = this.zonaCercanaParaCliente(cliente).get();

        return  this.buscarTransformadorParaCliente(cliente,zonaGeograficaCercana);

    }
    public Optional<ZonaGeografica> zonaCercanaParaCliente (Cliente cliente){
        Optional <ZonaGeografica> zonaParaCliente = Optional.of(zonas.stream().filter(zona-> zona.perteneceClienteAZona(cliente)).min(Comparator.comparingDouble(t -> t.distanciaACliente(cliente.getPosicion()))).get());
        if (zonaParaCliente.isPresent())
                throw  new ZonaNullException("No existe una zona cercana para:"+cliente.nombre());
        return zonaParaCliente;
     }
    private Transformador buscarTransformadorParaCliente(Cliente cliente, ZonaGeografica zonaGeografica) {
        return  zonaGeografica.conectarATransformadorCercano(cliente);
    }

}
