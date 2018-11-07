package dominio.dispositivo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "intervalo")
public class Intervalo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idIntervalo")
    protected Long id;

    @Column(length = 50)
    protected LocalDateTime inicio;
    @Column(length = 50)
    protected LocalDateTime fin;

    public Intervalo(){}
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
