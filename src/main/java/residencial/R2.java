package residencial;

import clases.Cliente;

public class R2 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R2;

	public R2()
	{
		CARGOFIJO = 35.32;
		CARGOVARIABLE = 0.644;
		tipo = TipoResidencia.R2;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return (unCliente.consumoEnergetico() > 150.0  && unCliente.consumoEnergetico() <= 325.0);
	}
	
}
