package residencial;

import clases.Cliente;
import categorias.*;

public class R3 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R3;

	public R3()
	{
		cargoFijo = 60.71;
		cargoVariable = 0.681;
		tipo = TipoResidencia.R3;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 325  && unCliente.consumoEnergetico() <= 400;
	}
}
