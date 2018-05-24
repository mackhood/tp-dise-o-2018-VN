package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class ConsultaConsumoUltimasNHoras extends ConsultaConsumo {

    public ConsultaConsumoUltimasNHoras(DispositivoInteligente unDI, double ultimasNHoras) {
        super(unDI,ultimasNHoras);
    }

    @Override
    public double consultar() {
        return unDI.consumoUltimasNHoras(ultimasNHoras);
    }

    @Override
    public double consultarDI(DispositivoInteligente unDI) {
        return unDI.consumoUltimasNHoras(ultimasNHoras);
    }
}