package dominio.sensor;

import dominio.regla.Regla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "PorMenor")

public class CondicionPorMenor extends Condicion {

    public CondicionPorMenor(double valorLimite, String tipo) {
        super(valorLimite, tipo);
    }

    ;

    @Override
    public boolean cumpleCondicion() {

        return medicionActual < valorLimite;
    }

}
