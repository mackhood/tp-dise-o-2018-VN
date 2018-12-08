package dominio.regla;

import dominio.actuador.Actuador;
import dominio.sensor.Condicion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Regla")

public class Regla {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actuador actuador;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Condicion> condicionesACumplir = new ArrayList<>();

    public Regla(Actuador actuador, List<Condicion> listaCondiciones) {
        this.actuador = actuador;
        this.condicionesACumplir = listaCondiciones;
    }

    public Regla() {
    }

    public boolean cumpleTodasLasCondiciones() {

        return condicionesACumplir.stream().allMatch(cond -> cond.cumpleCondicion());
    }

    private void ejecutarActuador() {

        actuador.ejecutar();
    }

    public List<Condicion> getCondicionesACumplir() {

        return condicionesACumplir;
    }

    public void agregarCondicion(Condicion unaCondicion) {

        condicionesACumplir.add(unaCondicion);
    }

    public void chequearCondicionesYEjecutar() {

        if (this.cumpleTodasLasCondiciones()) {

            this.ejecutarActuador();
        } else
            actuador.ejecutarInversa();
    }
}