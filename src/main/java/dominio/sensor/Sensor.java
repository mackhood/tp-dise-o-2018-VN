package dominio.sensor;

import dominio.regla.Regla;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sensor {

    private Regla regla;
    List<Condicion> observers = new ArrayList<>();
    Medicion ultimaMedicion;
    
    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
    }

    public void recibirMedicion(Medicion medicion){
        ultimaMedicion = medicion;
        this.notificar();
    }
    
    public List <Condicion> mismoTipo() {
    	
    	return this.getObservers().stream().filter(cond -> cond.getTipo().equals(ultimaMedicion.getTipo()) ).collect(Collectors.toList());
    }
    
    public void notificar() 
    
    {
        this.mismoTipo().forEach(cond -> cond.actualizar(this));
    }

    public List<Condicion> getObservers() {
        return regla.getCondicionesACumplir();
    }
    
    public Medicion getUltimaMedicion() {
    	return ultimaMedicion;
    }
    
    public double getValorMedicion() {
    	
    	return ultimaMedicion.getValor();
    }
}