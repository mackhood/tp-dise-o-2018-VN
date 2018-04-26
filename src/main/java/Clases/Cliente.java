package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	String nombreCompleto;
	String apellido;
	String nombreUsuario;
	ID identificacion;
	long telefono;
	Domicilio domicilio;
	LocalDate fechaDeAlta;
	Categoria categoria;
	String username;
	String pw;
	List <Dispositivo> dispositivos = new ArrayList <>();
	
	
	Cliente(String unNombreCompleto, String unApellido, String unNombreUsuario, ID unaIdentificacion, Domicilio unDomicilio, long unTelefono,
			List<Dispositivo> unosDisp) {
		
		this.nombreCompleto = unNombreCompleto;
		this.apellido = unApellido;
		this.identificacion = unaIdentificacion;
		this.nombreUsuario = unNombreUsuario;
		this.domicilio = unDomicilio;
		this.telefono = unTelefono;
		this.dispositivos = unosDisp;
		this.fechaDeAlta = LocalDate.now();
		this.actualizarCategoria();
	}
	
	public void actualizarCategoria()
	{
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
		
		return dispositivos.stream().map(disp -> disp.getConsumo()).reduce(0.0, (acum, consumo) -> acum + consumo);
	}
	
	public double obtenerFacturaTentativa() {
		/*
		AsignarCategoria ac = new AsignarCategoria();
		this.setCategoria(ac.definirCategoriaPara(this));
		
		EstimadorDeFacturacion ef = new EstimadorDeFacturacion();
		return ef.calcularCostosDe(this);
		*/
		this.actualizarCategoria();
		return categoria.getCostoFijo() + categoria.getCostoVariable() * this.consumoEnergeticoTotal();
	}
	
	public void setCategoria(Categoria unaCategoria) {
		
		categoria = unaCategoria;
	}
	
	public Categoria getCategoria() {
		
		return categoria;
	}
}
