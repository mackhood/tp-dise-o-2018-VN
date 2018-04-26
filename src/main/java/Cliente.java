package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	String nombreCompleto;
	ID identificacion;
	long telefono;
	Domicilio domicilio;
	LocalDate fechaDeAlta;
	Categoria cat;
	String username;
	String pw;
	List <Dispositivo> dispositivos = new ArrayList <>();
	
	
	Cliente(String nombreCompleto, ID identificacion, Domicilio domicilio, long telefono) {
		
		this.nombreCompleto = nombreCompleto;
		this.identificacion = identificacion;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.fechaDeAlta = LocalDate.now();
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
	
	public long consumo() {
		
		return dispositivos.stream().map(disp -> disp.getConsumoTotal()).count();
	}
	
	public double obtenerFacturaTentativa(EstimadorDeFacturacion facturaAssistant) {
		
		return facturaAssistant.calcularCostosDe(this);
	}
	
	public void setCategoria(Categoria categoria) {
		
		cat = categoria;
	}
	
	public Categoria getCategoria() {
		
		return cat;
	}
}
