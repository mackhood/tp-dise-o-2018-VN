package Clases.Dispositivo;

public class EstadoApagado implements EstadoDispositivo {

    public void apagar(DispositivoInteligente disp) {

    }

    public void encender(DispositivoInteligente disp) {
        disp.cambiarEstado(new EstadoApagado());
    }

    public void ponerModoAhorro(DispositivoInteligente disp) {

    }

    public boolean estaEncendido() {
        return false;
    }

    public boolean estaApagado() {
        return true;
    }
}
