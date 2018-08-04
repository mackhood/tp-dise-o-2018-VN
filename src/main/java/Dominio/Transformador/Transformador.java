package Dominio.Transformador;

import java.util.ArrayList;
import java.util.List;

import Dominio.Dispositivo.Dispositivo;
import Dominio.Usuario.Cliente;
import Dominio.ZonaGeografica.Ubicacion;
import Dominio.ZonaGeografica.ZonaGeografica;

public class Transformador {

	protected ZonaGeografica unaZona;
	List<Cliente> usuariosConectados = new ArrayList<>();

	private Ubicacion ubicacion;

	protected float radioCubierto;

	public Transformador (ZonaGeografica zona, List<Cliente> usuariosConectados,Ubicacion ubicacion,float radioCubierto) {

		this.ubicacion=ubicacion;
		this.unaZona=zona;
		this.usuariosConectados=usuariosConectados;
		this.radioCubierto=radioCubierto;
	}

	public List<Dispositivo> dispositivosUsandoRed() {
		
		List<Dispositivo> dispositivosRed = new ArrayList<>();
		usuariosConectados.forEach(usuariosConectado->dispositivosRed.addAll(usuariosConectado.getDispositivosInteligentes()));
		return  dispositivosRed;

	}
	
	public double suministroActual() {
		
		return this.dispositivosUsandoRed().stream().mapToDouble(disp -> disp.getConsumoEstimadoPorHora()).sum();
		
	}

	public double energiaSuministrada() {

		return usuariosConectados.stream().mapToDouble(usuario->usuario.consumoEnergeticoTotal()).sum();

	}
	public Double calcularDistancia (Cliente cliente) {

		return ubicacion.calcularDistancia(cliente);

	}
}
