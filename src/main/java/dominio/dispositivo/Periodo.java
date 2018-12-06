package dominio.dispositivo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Entity
@Table(name = "periodos")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPeriodo")
    protected Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private DispositivoInteligente disp;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    public Periodo(LocalDateTime fechaInicio, LocalDateTime fechaFin, DispositivoInteligente dispositivo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.disp = dispositivo;
    }

    ;

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public Intervalo convertir() {

        return new Intervalo(fechaInicio, fechaFin);
    }

    public double periodoEnHoras() {

        return fechaInicio.until(fechaFin, ChronoUnit.HOURS);
    }

    public double consumoPromedio() {

        return disp.consumoParaIntervalos(disp.getIntervalosDeUso().stream().filter(i -> i.estaEntre(fechaInicio, fechaFin)).collect(Collectors.toList())) / this.periodoEnHoras();
    }

    public double consumoTotal() {

        return disp.consumoParaIntervalos(disp.getIntervalosDeUso());
    }
}
