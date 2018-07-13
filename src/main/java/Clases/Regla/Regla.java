package Clases.Regla;

import Clases.Actuador.Actuador;
import Clases.Sensor.Condicion;

import java.util.ArrayList;
import java.util.List;

public class Regla {
    Actuador actuador;
    List<Condicion> condicionesACumplir = new ArrayList<>();
    EstadoRegla estado = new ReglaNoCumplida();

    public Regla(Actuador actuador, List<Condicion> condicionesACumplir) {
        this.actuador = actuador;
        this.condicionesACumplir = condicionesACumplir;
    }

    public boolean cumpleTodasLasCondiciones(){
        return condicionesACumplir.stream().allMatch(cond -> cond.cumpleCondicion());
    }
    public void ejecutarActuador(){
        actuador.ejecutar();
    }

    public List<Condicion> getCondicionesACumplir() {
        return condicionesACumplir;
    }
}