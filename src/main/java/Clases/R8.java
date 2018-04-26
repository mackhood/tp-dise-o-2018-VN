package Clases;

public class R8 extends Categoria {

	double cargoFijo = 545.96;
	double cargoVariable = 0.851;
	
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 700 && cliente.consumoEnergeticoTotal() <= 1400;
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