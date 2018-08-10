package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenPonerModoAhorro  implements  Actuador{

    List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();



    public OrdenPonerModoAhorro(List<DispositivoInteligente> dispInteligente) {

        this.dispositivosInteligentes=dispInteligente  ;
    }

    @Override
    public void ejecutar() {
        dispositivosInteligentes.stream().forEach(DispositivoInteligente::ponerModoAhorro);
    }
}
