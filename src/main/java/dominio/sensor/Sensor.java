package dominio.sensor;

import dominio.regla.Regla;

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    private Regla regla;
    List<Condicion> observers = new ArrayList<>();
    double ultimaMedicion;
    
    
    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
        this.observers = unaRegla.getCondicionesACumplir();
    }

    public void recibirMedicion(double medicion){
        this.ultimaMedicion = medicion;
        this.notificar();
    }
    
    public void notificar(){
        this.getObservers().forEach(obs -> obs.actualizar(this));
    }

    public List<Condicion> getObservers() {
        return regla.getCondicionesACumplir();
    }
    
    public double getUltimaMedicion() {
    	return ultimaMedicion;
    }
}