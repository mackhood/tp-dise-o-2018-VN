package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	String nombreCompleto;
	String apellido;
	ID identificacion;
	long telefono;
	Domicilio domicilio;
	LocalDate fechaDeAlta;
	Categoria categoria;
	String username;
	String pw;
	List <Dispositivo> dispositivos = new ArrayList <>();
	
	
	Cliente(String unNombreCompleto, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
			List<Dispositivo> listaDispositivos) {
		
		this.nombreCompleto = unNombreCompleto;
		this.apellido = unApellido;
		this.identificacion = id;
		this.username = username;
		this.domicilio = unDomicilio;
		this.telefono = unTelefono;
		this.dispositivos = listaDispositivos;
		this.fechaDeAlta = LocalDate.now();
		this.actualizarCategoria();
	}
	
	public void actualizarCategoria() {
		
		AsignarCategoria ac = new AsignarCategoria();
		this.setCategoria(ac.definirCategoriaPara(this));
	}
	
	public boolean algunDispositivoEncendido() {
		
		return dispositivos.stream().anyMatch(disp -> disp.isEncendido());
	}
	
	public long cantidadDeDispositivosEncendidos() {
		
		return dispositivos.stream().filter(disp -> disp.isEncendido()).count();
	}
	
	public long cantidadDeDispositivosApagados() {
		
		return dispositivos.stream().filter(disp -> !disp.isEncendido()).count();
	}
	
	public int cantidadDeDispositivos() {
		
		return dispositivos.size();
	}
	
	public void agregarDispositivo(Dispositivo disp) {
		
		dispositivos.add(disp);
	}
	
	public double consumoEnergeticoTotal() {
		
		return dispositivos.stream().mapToDouble(disp -> disp.getConsumo()).sum();
	}
	
	public double obtenerGastosAproximados() {
		
		this.actualizarCategoria();
		return categoria.calcularCostosPara(this);
	}
	
	public void setCategoria(Categoria unaCategoria) {
		
		categoria = unaCategoria;
	}
	
	public Categoria getCategoria() {
		
		return categoria;
	}
}
