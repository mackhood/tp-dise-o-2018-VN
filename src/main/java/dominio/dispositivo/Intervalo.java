package dominio.dispositivo;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Intervalo {
	
	LocalDateTime inicio;
	LocalDateTime fin;
	
	public Intervalo(LocalDateTime inicio, LocalDateTime fin) { this.inicio = inicio; this.fin = fin; };

	public boolean estaEntre(LocalDateTime fecha, LocalDateTime otraFecha) {
		
		return inicio.isAfter(fecha) && fin.isBefore(otraFecha);
	}
	
	public Intervalo ultimoMes() {
		
		LocalDateTime finIntervalo = LocalDateTime.now().minusMonths(1);
		return new Intervalo(LocalDateTime.now(),finIntervalo);
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicioIntervalo) {
		this.inicio = inicioIntervalo;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime finIntervalo) {
		this.fin = finIntervalo;
	}
	
	


}
