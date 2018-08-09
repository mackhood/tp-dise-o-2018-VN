package Dominio.repositories;

import Dominio.Dispositivo.Dispositivo;
import Dominio.Dispositivo.DispositivoEstandar;
import Dominio.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDispositivo {
    //public static RepositorioDispositivo instance;
    List<Dispositivo> dispositivos = new ArrayList<>();

    public RepositorioDispositivo()
    {
        DispositivoInteligente aireAcondicionado3500 = new DispositivoInteligente("aireAcondicionado","De 3500 frigorias", false,1.613,90,360);
        DispositivoInteligente aireAcondicionado2200 = new DispositivoInteligente("aireAcondicionado","De 2200 frigorias", true,1.013,90,360);
        DispositivoEstandar televisorTuboFluor21 = new DispositivoEstandar("televisor","Color de tubo fluorescente de 21", false,0.075,90,360);
        DispositivoEstandar televisorTuboFluor2943 = new DispositivoEstandar("televisor","Color de tubo fluorescente de 29 a 34", false,0.175,90,360);
        DispositivoEstandar televisorLCD40 = new DispositivoEstandar("televisor","LCD de 40", false,0.18,90,360);
        DispositivoInteligente televisorLED24 = new DispositivoInteligente("televisor","LED de 24", true,0.04,90,360);
        DispositivoInteligente televisorLED32 = new DispositivoInteligente("televisor","LED de 32", true,0.055,90,360);
        DispositivoInteligente televisorLED40= new DispositivoInteligente("televisor","LED de 40", true,0.08,90,360);

        DispositivoInteligente heladeraConFreezer = new DispositivoInteligente("heladera","Con freezer",true,0.09);
        DispositivoInteligente heladeraSinFreezer = new DispositivoInteligente("heladera","Sin freezer",true,0.075);

        DispositivoEstandar lavarropas5kgAgua = new DispositivoEstandar("lavarropas","Automatico de 5kg con calentamiento de agua",false,0.875,6,30);
        DispositivoInteligente lavarropas5kg = new DispositivoInteligente("lavarropas","Automatico de 5kg",true,0.175,6,30);
        DispositivoEstandar lavarropas5kgSemiautomatico = new DispositivoEstandar("lavarropas","Semi-automatico de 5kg",true,0.1275,6,30);

        DispositivoEstandar ventiladorDePie = new DispositivoEstandar("ventilador","De pie",true,0.09,120,360);
        DispositivoInteligente ventiladorDeTecho = new DispositivoInteligente("ventilador","De techo",true,0.06,120,360);

        DispositivoInteligente lamparaHalogena40W = new DispositivoInteligente("lampara","Halogena de 40W",false,0.04,90,360);
        DispositivoInteligente lamparaHalogena60W = new DispositivoInteligente("lampara","Halogena de 60W",false,0.06,90,360);
        DispositivoInteligente lamparaHalogena100W = new DispositivoInteligente("lampara","Halogena de 100W",false,0.015,90,360);
        DispositivoInteligente lampara11W = new DispositivoInteligente("lampara","De 11W",true,0.011,90,360);
        DispositivoInteligente lampara15W = new DispositivoInteligente("lampara","De 15W",true,0.015,90,360);
        DispositivoInteligente lampara20W = new DispositivoInteligente("lampara","De 20W",true,0.02,90,360);

        DispositivoInteligente pc = new DispositivoInteligente("PC", "De escritorio",true,0.4,60,360);

        DispositivoEstandar microondas = new DispositivoEstandar("microondas","Convencional",true,0.64,3,15);
        DispositivoEstandar plancha = new DispositivoEstandar("plancha","A vapor", true,0.75,3,30);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(aireAcondicionado2200);
        dispositivos.add(televisorTuboFluor21);
        dispositivos.add(televisorTuboFluor2943);
        dispositivos.add(televisorLCD40);
        dispositivos.add(televisorLED24);
        dispositivos.add(televisorLED32);
        dispositivos.add(televisorLED40);
        dispositivos.add(heladeraConFreezer);
        dispositivos.add(heladeraSinFreezer);
        dispositivos.add(lavarropas5kgAgua);
        dispositivos.add(lavarropas5kg);
        dispositivos.add(lavarropas5kgSemiautomatico);
        dispositivos.add(ventiladorDePie);
        dispositivos.add(ventiladorDeTecho);
        dispositivos.add(lamparaHalogena40W);
        dispositivos.add(lamparaHalogena60W);
        dispositivos.add(lamparaHalogena100W);
        dispositivos.add(lampara11W);
        dispositivos.add(lampara15W);
        dispositivos.add(lampara20W);
        dispositivos.add(pc);
        dispositivos.add(microondas);
        dispositivos.add(plancha);

    }

    public List<Dispositivo> getDispositivos()
    {
        return dispositivos;
    }

    public Dispositivo dispBuscado(Dispositivo dispositivo)
    {
        return this.getDispositivos().stream().filter(disp -> dispositivo.getEquipoConcreto() == disp.getEquipoConcreto()).collect(Collectors.toList()).get(0);
    }
    public double coefConsumokwh(Dispositivo dispositivo)
    {
        return this.dispBuscado(dispositivo).getConsumoEstimadoPorHora();
    }

    public double[] coeficientesDeConsumoKwh(List<Dispositivo> dispositivos)
    {
        List<Double> listaCoeficientes = dispositivos.stream().map(disp -> this.coefConsumokwh(disp)).collect(Collectors.toList());
        double[] arrayCoef = new double[listaCoeficientes.size()];
        for(int i = 0; i < listaCoeficientes.size();i++)
            arrayCoef[i] = listaCoeficientes.get(i);

        return arrayCoef;
    }
    public double[] coeficientesVariables(List<Dispositivo> dispositivos)
    {
        List<Double> listaCoeficientes = dispositivos.stream().map(disp -> this.coefConsumokwh(disp)).collect(Collectors.toList());
        double[] arrayCoef = new double[listaCoeficientes.size()];
        for(int i = 0; i < listaCoeficientes.size();i++)
            arrayCoef[i] = 1;

        return arrayCoef;
    }
}
