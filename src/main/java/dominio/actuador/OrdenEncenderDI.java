package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value="OrdenEncenderDI")

public class OrdenEncenderDI extends Actuador {

	public OrdenEncenderDI(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {

		dispositivos.forEach(DispositivoInteligente::encender);
	}

	@Override
	public void ejecutarInversa() {

		dispositivos.forEach(DispositivoInteligente::apagar);
	}
}