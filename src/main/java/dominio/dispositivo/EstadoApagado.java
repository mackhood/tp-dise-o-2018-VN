package dominio.dispositivo;

import java.time.LocalDateTime;

public class EstadoApagado implements EstadoDispositivo {

    private static EstadoApagado instance = new EstadoApagado();

    public static EstadoApagado getInstance() {
        return instance;
    }

    public void apagar(DispositivoInteligente disp) {

    }

    public void encender(DispositivoInteligente disp) {
    	
        disp.cambiarEstado(EstadoEncendido.getInstance());
    }

    public void ponerModoAhorro(DispositivoInteligente disp) {

        disp.cambiarEstado(EstadoModoAhorro.getInstance());
    }

    public boolean estaEncendido() {
        return false;
    }

    public boolean estaApagado() {
        return true;
    }

    public boolean estaEnModoAhorro() {
        return false;
    }
}