package Clases.Dispositivo;

public class EstadoEncendido implements EstadoDispositivo {

    public void apagar(DispositivoInteligente disp) {
        disp.cambiarEstado(new EstadoApagado());
    }

    public void encender(DispositivoInteligente disp) {

    }

    public void ponerModoAhorro(DispositivoInteligente disp) {
        disp.cambiarEstado(new EstadoModoAhorro());
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
