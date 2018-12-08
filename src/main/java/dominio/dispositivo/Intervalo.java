package dominio.dispositivo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
// @Table(name = "intervalo")
public class Intervalo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idIntervalo")
    protected Long id;

    @Column(length = 50)
    protected LocalDateTime inicio;

    @Column(length = 50)
    protected LocalDateTime fin;

    public Intervalo() {
    }

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

        return inicio.isBefore(i.getInicio()) && fin.isBefore(i.getFin()) ||
                inicio.isBefore(i.getInicio()) && fin.isAfter(i.getFin()) ||
                inicio.isAfter(i.getInicio()) && inicio.isBefore(i.getFin()) && fin.isAfter(i.getFin()) ||
                inicio.isBefore(i.getInicio()) && fin.isBefore(i.getFin()) && fin.isAfter(i.getInicio());
    }

    public double horasDentroDe(Intervalo i) {

        if (inicio.isAfter(i.getInicio()) && fin.isBefore(i.getFin())) {

            return this.intervaloEnHoras();
        } else if (inicio.isAfter(i.getInicio()) && inicio.isBefore(i.getFin()) && fin.isAfter(i.getFin())) {

            return this.getInicio().until(i.getFin(), ChronoUnit.HOURS);
        } else if (inicio.isBefore(i.getInicio()) && fin.isAfter(i.getFin())) {

            return i.intervaloEnHoras();
        } else if (inicio.isBefore(i.getInicio()) && fin.isBefore(i.getFin()) && fin.isAfter(i.getInicio())) {

            return i.getInicio().until(fin, ChronoUnit.HOURS);
        } else {

            return 0;
        }
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime finIntervalo) {
        this.fin = finIntervalo;
    }

}
