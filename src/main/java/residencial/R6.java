package residencial;

import clases.Cliente;
import categorias.*;

public class R6 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R6;

	public R6()
	{
		cargoFijo = 220.75;
		cargoVariable = 0.832;
		tipo = TipoResidencia.R6;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 500  && unCliente.consumoEnergetico() <= 600;
	}
}
