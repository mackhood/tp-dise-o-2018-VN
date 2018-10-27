package dominio.sensor;

import dominio.regla.Regla;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity

public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Regla regla;
    @Embedded
    private Medicion ultimaMedicion;

    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
    }

    public void recibirMedicion(Medicion medicion) {
        ultimaMedicion = medicion;
        this.notificar();
    }

    public List<Condicion> mismoTipo() {

        return this.getCondicionesACumplir().stream().filter(cond -> cond.getTipo().equals(ultimaMedicion.getTipo()))
                .collect(Collectors.toList());
    }

    private void notificar() {

        this.mismoTipo().forEach(cond -> cond.actualizar(this));
    }

    public List<Condicion> getCondicionesACumplir() {
        return regla.getCondicionesACumplir();
    }

    public Medicion getUltimaMedicion() {
        return ultimaMedicion;
    }

    public double getValorMedicion() {

        return ultimaMedicion.getValor();
    }
}