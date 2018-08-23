package dominio.sensor;

import dominio.regla.Regla;

public abstract class Condicion {
    
	double valorLimite;
	String tipo;
	double medicionActual;
	Regla regla;

    public Condicion(Regla regla, double valorLimite, String tipo){
        
        this.regla = regla;
        this.valorLimite = valorLimite;
        this.tipo = tipo;
    }
    
   /* Esto sigue la siguiente logica: al instanciar una condicion, se define lo que llame valor limite
    	y si el valor medido esta por abajo o por arriba de este dependiendo si la cond es por mayor o por menor, se considera cumplida la misma.
    	  Lo pense de esta manera porque Franco nos dijo que podiamos tomar todas las mediciones como double */
    
    public abstract boolean cumpleCondicion();

    public void actualizar(Sensor unSensor) {
        
            medicionActual = unSensor.getValorMedicion();
            regla.serNotificadaPor(this);
    }
    
    public String getTipo() {
    	
    	return tipo;
    }
    
    public double getMedicionActual() {
    	
    	return medicionActual;
    }
}
