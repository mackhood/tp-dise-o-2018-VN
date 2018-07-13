package Clases.Usuario;

import Clases.Categoria.Categoria;
import Clases.Dispositivo.Convertidor;
import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Dispositivo.DispositivoEstandarInteligente;
import Clases.Simplex.Simplex;
import Clases.repositories.TypeRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;




public class Cliente implements TypeRepo {

    private String nombre;
    private String apellido;
    private ID identificacion;
    private long telefono;
    private Domicilio domicilio;
    private LocalDate fechaDeAlta;
    private Categoria categoria;
    private String username;
    private String password;
    private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();
    private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
    private Simplex resolvedor;
    private  int posicionX;
    private int posicionY;
    private ZonaGeografica zona;


    private  boolean ahorroAutomatico=false;
    public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
                   List<DispositivoEstandar> estandares, List<DispositivoInteligente> inteligentes)
    {

        this.nombre = unNombre;
        this.apellido = unApellido;
        this.identificacion = id;
        this.username = username;
        this.domicilio = unDomicilio;
        this.telefono = unTelefono;
        this.dispositivosEstandar = estandares;
        this.dispositivosInteligentes = inteligentes;
        this.fechaDeAlta = LocalDate.now();
        this.resolvedor = new Simplex();
    }
    public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
                   int posicionX,int posicionY,ZonaGeografica zona) {

        this.nombre = unNombre;
        this.apellido = unApellido;
        this.identificacion = id;
        this.username = username;
        this.domicilio = unDomicilio;
        this.telefono = unTelefono;
        //this.dispositivosEstandar = dispEstandar;
        //this.dispositivosInteligentes = dispInteligentes;
        this.fechaDeAlta = LocalDate.now();
        this.resolvedor = new Simplex();
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.zona=zona;
    }

    public double puntosAcumulados() {
        return dispositivosInteligentes.stream().mapToDouble(inte -> inte.getPuntos()).sum()
                + dispositivosEstandar.stream().mapToDouble(estandar -> estandar.getPuntos()).sum();
    }

    public List<Dispositivo> todosLosDispositivos() {
    	
    	List<Dispositivo> todos = new ArrayList<>();
    	todos.addAll(dispositivosInteligentes);
        todos.addAll(dispositivosEstandar);
        return todos;
    }

    public void agregarModuloAdaptador(DispositivoEstandar disp) {
    	
    	if (this.tieneDispositivo(disp)) {

    	    Convertidor convertidor = new Convertidor();
    		
    		convertidor.convertirInteligente(disp, dispositivosEstandar, dispositivosInteligentes);
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
    }
    
    public void agregarDispositivoEstandar(DispositivoEstandar disp) {
    	
    	dispositivosEstandar.add(disp);
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

    public void activarAhorroAutomatico() {


        this.ahorroAutomatico=true;

    }

    public double horasTotalesConsumidasPorLosDispositivos()
    {
        this.resolvedor.execute(this.todosLosDispositivos());
        return resolvedor.getResultadoFuncionEconomica();
    }


    public double[] horasMaximasDeConsumoPorDispositivo()
    {
        this.resolvedor.execute(this.todosLosDispositivos());
        return resolvedor.getHorasMaximasDispositivo();
    }
    public boolean esHogarEficiente()
    {
        return   this.consumoEnergeticoTotal()<resolvedor.getResultadoFuncionEconomica();
    }



}
