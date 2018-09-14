package dominio.actuador;

import java.util.List;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType=DiscriminatorType.STRING)

public abstract class Actuador {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;


	/*
	 * Cada uno de los distintos Actuadores va a tener su propia logica a /
	 * implementar en ejecutar / El actuador sabe a que dispositivo tiene que
	 * afectar
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	DispositivoInteligente dispInteligente = null;
	@Transient
	protected List<DispositivoInteligente> dispositivos;

	public Actuador(List<DispositivoInteligente> dispositivosAfectados) {
		dispositivos = dispositivosAfectados;
	};

	public abstract void ejecutar();

	public abstract void ejecutarInversa();

	public void asignarDispositivo(DispositivoInteligente disp) {

		dispositivos.add(disp);
	}

	public void asignarGrupo(List<DispositivoInteligente> nuevosDispositivos) {

		dispositivos.addAll(nuevosDispositivos);
	}
}
