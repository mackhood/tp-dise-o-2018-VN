package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tarea {
	@Id
	@GeneratedValue
	private Long id_tarea;
	private Date fechaLimiteDeEntrega;
	private String enunciado;
	
	public Tarea(Date fechaLimiteDeEntrega, String enunciado) {
		super();
		this.fechaLimiteDeEntrega = fechaLimiteDeEntrega;
		this.enunciado = enunciado;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Date getFechaLimiteDeEntrega() {
		return fechaLimiteDeEntrega;
	}

	public void setFechaLimiteDeEntrega(Date fechaLimiteDeEntrega) {
		this.fechaLimiteDeEntrega = fechaLimiteDeEntrega;
	}

}
