package dominio.regla;

import dominio.actuador.Actuador;
import dominio.sensor.Condicion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "regla")

public class Regla {
	@ManyToOne(fetch = FetchType.LAZY)
	private Actuador actuador;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Condicion> condicionesACumplir = new ArrayList<>();

	public Regla(Actuador actuador, List<Condicion> condicionesACumplir) {
		this.actuador = actuador;
		this.condicionesACumplir = condicionesACumplir;
	}

	public boolean cumpleTodasLasCondiciones() {

		return condicionesACumplir.stream().allMatch(cond -> cond.cumpleCondicion());
	}

	private void ejecutarActuador() {

		actuador.ejecutar();
	}

	public List<Condicion> getCondicionesACumplir() {

		return condicionesACumplir;
	}

	public void agregarCondicion(Condicion unaCondicion) {

		condicionesACumplir.add(unaCondicion);
	}

	public void chequearCondicionesYEjecutar() {

		if (this.cumpleTodasLasCondiciones()) {

			this.ejecutarActuador();
		}

		else
			actuador.ejecutarInversa();
	}
}