package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue(value = "OrdenApagarDI")
public class OrdenApagarDI extends Actuador {

    public OrdenApagarDI(DispositivoInteligente disp) {
        super(disp);
    }

    @Override
    public void ejecutar() {
        dispositivo.apagar();
    }

    @Override
    public void ejecutarInversa() {
        dispositivo.encender();
    }
}
