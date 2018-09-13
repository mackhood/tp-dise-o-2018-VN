package dominio.zonageografica;

import dominio.entities.ZonaNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import org.hsqldb.rights.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AsignadorDeZonaService {

	private List<ZonaGeografica> zonas = new ArrayList<>();

	public AsignadorDeZonaService(List<ZonaGeografica> listaZonas) {
		this.zonas.addAll(listaZonas);
	}

	public Transformador buscarTransformadorCercanoPara(Cliente cliente) {
		ZonaGeografica zonaGeograficaCercana = this.zonaCercanaParaCliente(cliente);
		return this.buscarTransformadorParaCliente(cliente, zonaGeograficaCercana);
	}

	public ZonaGeografica zonaCercanaParaCliente(Cliente cliente) {
		List<ZonaGeografica> zonaParaCliente = zonas.stream().filter(zona -> zona.perteneceClienteAZona(cliente))
				.collect(Collectors.toList());
		if (zonaParaCliente.isEmpty())
			throw new ZonaNullException("No existe una zona cercana para: " + cliente.nombre());
		return zonaParaCliente.stream()
				.min(Comparator.comparingDouble(zonaGeografica -> zonaGeografica.distanciaAcliente(cliente))).get();
	}

	private Transformador buscarTransformadorParaCliente(Cliente cliente, ZonaGeografica zonaGeografica) {
		return zonaGeografica.conectarATransformadorCercano(cliente);
	}

}
