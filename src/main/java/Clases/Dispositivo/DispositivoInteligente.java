package Clases.Dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Clases.Fabricante;

public class DispositivoInteligente extends Dispositivo {

    private EstadoDispositivo estadoDispositivo;
    public LocalDateTime horaEncendido = null;
    public LocalDateTime horaApagado = null;

    public DispositivoInteligente(String nombre, double consumoEstimadoPorHora) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        estadoDispositivo = new EstadoApagado();
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

    public boolean esCiertoEstado(EstadoDispositivo estadoCond) {
        return estadoDispositivo.equals(estadoCond);
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
    
    /* Para este metodo de abajo no se me ocurre otra idea que hacer varios if con las distintas situaciones
      	tipo si la hora de encendido y apagado estan antes del intervalo de consulta, etc.
      		Si alguien tiene una mejor idea u otra forma de implementarlo buenisimo.
     */

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

    public void ejecutar(DispositivoFisico dispositivoFisico) {

        dispositivoFisico.ejecutar();
    }

    public void aumentarConsumoPor(double cantidad) {

        this.consumoEstimadoPorHora += cantidad;
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
}