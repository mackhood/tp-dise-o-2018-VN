package repositories;

import java.util.ArrayList;
import java.util.List;

import dominio.dispositivo.TipoDispositivo;

public class RepositorioTipoDispositivo {

    private static RepositorioTipoDispositivo instance = new RepositorioTipoDispositivo();
    
    public static RepositorioTipoDispositivo getInstance()
    {
    	return instance;
    }
    
    List<TipoDispositivo> tipos = new ArrayList<>();
    
    private RepositorioTipoDispositivo()
    {
    	TipoDispositivo aireAcondicionado = new TipoDispositivo("Aire Acondicionado",90,360);
    	TipoDispositivo televisor = new TipoDispositivo("Televisor",90,360);
    	TipoDispositivo lampara = new TipoDispositivo("Lampara",90,360);
    	TipoDispositivo heladera = new TipoDispositivo("Heladera",0,0);
    	TipoDispositivo lavarropas = new TipoDispositivo("Lavarropas",6,30);
    	TipoDispositivo ventilador = new TipoDispositivo("Ventilador",120,360);
    	TipoDispositivo computadora = new TipoDispositivo("Computadora",60,360);
    	TipoDispositivo microondas = new TipoDispositivo("Microondas",3,15);
    	TipoDispositivo plancha = new TipoDispositivo("Plancha",3,30);
    	
    	tipos.add(aireAcondicionado);
    	tipos.add(televisor);
    	tipos.add(lampara);
    	tipos.add(heladera);
    	tipos.add(lavarropas);
    	tipos.add(ventilador);
    	tipos.add(computadora);
    	tipos.add(microondas);
    	tipos.add(plancha);
    }
    
    public List<TipoDispositivo> getTipos()
    {
    	return tipos;
    }

}
