package Clases;

public class R4 extends Categoria {

	double cargoFijo = 71.74;
	double cargoVariable = 0.738;
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 400 && cliente.consumoEnergeticoTotal() <= 450;
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
