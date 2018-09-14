package dominio.dispositivo;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Intervalo {
	
	LocalDateTime diaYHoraEncendido;
	LocalDateTime diaYHoraApagado;
	
	public Intervalo(LocalDateTime diaYHoraEnc, LocalDateTime diaYHoraApagado) { this.diaYHoraEncendido = diaYHoraEnc; this.diaYHoraApagado = diaYHoraApagado; };

	public boolean estaEntre(LocalDateTime fecha, LocalDateTime otraFecha) {
		
		return diaYHoraEncendido.isAfter(fecha) && diaYHoraApagado.isBefore(otraFecha);
	}

	public LocalDateTime getDiaYHoraEncendido() {
		return diaYHoraEncendido;
	}

	public void setDiaYHoraEncendido(LocalDateTime diaYHoraEncendido) {
		this.diaYHoraEncendido = diaYHoraEncendido;
	}

	public LocalDateTime getDiaYHoraApagado() {
		return diaYHoraApagado;
	}

	public void setDiaYHoraApagado(LocalDateTime diaYHoraApagado) {
		this.diaYHoraApagado = diaYHoraApagado;
	}
	
	


}
