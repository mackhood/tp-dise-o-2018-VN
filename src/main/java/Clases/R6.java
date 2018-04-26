package Clases;

public class R6 extends Categoria {

	double cargoFijo =  220.75;
	double cargoVariable = 0.832;
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 500 && cliente.consumoEnergeticoTotal() <= 600;
	}

	@Override
	public double getCostoFijo() {
		
		return cargoFijo;
	}

	@Override
	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
