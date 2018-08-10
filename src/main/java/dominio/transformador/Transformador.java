package dominio.transformador;

import java.util.ArrayList;
import java.util.List;


import dominio.dispositivo.Dispositivo;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

public class Transformador {

	private List<Cliente> usuariosConectados = new ArrayList<>();
	private Ubicacion ubicacion;
	protected float radioCubierto;

	public Transformador ( List<Cliente> usuariosConectados,Ubicacion ubicacion,float radioCubierto) {

		this.ubicacion=ubicacion;
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

	//Metodo creado para TestearConsumo de clientes
	public Double calcularConsumoClientes (List<Cliente> clientes){

		return clientes.stream().mapToDouble(usuario->usuario.consumoEnergeticoTotal()).sum();
	}
	public double energiaConsumidaClientes() {

		return this.calcularConsumoClientes(usuariosConectados);

	}
	public Double calcularDistancia (Cliente cliente) {

		return ubicacion.calcularDistancia(cliente);

	}

    public boolean energiaMayorA700() {

        return this.energiaConsumidaClientes() > 700;
    }
    public void agregarCliente(Cliente cliente){

		usuariosConectados.add(cliente);
	}
}
