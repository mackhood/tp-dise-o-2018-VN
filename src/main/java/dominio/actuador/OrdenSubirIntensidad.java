package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "OrdenSubirIntensidad")
public class OrdenSubirIntensidad extends Actuador {

    public OrdenSubirIntensidad(DispositivoInteligente disp) {
        super(disp);
    }

    @Override
    public void ejecutar() {

        dispositivo.aumentarConsumoPor(0.5);
    }

    @Override
    public void ejecutarInversa() {

        dispositivo.reducirConsumoPor(0.5);
    }
}