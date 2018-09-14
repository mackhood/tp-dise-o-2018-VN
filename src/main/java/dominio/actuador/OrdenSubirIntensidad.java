package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value="OrdenSubirIntensidad")
public class OrdenSubirIntensidad extends Actuador {

	public OrdenSubirIntensidad(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {

		dispositivos.forEach(d -> d.aumentarConsumoPor(50));
	}

	@Override
	public void ejecutarInversa() {

		dispositivos.forEach(d -> d.reducirConsumoPor(50));
	}
}