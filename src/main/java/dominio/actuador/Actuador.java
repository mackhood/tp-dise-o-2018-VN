package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)

public abstract class Actuador {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<DispositivoInteligente> dispositivos;

    /*
     * Cada uno de los distintos Actuadores va a tener su propia logica a /
     * implementar en ejecutar / El actuador sabe a que dispositivo tiene que
     * afectar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Actuador(List<DispositivoInteligente> listaDI) {
        dispositivos = listaDI;
    }

    ;

    public abstract void ejecutar();

    public abstract void ejecutarInversa();

    public void asignarDispositivo(DispositivoInteligente disp) {

        dispositivos.add(disp);
    }

    public void asignarGrupo(List<DispositivoInteligente> nuevosDispositivos) {

        dispositivos.addAll(nuevosDispositivos);
    }
}
