package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class ConsultaConsumoUltimasXHoras extends ConsultaConsumo {

    public ConsultaConsumoUltimasXHoras(DispositivoInteligente unDI, double ultimasXHoras)
    {
        super(unDI,ultimasXHoras);
    }

    @Override
    public double consultar() {
        return unDI.consumoUltimasXHoras(ultimasXHoras);
    }

    @Override
    public double consultarDI(DispositivoInteligente unDI) {
        return unDI.consumoUltimasXHoras(ultimasXHoras);
    }
}
