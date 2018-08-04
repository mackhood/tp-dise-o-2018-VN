package Clases.repositories;

import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDispMinMax {
    List<Dispositivo> dispositivos = new ArrayList<>();
    double[] coefsRestriccion;
    public RepositorioDispMinMax()
    {
        DispositivoInteligente aireAcondicionado = new DispositivoInteligente("aireAcondicionado",90,360);
        DispositivoInteligente lampara = new DispositivoInteligente("lampara",90,360);
        DispositivoInteligente televisor = new DispositivoInteligente("televisor",90,360);
        DispositivoInteligente computadora = new DispositivoInteligente("computadora",60,360);
        DispositivoInteligente lavarropas = new DispositivoInteligente("lavarropas",6,30);
        DispositivoInteligente microondas = new DispositivoInteligente("microondas",3,15);
        DispositivoInteligente plancha = new DispositivoInteligente("plancha",3,30);
        DispositivoInteligente ventilador = new DispositivoInteligente("ventilador",120,360);

        dispositivos.add(aireAcondicionado);
        dispositivos.add(lampara);
        dispositivos.add(televisor);
        dispositivos.add(computadora);
        dispositivos.add(lavarropas);
        dispositivos.add(microondas);
        dispositivos.add(plancha);
        dispositivos.add(ventilador);
        coefsRestriccion = new double[dispositivos.size()];
    }
    public void agregarCerosAlArray()
    {
        for(int i=0;i<dispositivos.size();i++)
        {
            coefsRestriccion[i] = 0;
        }
    }
    public void modificarElemArray(int index)
    {
        coefsRestriccion[index] = 1;
    }
    public List<Dispositivo> getDispositivos()
    {
        return dispositivos;
    }
    public Dispositivo buscarDispositivo(Dispositivo dispBusq)
    {
        return this.getDispositivos().stream().filter(disp -> dispBusq.getNombre() == disp.getNombre()).collect(Collectors.toList()).get(0);
    }
    public double restriccionMinima(Dispositivo dispositivo)
    {
        return this.buscarDispositivo(dispositivo).getRestriccionMinima();
    }
    public double restriccionMaxima(Dispositivo dispositivo)
    {
        return this.buscarDispositivo(dispositivo).getRestriccionMaxima();
    }
    public int posicionDispositivoBusq(Dispositivo dispositivo)
    {
        return this.getDispositivos().indexOf(this.buscarDispositivo(dispositivo));
    }
    public double[] coefsResctriccionDeUnDispositivo(Dispositivo dispositivo)
    {
        this.agregarCerosAlArray();
        this.modificarElemArray(this.posicionDispositivoBusq(dispositivo));
        return coefsRestriccion;
    }
    /*
    public double[] coefsFuncionEconomica(List<Dispositivo> dispositivos)
    {
        double[] coefsFuncionEco = new double(dispositivos.size());
    }*/
}
