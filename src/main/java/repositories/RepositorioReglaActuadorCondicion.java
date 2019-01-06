package repositories;


import dominio.actuador.Actuador;
import dominio.actuador.OrdenEncenderDI;
import dominio.regla.Regla;
import dominio.sensor.*;
import persistence.DispositivosManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioReglaActuadorCondicion {
    private static RepositorioReglaActuadorCondicion instance = new RepositorioReglaActuadorCondicion();
    Medicion medicionCalor;
    Actuador ordenApagarDI;
    Regla regla;
    Sensor sensorCalor;
    List<Condicion> condicionesCumplir;

    private RepositorioReglaActuadorCondicion(){
        medicionCalor = new Medicion(2,"calor");
        ordenApagarDI = new OrdenEncenderDI(DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(9)));
        condicionesCumplir = new ArrayList<>();
        CondicionPorIgual condicion = new CondicionPorIgual(2,"calor");
        regla = new Regla(ordenApagarDI,condicionesCumplir);
        condicionesCumplir.add(condicion);
        condicion.asociarA(regla);
        
        //condicionesCumplir.add(new CondicionPorMayor(regla,1,"calor"));
        sensorCalor = new Sensor(regla);
    }

    public static RepositorioReglaActuadorCondicion getInstance(){return instance;}

    public Medicion getMedicionCalor() {
        return medicionCalor;
    }

    public Actuador getOrdenApagarDI() {
        return ordenApagarDI;
    }

    public Regla getRegla() {
        return regla;
    }

    public Sensor getSensorCalor() {
        return sensorCalor;
    }

    public List<Condicion> getCondicionesCumplir() {
        return condicionesCumplir;
    }
}
