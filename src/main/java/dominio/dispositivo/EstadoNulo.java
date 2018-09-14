package dominio.dispositivo;

public class EstadoNulo implements EstadoDispositivo {

	public void apagar(DispositivoInteligente disp) {

	}

	public void encender(DispositivoInteligente disp) {

	}

	public void ponerModoAhorro(DispositivoInteligente disp) {

	}

	@Override
	public boolean estaEncendido() {
		return false;
	}

	@Override
	public boolean estaApagado() {
		return false;
	}

	@Override

	public boolean estaEnModoAhorro() {

		return false;
	}
}
