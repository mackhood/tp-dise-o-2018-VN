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
    
    public double consumoEnIntervalo(Intervalo intervalo) {
    	return usuariosConectados.stream().mapToDouble(usuario -> usuario.consumoEnUnIntervalo(intervalo)).sum();
    }
    public double consumoPromedioEnIntervalo(Intervalo intervalo) {
        return this.consumoEnIntervalo(intervalo)/usuariosConectados.size();
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
