package dominio.zonageografica;

import dominio.entities.TransformadorNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class ZonaGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Transformador> transformadores = new ArrayList<>();
    @Column(length = 100)
    private String descripcion;
    @Basic
    private Double radio;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ubicacion ubicacion;

    public ZonaGeografica() {
    }

    public ZonaGeografica(String descripcion, List<Transformador> transformadores, Ubicacion ubicacion, Double radio) {
        this.transformadores.addAll(transformadores);
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.radio = radio;
    }

    public double consumoTotal() {
        return transformadores.stream().mapToDouble(transformador -> transformador.energiaConsumidaClientes()).sum();
    }

    public Transformador devolverTransformadorCercano(Ubicacion ubicacionCliente) {
        if (transformadores.isEmpty())
            throw new TransformadorNullException("La zona no tiene ningun Transformador");
        return transformadores.stream().min(Comparator.comparingDouble(t -> t.calcularDistancia(ubicacionCliente)))
                .get();
    }

    public boolean perteneceClienteAZona(Cliente cliente) {
        return this.distanciaAcliente(cliente) < radio;
    }

    public Double distanciaAcliente(Cliente cliente) {
        return cliente.getUbicacion().calcularDistancia(ubicacion);
    }

    public Transformador conectarATransformadorCercano(Cliente cliente) {
        Transformador transformadorCercano = this.devolverTransformadorCercano(cliente.getUbicacion());
        transformadorCercano.agregarCliente(cliente);
        return transformadorCercano;
    }
}
