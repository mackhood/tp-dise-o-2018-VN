package dominio.dispositivo;

public interface EstadoDispositivo {

    void apagar(DispositivoInteligente disp);

    void encender(DispositivoInteligente disp);

    void ponerModoAhorro(DispositivoInteligente disp);

    boolean estaEncendido();

    boolean estaApagado();
    
	boolean estaEnModoAhorro();
}
