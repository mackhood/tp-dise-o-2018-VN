package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RecomendacionParaHogarEficiente {

    private Cliente unCliente;
    private SimplexBuilder simplexBuilder;

    public RecomendacionParaHogarEficiente(Cliente unCliente) {
        this.unCliente = unCliente;
        simplexBuilder = new SimplexBuilder(unCliente);
    }

    // A corregir
    /*public double getResultadoDeLaFuncionEconomica() {
        return simplexBuilder.getResultadoDeLaFuncionEconomica();
    }*/

    /*public Map<Dispositivo,Double> getHorasMaximaDeConsumoPorDispositivo() {
        return simplexBuilder.getHorasMaximasDeConsumoPorDispositivo();
    }*/


    //Hacer metodo que apague a los dispositivos inteligentes si supera la horasMaximaDeConsumoPorDispositivo solo a los dispositivos inteligentes
    public void realizarRecomendacionParaLosDispositivosInteligentes() {

        /*
        int i;
        for (i = 0; i < unCliente.getDispositivosInteligentes().size(); i++) {
            if (unCliente.getDispositivosInteligentes().get(i).getHorasDeUso() > this.getHorasMaximaDeConsumoPorDispositivo()[i]) {

                unCliente.getDispositivosInteligentes().get(i).apagar();
            }
        }*/

        Map<DispositivoInteligente,Double> map = this.getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas();
        map.entrySet().stream().forEach(mapp -> mapp.getKey().apagar());
    }

    /*public Map<Dispositivo,Double> getHorasMaximaDeConsumoPorDispositivoInteligente()
    {
        Map<Dispositivo,Double> map = new HashMap<>();
        map = this.getHorasMaximaDeConsumoPorDispositivo();
        /*map.entrySet().stream().forEach(mapp -> {System.out.println(mapp.getKey().getNombre());
            System.out.println(mapp.getValue());});*/
       /* System.out.println("BUENO2");
        return map.entrySet().stream().filter(mapp -> mapp.getKey().esInteligente()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

    }*/

    public Map<DispositivoInteligente,Double> getHorasMaximaDeConsumoPorDispositivoInteligente(){
        return simplexBuilder.getHorasMaximasDeConsumoPorDispositivoInteligente();
    }

    public Map<DispositivoInteligente,Double> getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas()
    {
        /*
        Map<Dispositivo,Double> map = new HashMap<>();
        map = this.getHorasMaximaDeConsumoPorDispositivoInteligente();
        System.out.println("supereLasHorasMaximas");
        map.entrySet().stream().forEach(mapp -> {System.out.println(mapp.getKey().getNombre());
            System.out.println(mapp.getValue());});
        System.out.println("BUENO3");
        map.entrySet().stream().forEach(mapp ->{
            System.out.println(mapp.getValue());});

        return map.entrySet().stream().filter(mapp -> mapp.getKey().getConsumoTotal() > mapp.getValue()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        */
        Map<DispositivoInteligente,Double> map = this.getHorasMaximaDeConsumoPorDispositivoInteligente();
        return map.entrySet().stream().filter(mapp -> mapp.getKey().getConsumoTotal() > mapp.getValue()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }
}
