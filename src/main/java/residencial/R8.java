package residencial;

import clases.Cliente;
import categorias.*;

public class R8 extends Residencial
{ 
	//TipoResidencia tipo = TipoResidencia.R8;

	public R8()
	{
		cargoFijo = 545.96;
		cargoVariable = 0.851;
		tipo = TipoResidencia.R8;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 700  && unCliente.consumoEnergetico() <= 1400;
	}
}
