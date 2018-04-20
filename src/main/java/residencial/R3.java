package residencial;

import clases.Cliente;

public class R3 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R3;

	public R3()
	{
		CARGOFIJO = 60.71;
		CARGOVARIABLE = 0.681;
		tipo = TipoResidencia.R3;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 325  && unCliente.consumoEnergetico() <= 400;
	}
}
