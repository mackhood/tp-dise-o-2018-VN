package dominio.dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DispositivoInteligente extends Dispositivo {

    public EstadoDispositivo estadoDispositivo;
    public LocalDateTime horaEncendido;
    public LocalDateTime horaApagado;

    public DispositivoInteligente(DispositivoInteligenteBuilder builder) {
        this.nombre = builder.nombre;
        this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
        this.equipoConcreto = builder.equipoConcreto;
        this.esBajoConsumo = builder.esBajoConsumo;
        this.horasDeUso = builder.horasDeUso;
        this.restriccionMinima = builder.restriccionMinima;
        this.restriccionMaxima = builder.restriccionMaxima;
        this.horasMaximaRecomendadaPorConsumo = builder.horasMaximaRecomendadaPorConsumo;
        this.estadoDispositivo = builder.estadoDispositivo;
        this.horaEncendido = builder.horaEncendido;
        this.horaApagado = builder.horaApagado;

    }

    public DispositivoInteligente() {

    }

    public void setHoraEncendido(LocalDateTime horaEncendido) {
        this.horaEncendido = horaEncendido;
    }

    public void setHoraApagado(LocalDateTime horaApagado) {
        this.horaApagado = horaApagado;
    }

    public LocalDateTime getHoraEncendido() {
        return horaEncendido;
    }

    public LocalDateTime getHoraApagado() {
        return horaApagado;
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
    }

    public void encender() {

        estadoDispositivo.encender(this);
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

    public void aumentarConsumoPor(double cantidad) {

        this.consumoEstimadoPorHora += cantidad;
    }

    public void reducirConsumoPor(double cantidad) {

        consumoEstimadoPorHora = Math.max(0.0,consumoEstimadoPorHora-cantidad);
        
        if (consumoEstimadoPorHora == 0) {
            this.estadoDispositivo .apagar(this);
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

        private EstadoDispositivo estadoDispositivo = new EstadoApagado();
        private LocalDateTime horaEncendido = null;
        private LocalDateTime horaApagado = null;
        private final String nombre;
        private double consumoEstimadoPorHora;
        private String equipoConcreto;
        private boolean esBajoConsumo;
        private double horasDeUso = 0;
        private double restriccionMinima;
        private double restriccionMaxima;
        private double horasMaximaRecomendadaPorConsumo;

        public DispositivoInteligenteBuilder(String nombre) {
            this.nombre = nombre;

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

        public DispositivoInteligenteBuilder esBajoConsumo(Boolean esBajoConsumo) {
            this.esBajoConsumo = esBajoConsumo;
            return this;
        }

        public DispositivoInteligenteBuilder restriccionMinima(Double restriccionMinima) {
            this.restriccionMinima = restriccionMinima;
            return this;
        }

        public DispositivoInteligenteBuilder restriccionMaxima(Double restriccionMaxima) {
            this.restriccionMaxima = restriccionMaxima;
            return this;
        }

        public DispositivoInteligenteBuilder horasDeUso(Double horasDeUso) {
            this.horasDeUso = horasDeUso;
            return this;
        }

        public DispositivoInteligenteBuilder horasMaximaRecomendadaPorConsumo(Double horasMaximaRecomendadaPorConsumo) {
            this.horasMaximaRecomendadaPorConsumo = horasMaximaRecomendadaPorConsumo;
            return this;
        }

        public DispositivoInteligente build() {
            return new DispositivoInteligente(this);
        }

    }

}
