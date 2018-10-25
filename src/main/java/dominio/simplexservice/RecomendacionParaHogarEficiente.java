package dominio.simplexservice;

import dominio.usuario.Cliente;

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

    public double[] getHorasMaximaDeConsumoPorDispositivo() {
        return simplexBuilder.getHorasMaximasDeConsumoPorDispositivo();
    }


    //Hacer metodo que apague a los dispositivos inteligentes si supera la horasMaximaDeConsumoPorDispositivo solo a los dispositivos inteligentes
    public void realizarRecomendacionParaLosDispositivosInteligentes() {

        for (int i = 0; i < unCliente.getDispositivosInteligentes().size(); i++) {
            if (unCliente.getDispositivosInteligentes().get(i).getHorasDeUso() > this.getHorasMaximaDeConsumoPorDispositivo()[i]) {
                unCliente.getDispositivosInteligentes().get(i).apagar();
            }
        }
    }
}
