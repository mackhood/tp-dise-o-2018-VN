package dominio;

import java.time.LocalDateTime;

import javax.persistence.Entity;

public class Consumo {
	
	LocalDateTime inicio;
	LocalDateTime fin;
	double consumo;
	
	public Consumo(LocalDateTime inicio, LocalDateTime fin, double consumo) {
		
		this.inicio = inicio;
		this.fin = fin;
		this.consumo = consumo;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public double getConsumo() {
		return consumo;
	}
}
