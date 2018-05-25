package Clases.Usuario;

import Clases.Categoria.AsignadorDeCategoria;
import Clases.Categoria.Categoria;
import Clases.Dispositivo.Convertidor;
import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Dispositivo.DispositivoEstandarInteligente;
import Clases.Dispositivo.EstadoApagado;
import Clases.Dispositivo.EstadoDispositivo;
import Clases.Dispositivo.EstadoEncendido;
import Clases.entities.ProcessingDataFailedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nombre;
    private String apellido;
    private ID identificacion;
    private long telefono;
    private Domicilio domicilio;
    private LocalDate fechaDeAlta;
    private Categoria categoria;
    private String username;
    private String password;
    private double puntosAcumulados = 0;
    private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();
    private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

    public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
                   List<DispositivoEstandar> dispEstandar, List <DispositivoInteligente> dispInteligentes) {

        this.nombre = unNombre;
        this.apellido = unApellido;
        this.identificacion = id;
        this.username = username;
        this.domicilio = unDomicilio;
        this.telefono = unTelefono;
        this.dispositivosEstandar = dispEstandar;
        this.dispositivosInteligentes = dispInteligentes;
        this.fechaDeAlta = LocalDate.now();
    }

    
    public double puntosAcumulados() {
        return puntosAcumulados;
    }
    
    public List<Dispositivo> todosLosDispositivos() {
    	
    	List<Dispositivo> todos = new ArrayList<>();
    	todos.addAll(dispositivosEstandar);
    	todos.addAll(dispositivosInteligentes);
    	return todos;
    }

    public DispositivoInteligente agregarModuloAdaptador(Convertidor moduloAdaptador, DispositivoEstandar disp) {
    	
    	if (this.tieneDispositivo(disp)) {
    		
    		DispositivoEstandarInteligente nuevo = null;
			DispositivoEstandarInteligente nuevoDispositivo =moduloAdaptador.convertirInteligente(disp, nuevo);
			dispositivosEstandar.remove(disp);
    		dispositivosInteligentes.add(nuevoDispositivo);
            this.sumarPuntos(10);
            return nuevo; 
    	}
    	
    /* Esto se cambia, lo pongo asi para ir haciendo lo demas 
    	  y despues tratar las excepciones todas juntas. Para mi si quiere convertir un disp
     		que no tiene deber�a tirar excepcion, pero es charlable */
    	
    	else throw new RuntimeException();
    }
    
    public boolean tieneDispositivo (DispositivoEstandar disp) {
    	
    	return dispositivosEstandar.contains(disp);
    }
    
    public boolean algunDispositivoEncendido() {
    	
    	return dispositivosInteligentes.stream().anyMatch(disp -> disp.estaEncendido());
    }

    public long cantidadDeDispositivosEncendidos() {
    	
    	return dispositivosInteligentes.stream().filter(disp -> disp.estaEncendido()).count();
    }

    public long cantidadDeDispositivosApagados() {

        return dispositivosInteligentes.stream().filter(disp -> disp.estaApagado()).count();
    }

    public int cantidadDeDispositivos() {
    
    	return todosLosDispositivos().size();
    }

    public void agregarDispositivoInteligente(DispositivoInteligente disp) {

        dispositivosInteligentes.add(disp);
        this.sumarPuntos(15);
    }
    
    public void agregarDispositivoEstandar(DispositivoEstandar disp) {
    	
    	dispositivosEstandar.add(disp);
    }

    public void sumarPuntos(int puntos) {
        puntosAcumulados = puntosAcumulados + puntos;
    }

    public void usarDispositivo(DispositivoEstandar dispositivo, int cantHorasEstimativa) {
        
    	dispositivo.serUsado(cantHorasEstimativa);
    }
    
    public double consumoEnergeticoTotal() {

        return todosLosDispositivos().stream().mapToDouble(disp -> disp.getConsumoTotal()).sum();
    }
    
    public double obtenerGastosAproximados() {

        return categoria.calcularCostosPara(this);
    }

    public void setCategoria(Categoria unaCategoria) {

        categoria = unaCategoria;
    }

    public Categoria getCategoria() {

        return this.categoria;
    }

    public String nombreCategoria() {

        return this.categoria.getNombreCategoria();
    }

    public String nombre() {
 
		return this.nombre;
    }
	
	public List<DispositivoInteligente> getDispositivosInteligentes() { 

		return dispositivosInteligentes; 
	} 
}
