package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

public class ConsultaConsumoUltimasNHoras extends ConsultaConsumo {

    public ConsultaConsumoUltimasNHoras(DispositivoInteligente unDI, long ultimasNHoras) {
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