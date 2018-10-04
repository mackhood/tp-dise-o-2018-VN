package dominio.dispositivo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dispositivoInteligente")
public class DispositivoInteligente extends Dispositivo {

    @Embedded
    public EstadoDispositivo estadoDispositivo;
    public LocalDateTime horaEncendido;
    public LocalDateTime horaApagado;
    @Embedded
    public List<Intervalo> intervalosDeUso = new ArrayList<>();
    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDispositivoInteligente")
    private Long id;

    public DispositivoInteligente(DispositivoInteligenteBuilder builder) {
        this.nombre = builder.nombre;
        this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
        this.equipoConcreto = builder.equipoConcreto;
        this.horasDeUso = builder.horasDeUso;
        this.estadoDispositivo = builder.estadoDispositivo;
        this.horaEncendido = builder.horaEncendido;
        this.horaApagado = builder.horaApagado;
        this.tipoDispositivo = builder.tipoDispositivo;

    }

    public DispositivoInteligente() {

    }

    public LocalDateTime getHoraEncendido() {
        return horaEncendido;
    }

    public void setHoraEncendido(LocalDateTime horaEncendido) {
        this.horaEncendido = horaEncendido;
    }

    public LocalDateTime getHoraApagado() {
        return horaApagado;
    }

    public void setHoraApagado(LocalDateTime horaApagado) {
        this.horaApagado = horaApagado;
    }

    public EstadoDispositivo estadoDispositivo() {
        return estadoDispositivo;
    }

    public double getConsumoEstimadoPorHora() {

        return consumoEstimadoPorHora;
    }

    public void setHorasDeUso(double horas) {

        this.horasDeUso = horas;
    }

    public boolean estaEncendido() {
        return estadoDispositivo.estaEncendido();
    }

    public boolean estaApagado() {
        return estadoDispositivo.estaApagado();
    }

    public void apagar() {
        estadoDispositivo.apagar(this);
        setHoraApagado(LocalDateTime.now());
        intervalosDeUso.add(new Intervalo(horaEncendido, horaApagado));
        this.reiniciar();
    }

    public List<Intervalo> encendidoEntre(LocalDateTime fecha, LocalDateTime otraFecha) {

        return intervalosDeUso.stream().filter(i -> i.estaEntre(fecha, otraFecha))
                .collect(Collectors.toList());
    }

    private void reiniciar() {

        this.setHoraApagado(null);
        this.setHoraEncendido(null);
    }

    public double intervaloEnHoras(Intervalo intervalo) {

        return intervalo.getInicio().until(intervalo.getFin(), ChronoUnit.HOURS);
    }

    public double consumoParaIntervalo(Intervalo intervalo) {

        return consumoEstimadoPorHora * intervaloEnHoras(intervalo);
    }

    public void encender() {
        estadoDispositivo.encender(this);
        setHoraEncendido(LocalDateTime.now());
    }

    public void ponerModoAhorro() {
        estadoDispositivo.ponerModoAhorro(this);
    }

    public void sumarHorasDeUso(LocalDateTime unHorario, LocalDateTime otroHorario) {

        horasDeUso = horasDeUso + unHorario.until(otroHorario, ChronoUnit.HOURS);
    }

    public double consumoUltimasNHoras(double horas) {
        if (horas > horasDeUso) {
            return consumoEstimadoPorHora * horasDeUso;
        } else {
            return consumoEstimadoPorHora * horas;
        }
    }

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {

        estadoDispositivo = estadoNuevo;
    }


    // Para aumentar consumo en un 1000% aumentarConsumoPor(consumoEstimadoPorHora*10); -- PARA TEST ENTREGA 3
    public void aumentarConsumoPor(double cantidad) {

        this.consumoEstimadoPorHora += cantidad;
    }

    public void reducirConsumoPor(double cantidad) {

        consumoEstimadoPorHora = Math.max(0.0, consumoEstimadoPorHora - cantidad);

        if (consumoEstimadoPorHora == 0) {
            this.estadoDispositivo.apagar(this);
        }
    }

    public double getConsumoTotal() {

        return horasDeUso * consumoEstimadoPorHora;
    }

    public boolean estaEnModoAhorro() {

        return estadoDispositivo.estaEnModoAhorro();
    }

    public int getPuntos() {
        return 15;
    }

    public static class DispositivoInteligenteBuilder {

        private final String nombre;
        private EstadoDispositivo estadoDispositivo = new EstadoApagado();
        private LocalDateTime horaEncendido = null;
        private LocalDateTime horaApagado = null;
        private double consumoEstimadoPorHora;
        private String equipoConcreto;
        private double horasDeUso = 0;
        private TipoDispositivo tipoDispositivo;

        public DispositivoInteligenteBuilder(String nombre) {
            this.nombre = nombre;

        }

        public DispositivoInteligenteBuilder tipoDispositivo(TipoDispositivo tipoDispositivo) {
            this.tipoDispositivo = tipoDispositivo;
            return this;
        }

        public DispositivoInteligenteBuilder horaEncendido(LocalDateTime horaEncendido) {

            this.horaEncendido = horaEncendido;
            return this;

        }

        public DispositivoInteligenteBuilder horaApagado(LocalDateTime horaApagado) {

            this.horaApagado = horaApagado;
            return this;
        }

        public DispositivoInteligenteBuilder estadoDispositivo(EstadoDispositivo estadoDispositivo) {

            this.estadoDispositivo = estadoDispositivo;
            return this;

        }

        public DispositivoInteligenteBuilder consumoEstimadoPorHora(Double consumoEstimadoPorHora) {
            this.consumoEstimadoPorHora = consumoEstimadoPorHora;
            return this;
        }

        public DispositivoInteligenteBuilder equipoConcreto(String equipoConcreto) {
            this.equipoConcreto = equipoConcreto;
            return this;
        }

        public DispositivoInteligenteBuilder horasDeUso(Double horasDeUso) {
            this.horasDeUso = horasDeUso;
            return this;
        }

        public DispositivoInteligente build() {
            return new DispositivoInteligente(this);
        }

    }

}
