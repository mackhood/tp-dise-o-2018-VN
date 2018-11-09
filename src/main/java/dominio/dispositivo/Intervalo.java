package dominio.dispositivo;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Embeddable
public class Intervalo {

    protected LocalDateTime inicio;
    protected LocalDateTime fin;

    
    public Intervalo(LocalDateTime inicio, LocalDateTime fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public boolean estaEntre(LocalDateTime fecha, LocalDateTime otraFecha) {

        return inicio.isAfter(fecha) && fin.isBefore(otraFecha);
    }

    public Intervalo ultimoMes() {

        LocalDateTime finIntervalo = LocalDateTime.now().minusMonths(1);
        return new Intervalo(LocalDateTime.now(), finIntervalo);
    }
    
    public double intervaloEnHoras() {

        return inicio.until(fin, ChronoUnit.HOURS);
    }
    
    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicioIntervalo) {
        this.inicio = inicioIntervalo;
    }
    
    public boolean caeDentroDe(Intervalo i) {
    	
    	return fin.isAfter(i.getInicio()) && fin.isBefore(i.getFin());
    }
    
    public double horasDentroDe(Intervalo i) {
    	
    	if (inicio.isAfter(i.getInicio()) && fin.isBefore(i.getFin())) {
    		
    		return this.intervaloEnHoras();
    	}
		
    	else if (inicio.isAfter(i.getInicio()) && fin.isAfter(i.getFin())) {
    		
    		return this.getInicio().until(i.getFin(), ChronoUnit.HOURS);
    	}
    	
    	else if (inicio.isBefore(i.getInicio()) && fin.isAfter(i.getFin())) {
    		
    		return i.intervaloEnHoras();
    	}
    	
    	else {
    		
    		return i.getInicio().until(fin, ChronoUnit.HOURS);
    	}
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime finIntervalo) {
        this.fin = finIntervalo;
    }


}
