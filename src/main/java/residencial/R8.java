package residencial;

import clases.Cliente;

public class R8 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R8;

	public R8()
	{
		CARGOFIJO = 545.96;
		CARGOVARIABLE = 0.851;
		tipo = TipoResidencia.R8;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 700  && unCliente.consumoEnergetico() <= 1400;
	}
}
