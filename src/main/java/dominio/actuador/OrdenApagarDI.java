package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue(value="OrdenApagarDI")
public class OrdenApagarDI extends Actuador {

	public OrdenApagarDI(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {
		dispositivos.forEach(DispositivoInteligente::apagar);
	}

	@Override
	public void ejecutarInversa() {
		dispositivos.forEach(DispositivoInteligente::encender);
	}
}
