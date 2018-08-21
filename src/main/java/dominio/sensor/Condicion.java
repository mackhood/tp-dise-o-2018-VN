package dominio.sensor;

import dominio.regla.Regla;

public abstract class Condicion {
    
	double valorLimite;
	double medicionActual;
	Regla regla;

    public Condicion(Regla regla, double valorLimite){
        
        this.regla = regla;
        this.valorLimite = valorLimite;
    }
    
   /* Esto sigue la siguiente logica: al instanciar una condicion, se define lo que llame valor limite
    	y si el valor medido esta por abajo o por arriba de este, se considera cumplida la misma.
    	  Lo pense de esta manera porque Franco nos dijo que podiamos tomar todas las mediciones como double */
    
    public abstract boolean cumpleCondicion();

    public void actualizar(Sensor unSensor) {
        
            this.medicionActual = unSensor.getUltimaMedicion();
            regla.serNotificadaPor(this);
    }
}
