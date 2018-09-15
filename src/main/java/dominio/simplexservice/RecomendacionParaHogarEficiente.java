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


    //Hacer metodo que apague a los dispositivos inteligentes si supera la horasMaximaDeConsumoPorDispositivo solo a los dispositivos inteligentes
    public void realizarRecomendacionParaLosDispositivosInteligentes() {

        for(int i = 0; i < unCliente.getDispositivosInteligentes().size(); i++)
        {
            if(unCliente.getDispositivosInteligentes().get(i).getHorasDeUso() > this.getHorasMaximaDeConsumoPorDispositivo()[i])
            {
                unCliente.getDispositivosInteligentes().get(i).apagar();
            }
        }
    }
}
