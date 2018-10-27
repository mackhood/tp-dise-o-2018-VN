package dominio.dispositivo;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Embeddable
public class Intervalo {

    LocalDateTime inicio;
    LocalDateTime fin;

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

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime finIntervalo) {
        this.fin = finIntervalo;
    }


}
