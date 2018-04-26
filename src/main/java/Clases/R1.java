package Clases;

public class R1 extends Categoria {
	
	double cargoFijo = 18.76;
	double cargoVariable = 0.644;
	
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() < 150;
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
