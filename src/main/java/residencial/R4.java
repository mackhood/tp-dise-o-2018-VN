package residencial;

import clases.Cliente;
import categorias.*;

public class R4 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R4;

	public R4()
	{
		cargoFijo = 71.74;
		cargoVariable = 0.738;
		tipo = TipoResidencia.R4;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 400  && unCliente.consumoEnergetico() <= 450;
	}
}
