package dominio.zonageografica;

import dominio.entities.ZonaNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioAsignadorDeZonaService {

    private List<ZonaGeografica> zonas = new ArrayList<>();

    public ServicioAsignadorDeZonaService(List<ZonaGeografica> listaZonas) {
        this.zonas.addAll(listaZonas);
    }

    public void buscarTransformadorCercanoPara(Cliente cliente) {
        ZonaGeografica zonaGeograficaCercana = this.zonaCercanaParaCliente(cliente);
        Transformador transformadorParaCliente = this.buscarTransformadorParaCliente(cliente, zonaGeograficaCercana);
        cliente.setTransformador(transformadorParaCliente);
        transformadorParaCliente.agregarCliente(cliente);
    }

    private ZonaGeografica zonaCercanaParaCliente(Cliente cliente) {
        List<ZonaGeografica> zonaParaCliente = zonas.stream().filter(zona -> zona.perteneceClienteAZona(cliente))
                .collect(Collectors.toList());
        if (zonaParaCliente.isEmpty())
            throw new ZonaNullException("No existe una zona cercana para: " + cliente.getNombre());
        return zonaParaCliente.stream()
                .min(Comparator.comparingDouble(zonaGeografica -> zonaGeografica.distanciaAcliente(cliente))).get();
    }

    private Transformador buscarTransformadorParaCliente(Cliente cliente, ZonaGeografica zonaGeografica) {
        return zonaGeografica.conectarATransformadorCercano(cliente);
    }

}