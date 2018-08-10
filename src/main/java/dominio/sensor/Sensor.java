package dominio.sensor;

import dominio.regla.Regla;

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    private Regla regla;
    String tipo;
    List<Condicion> observers = new ArrayList<>();
    int estado;
    public Sensor(Regla unaRegla, String tipoSensor) {
        this.regla = unaRegla;
        this.tipo = tipoSensor;
        observers = unaRegla.getCondicionesACumplir();
    }

    public void recibirMedicion(int estado){
        this.estado = estado;
        this.notificar();
    }
    public void notificar(){
        this.getObservers().stream().forEach(obs -> obs.actualizar(this));
    }

    public List<Condicion> getObservers() {
        return regla.getCondicionesACumplir();
    }

    public String getTipo() {
        return tipo;
    }
    public int getEstadoSensor(){return estado;}
}
