package dominio.dispositivo;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class EstadoEncendido implements EstadoDispositivo {
    private static EstadoEncendido instance = new EstadoEncendido();

    public static EstadoEncendido getInstance() {
        return instance;
    }

    public void apagar(DispositivoInteligente disp) {

        disp.horaApagado = LocalDateTime.now();
        disp.sumarHorasDeUso(disp.horaEncendido, disp.horaApagado);

        disp.cambiarEstado(EstadoApagado.getInstance());
    }

    public void encender(DispositivoInteligente disp) {

    }

    public void ponerModoAhorro(DispositivoInteligente disp) {
        disp.cambiarEstado(EstadoModoAhorro.getInstance());
    }

    public boolean estaEncendido() {
        return true;
    }

    public boolean estaApagado() {
        return false;
    }

    public boolean estaEnModoAhorro() {

        return false;
    }
}
