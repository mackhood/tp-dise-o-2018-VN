package residencial;

import clases.Cliente;
import categorias.*;

public class R7 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R7;

	public R7()
	{
		cargoFijo = 443.59;
		cargoVariable = 0.851;
		tipo = TipoResidencia.R7;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 600  && unCliente.consumoEnergetico() <= 700;
	}
}
