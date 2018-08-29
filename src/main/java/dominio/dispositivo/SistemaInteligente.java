package dominio.dispositivo;

import dominio.usuario.Cliente;

import java.util.stream.Collectors;

public class SistemaInteligente {
	public boolean algunDIencendido(Cliente unCliente) {

		return unCliente.getDispositivosInteligentes().stream().anyMatch(unDI -> unDI.estaEncendido());
	}

	public int cantidadDIencendidos(Cliente unCliente) {

		return unCliente.getDispositivosInteligentes().stream().filter(unDI -> unDI.estaEncendido())
				.collect(Collectors.toList()).size();
	}

	public int cantidadDIapagados(Cliente unCliente) {

		return unCliente.getDispositivosInteligentes().stream().filter(unDI -> unDI.estaApagado())
				.collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos(Cliente unCliente) {
		return unCliente.cantidadDeDispositivos();
	}
}