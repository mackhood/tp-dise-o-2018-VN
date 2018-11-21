package dominio.transformador;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Transformador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Cliente> usuariosConectados = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Ubicacion ubicacion;

    public Transformador() {
    }


    public Transformador(List<Cliente> usuariosConectados, Ubicacion ubicacion) {

        this.ubicacion = ubicacion;
        this.usuariosConectados.addAll(usuariosConectados);

    }

    public double suministroActual() {
        return this.consumoDeDispositivosInteligentesClientes();
    }

    private double consumoDeDispositivosInteligentesClientes() {
        return usuariosConectados.stream().mapToDouble(cliente -> cliente.consumoDispositivosInteligentes()).sum();
    }

    public double energiaConsumidaClientes() {
        return usuariosConectados.stream().mapToDouble(usuario -> usuario.consumoEnergeticoTotal()).sum();
    }
    
    public double consumoEnIntervalo(Intervalo i) {
    	
    	double totalConsumo =0;
    	
    	/* Genera una lista de listas de dispositivos. Esto es, una lista que contiene todos los dispositivos de todos
    	 * los usuarios y cada sublista representa los dispositivos de cada usuario
    	 */
    	List<List<DispositivoInteligente>> dispositivosConectados = usuariosConectados.stream()
    			.map(user -> user.getDispositivosInteligentes()).collect(Collectors.toList());
    	
    	/* Recorremos los dispositivos de cada uno de los usuarios */
    	
    	for (int j=0; j < dispositivosConectados.size(); j++) {
    		
    		List <DispositivoInteligente> dispositivosPorUsuario = dispositivosConectados.get(j); 
    		
    		/* Recorremos cada uno de los dispositivos de un usuario */
    		
    		for (int k = 0; k < dispositivosPorUsuario.size(); k++) {
    			
    			List <Intervalo> intervalosDeConsumoPorDispositivo = dispositivosPorUsuario.get(k).getIntervalosDeUso();
    			
    			double horasDeConsumoEnPeriodo = intervalosDeConsumoPorDispositivo.stream().mapToDouble(intervalo ->
    					intervalo.horasDentroDe(i)).sum();
    			
    			double consumoDePeriodo = horasDeConsumoEnPeriodo* dispositivosPorUsuario.get(k).getConsumoEstimadoPorHora();
    			
    			totalConsumo = totalConsumo + consumoDePeriodo;
    	}
    }
    	return totalConsumo;
  }
    
    public Double calcularDistancia(Ubicacion ubicacionCliente) {
        return ubicacion.calcularDistancia(ubicacionCliente);
    }

    public void agregarCliente(Cliente cliente) {
        usuariosConectados.add(cliente);
    }

    /*
    public double consumoDeIntervalo(Intervalo intervalo) {

        return usuariosConectados.stream().mapToDouble(cliente -> cliente.consumoDeIntervalo(intervalo)).sum();
    }*/

    public List<Cliente> getUsuariosConectados() {

        return usuariosConectados;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

}
