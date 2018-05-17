package Clases;

public class DispositivoInteligente extends Dispositivo
{
    public DispositivoInteligente(String nombre, double estimacionConsumo)
    {
        super(nombre,estimacionConsumo);
    }
    public double consumoUltimasXHoras(int X)
    {
        if(X >= horasDeUso)
        {
            return estimacionConsumo * horasDeUso;
        }
        else
        {
            return estimacionConsumo * X;
        }
    }

    public void modoAhorroDeEnergia()
    {
        estado = EstadoDispositivo.APAGADO.MODOAHORRO;
    }

}
