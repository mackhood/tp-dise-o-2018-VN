package residencial;

import clases.Cliente;

public class R6 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R6;

	public R6()
	{
		CARGOFIJO = 220.75;
		CARGOVARIABLE = 0.832;
		tipo = TipoResidencia.R6;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 500  && unCliente.consumoEnergetico() <= 600;
	}
}
