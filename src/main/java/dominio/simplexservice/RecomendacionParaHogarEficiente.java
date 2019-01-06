package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendacionParaHogarEficiente {

    private Cliente unCliente;
    private SimplexBuilder simplexBuilder;

    public RecomendacionParaHogarEficiente(Cliente unCliente) {
        this.unCliente = unCliente;
        simplexBuilder = new SimplexBuilder(unCliente);
    }

    public double getResultadoDeLaFuncionEconomica() {
        return simplexBuilder.getResultadoDeLaFuncionEconomica();
    }


    public void realizarRecomendacionParaLosDispositivosInteligentes() {

        Map<DispositivoInteligente,Double> map = this.getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas();
        map.entrySet().stream().forEach(mapp -> mapp.getKey().apagar());
    }

    public Map<DispositivoInteligente,Double> getHorasMaximaDeConsumoPorDispositivoInteligente(){
        return simplexBuilder.getHorasMaximasDeConsumoPorDispositivoInteligente();
    }

    public Map<DispositivoInteligente,Double> getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas()
    {
        Map<DispositivoInteligente,Double> map = this.getHorasMaximaDeConsumoPorDispositivoInteligente();
        return map.entrySet().stream().filter(mapp -> mapp.getKey().getConsumoTotal() > mapp.getValue()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public List<DispositivoInteligente> getDispositivosInteligentesQueSuperanLasHorasMaximas(){
        List<DispositivoInteligente> inteligentes = new ArrayList<>();
        Map<DispositivoInteligente, Double> map = this.getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas();
        map.entrySet().stream().forEach(mapp -> inteligentes.add(mapp.getKey()));

        return inteligentes;
    }
}
