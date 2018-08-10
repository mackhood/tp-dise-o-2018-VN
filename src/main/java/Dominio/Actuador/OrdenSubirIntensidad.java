package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenSubirIntensidad  implements  Actuador {

    List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();



    public OrdenSubirIntensidad(List<DispositivoInteligente> unDI){this.dispositivosInteligentes=unDI  ; }

    public void ejecutar() {
        dispositivosInteligentes.stream().forEach(x-> x.aumentarConsumoPor(50));
    }
}