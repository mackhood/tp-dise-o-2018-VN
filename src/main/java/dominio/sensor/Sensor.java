package dominio.sensor;

import dominio.regla.Regla;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sensor {

	private Regla regla;
	private Medicion ultimaMedicion;

	public Sensor(Regla unaRegla) {
		this.regla = unaRegla;
	}

	public void recibirMedicion(Medicion medicion) {
		ultimaMedicion = medicion;
		this.notificar();
	}

	public List<Condicion> mismoTipo() {

		return this.getCondiciones().stream().filter(cond -> cond.getTipo().equals(ultimaMedicion.getTipo()))
				.collect(Collectors.toList());
	}

	private void notificar() {

		this.mismoTipo().forEach(cond -> cond.actualizar(this));
	}

	public List<Condicion> getCondiciones() {
		return regla.getCondicionesACumplir();
	}

	public Medicion getUltimaMedicion() {
		return ultimaMedicion;
	}

	public double getValorMedicion() {

		return ultimaMedicion.getValor();
	}
}