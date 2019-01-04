package dominio.dispositivo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivoEstandar")
public class DispositivoEstandar extends Dispositivo {

    public DispositivoEstandar() {
    }

    private DispositivoEstandar(DispositivoEstandarBuilder builder) {
        this.nombre = builder.nombre;
        this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
        this.equipoConcreto = builder.equipoConcreto;
        this.horasDeUso = builder.horasDeUso;
        this.tipoDispositivo = builder.tipoDispositivo;

    }

    public double getHorasDeUso() {
        return horasDeUso;
    }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumoTotal() {
        return consumoEstimadoPorHora * horasDeUso;
    }

    public int getPuntos() {
        return 0;
    }

    public void serUsado(double cantHorasEstimada) {
        horasDeUso = horasDeUso + cantHorasEstimada;
    }

    public void apagar(){;}
    public boolean esInteligente(){return false;}

    public static class DispositivoEstandarBuilder {

        private final String nombre;
        private double consumoEstimadoPorHora;
        private String equipoConcreto;
        private double horasDeUso = 0;
        private TipoDispositivo tipoDispositivo;

        public DispositivoEstandarBuilder(String firstName) {
            this.nombre = firstName;

        }

        public DispositivoEstandarBuilder consumoEstimadoPorHora(Double consumoEstimadoPorHora) {
            this.consumoEstimadoPorHora = consumoEstimadoPorHora;
            return this;
        }

        public DispositivoEstandarBuilder equipoConcreto(String equipoConcreto) {
            this.equipoConcreto = equipoConcreto;
            return this;
        }


        public DispositivoEstandarBuilder horasDeUso(Double horasDeUso) {
            this.horasDeUso = horasDeUso;
            return this;
        }


        public DispositivoEstandarBuilder tipoDispositivo(TipoDispositivo tipoDispositivo) {
            this.tipoDispositivo = tipoDispositivo;
            return this;
        }

        public DispositivoEstandar build() {
            return new DispositivoEstandar(this);
        }

    }

}
