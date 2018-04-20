package residencial;

import clases.Cliente;

public class R4 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R4;

	public R4()
	{
		CARGOFIJO = 71.74;
		CARGOVARIABLE = 0.738;
		tipo = TipoResidencia.R4;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() > 400  && unCliente.consumoEnergetico() <= 450;
	}
}
