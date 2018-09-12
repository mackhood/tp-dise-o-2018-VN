package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Asignacion {


    @GeneratedValue
    @Id
    private Long id;

    @ElementCollection
    private List<String> notas;


    @ManyToOne
    private Tarea tarea;

    public Asignacion(Tarea tarea) {
        this.tarea = tarea;
        this.notas = new ArrayList<>();
    }

    public List<String> getNotas() {
        return notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public void agregarNota(String m) {
        notas.add(m);
    }
}
