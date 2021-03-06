package dominio.sensor;

import javax.persistence.Embeddable;

@Embeddable
public class Medicion {

    double valor;
    String tipoMedicion;

    public Medicion(double val, String tipoMedicion) {
        this.valor = val;
        this.tipoMedicion = tipoMedicion;
    }

    public String getTipo() {

        return tipoMedicion;
    }

    public double getValor() {

        return valor;
    }
}
