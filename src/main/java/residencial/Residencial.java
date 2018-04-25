package residencial;
import categorias.*;
import clases.*;

public class Residencial extends Categoria
{
	double cargoFijo;
	double cargoVariable;
	
	TipoResidencia tipo;
	@Override
	public double facturacionEstimada(Cliente unCliente)
	{
		return this.cargoFijo() + this.cargoVariable() * unCliente.consumoEnergetico();
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return false;
	}
	@Override
	public double cargoFijo()
	{
		return cargoFijo;
	}
	@Override
	public double cargoVariable()
	{
		return cargoVariable;
	}
}
