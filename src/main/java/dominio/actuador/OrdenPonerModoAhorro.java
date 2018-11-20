package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue(value = "OrdenPonerModoAhorro")
public class OrdenPonerModoAhorro extends Actuador {

    public OrdenPonerModoAhorro(DispositivoInteligente disp) {
        super(disp);
    }

    @Override
    public void ejecutar() {

        dispositivo.ponerModoAhorro();
    }

    @Override
    public void ejecutarInversa() {

        if (dispositivo.estaApagado()) {
        	
        	dispositivo.apagar();
        }
        
        else {
        	
        	dispositivo.encender();
        }
    }
}