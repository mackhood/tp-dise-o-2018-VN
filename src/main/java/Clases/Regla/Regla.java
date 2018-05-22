package Clases.Regla;

import Clases.Actuador.Actuador;
import Clases.Sensor.Medicion;

import java.util.ArrayList;
import java.util.List;

public class Regla {
    Actuador actuador;
    List<Medicion> medicionesACumplir = new ArrayList<>();
    List<Medicion> medicionesObservers = new ArrayList();
    EstadoRegla estado = new ReglaNoCumplida();

    public Regla(Actuador actuador, List<Medicion> medicionesACumplir) {
        this.actuador = actuador;
        this.medicionesACumplir = medicionesACumplir;
    }

    public final void recibirMedicion(Medicion medicionTomada) {
        if (!estaEntreLosObservers(medicionTomada)) {
            this.agregarMedicion(medicionTomada);
            this.tratarActualizarEstadoRegla();
            this.templateEjecutar();
        }
    }

    private void agregarMedicion(Medicion medicionTomada)
    {
        medicionesObservers.add(medicionTomada);
    }

    public final boolean estaEntreLosObservers(Medicion medicionTomada) {
        return medicionesACumplir.stream().anyMatch(medicionACumplir -> medicionACumplir.compararMedicion(medicionTomada));
    }

    public final void cambiarEstado(EstadoRegla nuevoEstadoRegla) {
        estado = nuevoEstadoRegla;
    }

    public final void tratarActualizarEstadoRegla() {
        if (medicionesACumplir.size() == medicionesObservers.size())
            estado.cambiarEstado(this);
    }

    public void templateEjecutar() {
        if (estado.cumpleCondiciones())
            actuador.ejecutar();
    }


}