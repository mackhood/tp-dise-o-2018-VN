package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue(value="OrdenPonerModoAhorro")
public class OrdenPonerModoAhorro extends Actuador {

	public OrdenPonerModoAhorro(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {

		dispositivos.forEach(DispositivoInteligente::ponerModoAhorro);	
	}
	
	@Override
	public void ejecutarInversa() {
		
		dispositivos.forEach(DispositivoInteligente::encender);
	}
}