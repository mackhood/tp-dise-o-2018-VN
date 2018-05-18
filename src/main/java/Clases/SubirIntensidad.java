package Clases;

public class SubirIntensidad implements Actuador {
    Dispositivo dispositivo;

    //No me queda claro si el actuador ya conoce desde antes al dispositivo que va a aplicar su accion
    @Override
    public void ejecutar() {
        dispositivo.aumentarConsumoPor(150);
    }
}
