package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "OrdenEncenderDI")

public class OrdenEncenderDI extends Actuador {

    public OrdenEncenderDI(DispositivoInteligente disp) {
        super(disp);
    }

    @Override
    public void ejecutar() {

        dispositivo.encender();
    }

    @Override
    public void ejecutarInversa() {

        dispositivo.apagar();
    }
}