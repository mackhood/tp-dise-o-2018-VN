package dominio.sensor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PorIgual")
public class CondicionPorIgual extends Condicion {

    public CondicionPorIgual(double valorLimite, String tipo) {
        super(valorLimite, tipo);
    }

    @Override
    public boolean cumpleCondicion() {

        return medicionActual == valorLimite;
    }
}
