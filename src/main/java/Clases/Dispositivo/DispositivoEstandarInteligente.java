package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandarInteligente extends DispositivoInteligente {


    public DispositivoEstandarInteligente(String nombre, int idInteligente, Fabricante unFabricante) {

        super (nombre, idInteligente, unFabricante);
    }

    @Override
    public int getPuntos() {
        return 15;
    }
}
