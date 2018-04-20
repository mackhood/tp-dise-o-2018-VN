package residencial;

import clases.Cliente;

public class R9 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R9;

	public R9()
	{
		CARGOFIJO = 887.19;
		CARGOVARIABLE = 0.851;
		tipo = TipoResidencia.R9;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 1400;
	}
}
