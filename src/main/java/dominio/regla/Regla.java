package dominio.regla;

import dominio.actuador.Actuador;
import dominio.sensor.Condicion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Regla {
	
    private Actuador actuador;
    private List<Condicion> condicionesACumplir = new ArrayList<>();

    public Regla(Actuador actuador, List<Condicion> condicionesACumplir) {
        this.actuador = actuador;
        this.condicionesACumplir = condicionesACumplir;
    }

    private boolean cumpleTodasLasCondiciones() {
    	
        return condicionesACumplir.stream().allMatch(cond -> cond.cumpleCondicion());
    }
    
    private void ejecutarActuador(){
    	
    	actuador.ejecutar();
    }

    public List<Condicion> getCondicionesACumplir() {
        
    	return condicionesACumplir;
    }
   
    public void agregarCondicion(Condicion unaCondicion) {
    	
    	condicionesACumplir.add(unaCondicion);
    }

    
	public void serNotificadaPor(Condicion condicion) {
    	
    	if (condicion.cumpleCondicion() && this.cumpleTodasLasCondiciones() ) {
    		
    		this.ejecutarActuador();
    	}
    	
    	else actuador.ejecutarInversa();
    }
}