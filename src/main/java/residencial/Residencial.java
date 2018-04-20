package residencial;
import categorias.*;
import clases.*;

public class Residencial
{
	double CARGOFIJO;
	double CARGOVARIABLE;
	
	TipoResidencia tipo;
	public double facturacionEstimada(Cliente unCliente)
	{
		return this.CargoFijo() + this.CargoVariable() * unCliente.consumoEnergetico();
	}
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return false;
	}
	public double CargoFijo()
	{
		return CARGOFIJO;
	}
	public double CargoVariable()
	{
		return CARGOVARIABLE;
	}
}
