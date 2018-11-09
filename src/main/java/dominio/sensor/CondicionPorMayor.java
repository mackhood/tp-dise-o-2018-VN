package dominio.sensor;

import dominio.regla.Regla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "PorMayor")
public class CondicionPorMayor extends Condicion {

    public CondicionPorMayor(double valorLimite, String tipo) {
        super(valorLimite, tipo);
    }

    @Override
    public boolean cumpleCondicion() {

        return medicionActual > valorLimite;
    }

}
