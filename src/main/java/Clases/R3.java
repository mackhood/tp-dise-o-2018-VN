package Clases;

public class R3 extends Categoria {

	double cargoFijo = 60.71;
	double cargoVariable = 0.681;
	
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 325 && cliente.consumoEnergeticoTotal() <= 400;
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
