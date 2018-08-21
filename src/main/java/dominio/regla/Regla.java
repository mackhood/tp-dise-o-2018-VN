package dominio.regla;

import dominio.actuador.Actuador;
import dominio.sensor.Condicion;

import java.util.ArrayList;
import java.util.List;

public class Regla {
	
    Actuador actuador;
    List<Condicion> condicionesACumplir = new ArrayList<>();

    public Regla(Actuador actuador, List<Condicion> condicionesACumplir) {
        this.actuador = actuador;
        this.condicionesACumplir = condicionesACumplir;
    }

    public boolean cumpleTodasLasCondiciones(){
        return condicionesACumplir.stream().allMatch(cond -> cond.cumpleCondicion());
    }
    
    public void ejecutarActuador(){
        if (this.cumpleTodasLasCondiciones() ) {
    	actuador.ejecutar();
        }
    }

    public List<Condicion> getCondicionesACumplir() {
        return condicionesACumplir;
    }
    
    public void agregarCondicion(Condicion unaCondicion) {
    	
    	condicionesACumplir.add(unaCondicion);
    }
    
    /* cada vez que una condicion recibe una nueva medicion notifica a la regla que la contiene y esta intenta ejecutar
    		el actuador, si todas las condiciones se cumplen ejecuta y sino no pasa nada */
    
    public void serNotificadaPor(Condicion condicion) {
    	
    	this.ejecutarActuador();
    }
}