package residencial;

import clases.Cliente;
import categorias.*;

public class R5 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R5;

	public R5()
	{
		cargoFijo = 110.38;
		cargoVariable = 0.794;
		tipo = TipoResidencia.R5;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 450  && unCliente.consumoEnergetico() <= 500;
	}
}
