package dominio.simplexservice;

import dominio.usuario.Cliente;

public class RecomendacionParaHogarEficiente {

    Cliente unCliente;
    public RecomendacionParaHogarEficiente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public double getResultadoDeLaFuncionEconomica() {
        return new SimplexBuilder(unCliente).getResultadoDeLaFuncionEconomica();
    }

    public double[] getHorasMaximaDeConsumoPorDispositivo() {
        return new SimplexBuilder(unCliente).getHorasMaximasDeConsumoPorDispositivo();
    }

    /*
    public void realizarRecomendacionParaLosDispositivosInteligentes() {
        this.asignarHorasMaximasRecomendadasACadaDispositivo();
        unCliente.getDispositivosInteligentes().stream().forEach(dispositivo ->
        {
            if (dispositivo.consumioMasDeLaRecomendacion()) {
                dispositivo.apagar();
            }
        });
    }

    public void asignarHorasMaximasRecomendadasACadaDispositivo() {
        for (int i = 0; i < horasMaximaDeConsumoPorDispositivo.length; i++) {
            unCliente.getTodosLosDispositivos().get(i).setHorasMaximaPorConsumo(getHorasMaximaDeConsumoPorDispositivo()[i]);
        }
    }
    */
}
