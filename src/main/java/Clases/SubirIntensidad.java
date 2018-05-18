package Clases;

public class SubirIntensidad extends Actuador {


    public SubirIntensidad(DispositivoInteligente unDisp, Fabricante unFabricante)
    {
        super(unDisp, unFabricante);
    }
    //No me queda claro si el actuador ya conoce desde antes al dispositivo que va a aplicar su accion
    @Override
    public void ejecutar() {

        if(puedeEjecutarse())
        {
            dispositivo.aumentarConsumoPor(150);
        }
    }
}
