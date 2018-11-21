package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Actuador {

	@OneToOne(fetch = FetchType.EAGER)
	protected DispositivoInteligente dispositivo;

	/*
	 * Cada uno de los distintos Actuadores va a tener su propia logica a /
	 * implementar en ejecutar / El actuador sabe a que dispositivo tiene que
	 * afectar
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public Actuador(DispositivoInteligente disp) {
		dispositivo = disp;
	}

	public abstract void ejecutar();

	public abstract void ejecutarInversa();

}
