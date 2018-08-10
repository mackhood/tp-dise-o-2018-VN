package dominio.dispositivo;

public class EstadoModoAhorro implements EstadoDispositivo {

    private static EstadoModoAhorro instance = new EstadoModoAhorro();

    public static EstadoModoAhorro getInstance() {
        return instance;
    }

    public void apagar(DispositivoInteligente disp) {

    }

    public void encender(DispositivoInteligente disp) {
        disp.cambiarEstado(new EstadoEncendido());
    }

    public void ponerModoAhorro(DispositivoInteligente disp) {

    }

    public boolean estaEncendido() {
        return false;
    }

    public boolean estaApagado() {
        return false;
    }

	public boolean estaEnModoAhorro() {

		return true;
	}
}
